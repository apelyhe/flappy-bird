package game;

import java.awt.*;

/**
 * an abstract class which contains the common functions of the sprites
 */
public abstract class Sprite {

    /**
     * the x and y coordinates of the position and its speed
     */
    private int x,y,speed;

    /**
     * the constructor of the class, it sets the position and the speed
     * @param x, x coordinate of position
     * @param y, y coordinate of position
     * @param speed, speed of sprite
     */
    public Sprite(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    /**
     * getter for the x coordinate
     * @return x, x coordinate of position
     */
    public int getX() {
        return x;
    }

    /**
     * getter for the y coordinate
     * @return y, y coordinate of position
     */
    public int getY() {
        return y;
    }

    /**
     * getter for the speed
     * @return speed, speed of sprite
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * setter for the x coordinate
     * @param x, x coordinate of position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setter for the y coordinate
     * @param y, y coordinate of position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * setter for the speed
     * @param speed, speed of sprite
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * the function which paints the sprite to the screen
     * @param g, 2D Graphics
     */
    public abstract void paint(Graphics2D g);


}
