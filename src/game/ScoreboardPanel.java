package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

/**
 * the class for the panel where the scoreboard appears
 */
public class ScoreboardPanel extends BackgroundPanel implements KeyListener, SizeManager{
    /**
     * the panel which contains the 'TOP10' label
     */
    private final JPanel labelPanel = new JPanel();
    /**
     * the panel which contains the player's names and points
     */
    private final JPanel tablePanel = new JPanel();
    /**
     * the scoreboard which content appears in this panel
     */
    private Scoreboard sc;
    /**
     * the list where the content of the scoreboard will be stored
     */
    private List<Player> list = new ArrayList<Player>();


    /**
     * constructor of the class
     * @param sc, scoreboard which will appear on the screen
     */
    public ScoreboardPanel(Scoreboard sc) {
        this.sc = sc;
        sc.read(FILENAME);
        this.setLayout(new BorderLayout());
        setPanel();
        addKeyListener(ScoreboardPanel.this);
        requestFocus(true);
    }

    /**
     * sets the appearance of the panel
     */
    private void setPanel() {
        setLabelPanel();
        setTablePanel();
        JLabel esc = new JLabel("Press ESC to return main menu", SwingConstants.CENTER);
        esc.setFont(new Font("Bodoni MT Black", Font.BOLD, 30));
        add(esc, BorderLayout.SOUTH);
    }

    /**
     * set the appearance of the player's name and their points
     */
    public void setTablePanel() {
        tablePanel.removeAll();
        tablePanel.setOpaque(false);
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        list = sc.getTop10();
        for(Player p : list) {
            JPanel jp = new JPanel();
            jp.setOpaque(false);
            jp.setMaximumSize(new Dimension(600, 65));
            jp.setAlignmentX(Component.CENTER_ALIGNMENT);
            jp.setAlignmentY(Component.CENTER_ALIGNMENT);
            JLabel jl = new JLabel();
            jl.setText(p.getName() + "    " + p.getPoints() + System.lineSeparator());
            jl.setFont(new Font("Bodoni MT Black", Font.BOLD, 30));
            jp.add(jl);
            tablePanel.add(jp);
        }
        add(tablePanel, BorderLayout.CENTER);
    }

    /**
     * set the appearance of the 'TOP10' label
     */
    private void setLabelPanel() {
        labelPanel.setOpaque(false);
        JLabel label = new JLabel("TOP 10", SwingConstants.CENTER);
        label.setFont(new Font("Bodoni MT Black", Font.BOLD, 40));
        labelPanel.add(label);
        add(labelPanel, BorderLayout.NORTH);
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
     * @param e, key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Screen.getInstance().switchMenuScreen();
        }
    }

    /**
     * an override of the KeyListener's keyReleased function
     * @param e, key event
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    public void updateScoreboard() {
        sc.read(FILENAME);
    }

}
