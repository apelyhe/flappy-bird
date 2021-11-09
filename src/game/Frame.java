package game;

import javax.swing.*;
import java.awt.*;

/**
 * the frame where the panels will appear
 */

public class Frame extends JFrame implements SizeManager {


    private final Screen screen = Screen.getInstance();     //singleton class

    /**
     * the constructor of Frame class.
     * Here we can set the properties of the JFrame
     * @param str, the title of the JFrame
     */
    public Frame(String str) {
        setTitle(str);
        setFrame();
        setVisible(true);
        requestFocus();
    }

    /**
     * A function to set the properties of the JFrame,
     * and add a Screen type variable to it
     */
    public void setFrame() {
        add(screen);
        this.setResizable(false);
        this.pack();
        this.setSize(new Dimension(BG_WIDTH, BG_HEIGHT)); //the size of background
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


}
