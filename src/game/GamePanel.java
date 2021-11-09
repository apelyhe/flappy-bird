package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * the class which contains the logic behind the game
 */
public class GamePanel extends BackgroundPanel implements KeyListener, ActionListener, SizeManager {
    /**
     * spawn the bird to the map
     */
    private final Bird bird = new Bird(30, BG_GAMEFIELD_HEIGHT/2-50, 0); // positioning bird
    /**
     * create a list which contains the obstacles
     */
    private final ObstacleList list = new ObstacleList(new Obstacle(BG_WIDTH-OBSTACLE_WIDTH, 0, 2, BEGIN_GAP_HEIGHT));      //the list of obstacles gets the first obstacle
    /**
     * set the frames per second
     */
    private final int refresh = 12;     //Timer calls actionPerformed in every 'refresh' milliseconds, 1000/refresh = fps
    /**
     * the timer which calls the actionPerformed in every 'refresh' milliseconds
     */
    private final Timer timer = new Timer(refresh, this);
    /**
     * the number which shows how many pixels does the bird go up
     */
    private int birdDeltaUp;
    /**
     * the points earned by the player
     */
    private int points;
    /**
     * counts the number of the ticks to convert it into points
     */
    private int numOfTicks;
    /**
     * check if the game has begun yet
     */
    private boolean isPlaying = false;
    /**
     * an instance of the Scoreboard class
     */
    private Scoreboard sc;



    /**
     * the constructor of the class
     * @param sc, scoreboard where the top scorers will be represented
     */
    public GamePanel(Scoreboard sc) {
        this.sc = sc;
        this.setLayout(new BorderLayout());
        this.addKeyListener(GamePanel.this);
        //setFocusable(true);
        setDoubleBuffered(true);        // instead of drawing objects one by one, draw them on an image and then tell the renderer to draw that entire image
        points = 0;
        numOfTicks = 0;         // count the ticks for the point system
        birdDeltaUp = 0;
    }

    /**
     * an override of the KeyListener's keyTyped function
     * @param e the key event performed by the player
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * an override of the KeyListener's keyReleased function,
     * check if the bird releases the space button, to forbid the continuous flying
     * @param e the key event performed by the player
     */
    @Override
    public void keyReleased(KeyEvent e) {
        bird.offKey(e);
        birdDeltaUp = 0;
    }

    /**
     * an override of the KeyListener's keyPressed function,
     * check if the player presses the space to fly the bird
     * @param e the key event performed by the player
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !isPlaying) {
            timer.start();
            isPlaying = true;
        }
        birdDeltaUp = bird.onKey(e);           //returns the change of the bird's position upwards
    }

    /**
     * an override of the JPanel's paintComponent function
     * it is called when calling repaint()
     * @param g, Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {     // source: http://zetcode.com/javagames/basics/
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        draw(g2);
    }

    /**
     * an override of the ActionListener's actionPerformed function
     * it is called in every 'refresh' milliseconds
     * @param e, the action event performed by the player
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        tick();
    }

    /**
     * the function which called by actionPerformed in every 'refresh' milliseconds
     */
    private void tick() {
        updateGame();
        updateMap();
    }

    /**
     * a function which updates the game including bird's position, obstacles' position, points earned... etc
     */
    private void updateGame() {
        bird.setY(bird.getY() + DOWN_PER_TICKS - birdDeltaUp);
        list.moveObstacles();
        list.maintainList();
        //list.printListHelp();
        numOfTicks++;
        if(numOfTicks%83==0) {          //every ~1sec +1 point (1000ms/12 (refresh in ms) = 83.3)
            points++;
            System.out.println(points);
            if(points%5==0) {
                list.decrGap();
            }
            //System.out.println(list.getGap());
        }
        boolean collide = list.collideWith(bird.toRectangle());
       // System.out.println(points);
        if (collide) {
            gameOver();
        }
    }

    /**
     *  a function which updates the map by repainting it
     */
    private void updateMap() {
        //repaint(bird.getX(), 0, getX()+BIRD_WIDTH, BG_GAMEFIELD_HEIGHT);
        repaint();
    }

    /**
     * a function which draws out the bird and the obstacles
     * @param g, a 2D Graphics variable
     */
    private void draw(Graphics2D g) {
        bird.paint(g);
        list.drawList(g);
    }

    /**
     * a function which is called when the bird collides with an obstacle
     * it calls the Screen's switchGameOverScreen() function
     */
    private void gameOver() {
        resetGame();
        Screen.getInstance().switchGameOverScreen();
    }

    /**
     * a function to reset the positions of the sprites
     */
    private void resetGame() {
        list.removeObs();
        timer.stop();
        isPlaying = false;
        Screen.getInstance().setPlayerPoints(points);        //with this the game over screen can write out the points earned by the player
        saveTopList();
        points = 0;
        bird.setX(30);              // initial position of the bird
        bird.setY(BG_GAMEFIELD_HEIGHT/2-50);
        Obstacle o = new Obstacle(BG_WIDTH-OBSTACLE_WIDTH, 0, 2, BEGIN_GAP_HEIGHT);     //first obstacle
        list.add(o);
    }

    /**
     * add player to the scoreboard and write the whole scoreboard back to the file
     */
    private void saveTopList() {
        sc.addElement(Screen.getInstance().getPlayer());
    }



}
