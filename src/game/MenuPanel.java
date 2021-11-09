package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the class for the panel which contains the menu
 */
public class MenuPanel extends BackgroundPanel implements ActionListener {
    /**
     * the button to start a new game
     */
    private final JButton newGameButton = new JButton("New Game");
    /**
     * the button to select scoreboard
     */
    private final JButton scoreboardButton = new JButton("Scoreboard");
    /**
     * the button to exit from the game
     */
    private final JButton exitButton = new JButton("Exit");
    /**
     * the panel which contains the buttons
     */
    private final JPanel buttons = new JPanel();

    /**
     * the constructor of the class
     * it sets the layout and the view
     */
    public MenuPanel() {
        setLayout(new BorderLayout());
        setView();
    }

    /**
     * a function for setting the look of the menu
     */
    private void setView() {
        setTitle();
        setButtons();
        setFocusable(true);
    }

    /**
     * a function which locates the buttons and adds key listeners to them
     */
    private void setButtons() {
        newGameButton.addActionListener(this);
        scoreboardButton.addActionListener(this);
        exitButton.addActionListener(this);
        buttons.setOpaque(false);           //to make it transparent, source: https://stackoverflow.com/questions/10059020/transparent-jpanel
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 20, 30, 20);
        newGameButton.setPreferredSize(new Dimension(275, 75));
        scoreboardButton.setPreferredSize(new Dimension(275, 75));
        exitButton.setPreferredSize(new Dimension(275, 75));
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttons.add(newGameButton, gbc);
        gbc.gridy = 1;
        buttons.add(scoreboardButton, gbc);
        gbc.gridy = 2;
        buttons.add(exitButton, gbc);
        add(buttons);
    }

    /**
     * a function which sets the title label of the screen
     */
    private void setTitle() {
        JPanel title = new JPanel();
        title.setOpaque(false);
        JLabel welcomeLabel = new JLabel("Birdy Flap");
        welcomeLabel.setFont(new Font("Bodoni MT Black", Font.BOLD, 100));
        title.add(welcomeLabel);
        add(title, BorderLayout.NORTH);
    }

    /**
     * an override for the ActionListener's actionPerformed method
     * this method switches to the panel selected by the user
     * @param e, action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            Screen.getInstance().switchNameScreen();
        }
        else if (e.getSource() == scoreboardButton) {
            Screen.getInstance().switchScoreboardView();
            //JPanel scoreboard = new Scoreboard();
            //screen.add(scoreboard,"Scoreboard");
            //screenPanel.show(screen,"Scoreboard");
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

}
