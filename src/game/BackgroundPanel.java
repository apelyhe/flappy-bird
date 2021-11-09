package game;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel with an image as its background
 */
public class BackgroundPanel extends JPanel {           // JPanel which changes the background
    /**
     * Background type attribute
     */
    private final Sprite bg = new Background(0,0,0);

    /**
     * an override of the paintComponent function of the JPanel.
     * It is required in order to paint the image to the background
     * @param g, Graphics type parameter
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        draw(g2);
    }

    /**
     * this function draws the attribute to the JPanel
     * @param g2, 2D Graphics
     */
    private void draw(Graphics2D g2) {
        bg.paint(g2);
    }

}
