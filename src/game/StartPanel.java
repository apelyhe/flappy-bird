package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * the class for the panel which appears right before the game begins
 */
public class StartPanel extends BackgroundPanel implements KeyListener {

    /**
     * constructor of the class
     */
    public StartPanel() {
        this.addKeyListener(StartPanel.this);
        JLabel label = new JLabel("Press SPACE to start", SwingConstants.CENTER);
        label.setFont(new Font("Bodoni MT Black", Font.BOLD, 40));
        this.setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        requestFocus(true);
    }

    /**
     * an override of the KeyListener's keyTyped function
     * @param e, key event
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * an override of the KeyListener's keyPressed function.
     * After hitting space, the game begins
     * @param e, key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Screen.getInstance().switchGameScreen();
        }
    }

    /**
     * an override of the KeyListener's keyReleased function
     * @param e, key event
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
