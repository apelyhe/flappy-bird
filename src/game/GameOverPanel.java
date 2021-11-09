package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * the class for the panel which appears when the bird collides with an obstacle
 */
public class GameOverPanel extends BackgroundPanel implements KeyListener {

    /**
     * the points earned by the player
     */
    private int points;
    /**
     * the label which contains the points earned
     */
    private JLabel pLabel;

    /**
     * constructor of the class
     * sets layout and adds key listener
     */
    public GameOverPanel() {
        this.setLayout(new BorderLayout());
        this.addKeyListener(GameOverPanel.this);
    }

    /**
     * a function which sets the value of the 'points' attribute
     * @param points, the value which will be set for the attribute
     */
    public void setResultPoints(int points) {
        this.points = points;
        addPLabel();
        setReturnLabels();
    }

    /**
     * adds 'pLabel' to the screen
     */
    private void addPLabel() {
        String p = String.valueOf(points);
        if (pLabel != null) {
            remove(pLabel);
        }
        pLabel = new JLabel(p, SwingConstants.CENTER);
        pLabel.setFont(new Font("Bodoni MT Black", Font.BOLD, 70));
        add(pLabel, BorderLayout.CENTER);
    }

    /**
     * adds the remaining labels to the screen
     */
    private void setReturnLabels() {
        JLabel goLabel = new JLabel("Game Over", SwingConstants.CENTER);
        goLabel.setFont(new Font("Bodoni MT Black", Font.BOLD, 70));
        this.add(goLabel, BorderLayout.NORTH);

        JLabel enterLabel = new JLabel("Press ENTER to start a new game", SwingConstants.CENTER);
        enterLabel.setFont(new Font("Bodoni MT Black", Font.BOLD, 30));
        JLabel escLabel = new JLabel("or press ESC to return main menu", SwingConstants.CENTER);
        escLabel.setFont(new Font("Bodoni MT Black", Font.BOLD, 30));
        JPanel optionsLabels = new JPanel(new GridLayout(0, 1));
        optionsLabels.setOpaque(false);
        optionsLabels.add(enterLabel);

        optionsLabels.add(escLabel);
        this.add(optionsLabels, BorderLayout.SOUTH);
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
     * an override of the KeyListener's keyPressed function
     * this function changes the screen to the main menu or starts a new game
     * @param e, key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
           Screen.getInstance().switchStartScreen();
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Screen.getInstance().switchMenuScreen();
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
