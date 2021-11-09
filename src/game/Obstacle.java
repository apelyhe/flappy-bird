package game;

import java.awt.*;
import java.util.Random;

/**
 * the class which describes the obstacles
 */
public class Obstacle extends Sprite implements SizeManager{
    /**
     * the top part and the bottom part of the obstacle
     */
    private Rectangle topObs, bottomObs;
    /**
     * the gap between the top and the bottom part of the obstacle
     */
    private int gap;

    /**
     * the constructor of the class
     * sets the position and the gap by the parameters:
     * @param x, for x coordinate
     * @param y, for y coordinate
     * @param speed, for speed
     * @param gap_size, the size of the gap
     */
    public Obstacle(int x, int y, int speed, int gap_size) {
        super(x, y, speed);
        setGap(gap_size);
        createTopObs();
        createBottomObs();
    }

    public int getGap() {
        return gap;
    }

    /**
     * @return the y coordinate of the top obstacle's bottom left corner
     */
    public int getTopObsY() {
        return topObs.height;
    }

    /**
     * @return the y coordinate of the bottom obstacle's top left corner
     */
    public int getBottomObsY() {
        return BG_GAMEFIELD_HEIGHT - bottomObs.height;
    }

    /**
     * sets the value of the gap
     * @param gap_size, the value to be set for the gap attribute
     */
    private void setGap(int gap_size) {
        gap = gap_size;          //TODO if score % 15 == 0 && score != 0, GAP -= 10, min gap = 110
    }

    /**
     * this function creates the top part of the obstacle
     * it uses random numbers for setting its height
     */
    private void createTopObs() {
        Random rand = new Random();
        int min = 10;       //minimum height of an obstacle
        int max = BG_GAMEFIELD_HEIGHT-gap-min;            //maximum height of the top obstacle,
        int height = rand.nextInt(max-min) + min;      //generate random number between min and max
        topObs = new Rectangle(OBSTACLE_WIDTH, height);
    }

    /**
     * this function creates the bottom part of the obstacle
     */
    private void createBottomObs() {
        int height = BG_GAMEFIELD_HEIGHT- topObs.height - gap;
        bottomObs = new Rectangle(OBSTACLE_WIDTH, height);
    }

    /**
     * draws the obstacles to the screen
     * @param g, 2D Graphics
     */
    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getX(), 0, topObs.width, topObs.height);
        g.fillRect(getX(), topObs.height+gap,bottomObs.width, bottomObs.height);
    }
}
