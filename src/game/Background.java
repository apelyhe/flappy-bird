package game;

import javax.swing.*;
import java.awt.*;

/**
 * set an image as the background of the panel
 */
public class Background extends Sprite {
    /**
     * the image to be set as background
     */
    private Image background;

    /**
     * the constructor of the class.
     * It gets 3 parameters:
     * @param x, x element of the position
     * @param y, y element of the position
     * @param speed, speed of the background
     * Calls the constructor of the base class
     */
    public Background(int x, int y, int speed) {
        super(x, y, speed);
        ImageIcon path = new ImageIcon("src/resources/background.png");
        background = path.getImage();
    }

    /**
     * an override of the Sprite's paint function.
     * It draws the image to the panel
     * @param g, 2D Graphics
     */
    @Override
    public void paint(Graphics2D g) {
        g.drawImage(background,getX(),getY(),null);     //set x,y positions, ImageObserver is null
    }
}
