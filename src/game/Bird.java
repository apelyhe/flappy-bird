package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * the class of the bird which is controlled by the player
 */
public class Bird extends Sprite implements SizeManager {
    /**
     * an image (.png) of the bird
     */
    private final Image bird;
    /**
     * check if the space button was released,
     * it is important because with this the bird cannot fly continuously
     */
    private boolean canFly;

    /**
     * constructor of the class
     * @param x, x coordinate of the bird
     * @param y, y coordinate of the bird
     * @param speed, speed of the bird
     */
    public Bird(int x, int y, int speed) {
        super(x, y, speed);
        ImageIcon path = new ImageIcon("D:/suli/3.félév/Prog 3/nhf/HW/src/resources/bird.png");
        bird = path.getImage();
        canFly = true;
    }

    /**
     * draws the bird's image to the screen
     * @param g, 2D Graphics
     */
    @Override
    public void paint(Graphics2D g) {
        g.drawImage(bird,getX(),getY(),null);     //set x,y positions, ImageObserver is null
    }

    /**
     * called in every key event,
     * check if the space or the up arrow was pressed, if yes move the bird upwards
     * @param e, key event
     * @return a constant number (defined in SizeManager class) if the expected key was pressed,
     * 0 otherwise
     */
    public int onKey(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            if(canFly) {
                canFly = false;     //not to hold down space and fly continuously
                return MOVE_UP;
            }
        }
        return 0;       //if noone is hitting space, the bird is not moving upwards
    }

    /**
     * called in every key event,
     * check if the space or up arrow key was released, if yes the function allows to fly again
     * @param e, key event
     */
    public void offKey(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            canFly = true;
        }
    }

    /**
     * a function which puts the bird into a rectangle
     * @return a rectangle with the bird in it
     */
    public Rectangle toRectangle() {     //put the bird into a Rectangle and return that Rectangle
        return new Rectangle(getX(), getY(), BIRD_WIDTH, BIRD_HEIGHT);  //first 2 values are the coordinates of the top left corner
    }
}
