package game;

import javax.swing.*;
import java.awt.*;

/**
 * The class which manages the screen.
 * It is a singleton class.
 */
public class Screen extends JPanel implements SizeManager{       //singleton class, source: https://www.geeksforgeeks.org/singleton-class-java/
    /**
     * this an attribute to be sure that the class only has one instance
     */
    private static Screen single = null;
    /**
     * this screen attribute is the main panel where the different panels appear
     */
    private static final JPanel screen = new JPanel();
    /**
     * the panel where the menu appears
     */
    private static final JPanel menu = new MenuPanel();
    /**
     * the panel where the screen where the player can give his name appears
     */
    private static JPanel name = new NamePanel();
    /**
     * the panel where the screen which prints "press space to begin" appears
     */
    private static JPanel start = new StartPanel();
    /**
     * the scoreboard which will be used by the game panel and the scoreboard panel
     */
    private static Scoreboard sc = new Scoreboard();
    /**
     * the panel where the game appears
     */
    private static GamePanel game = new GamePanel(sc);
    /**
     * the panel where the game over screen appears
     */
    private static GameOverPanel gameOver = new GameOverPanel();
    /**
     * the panel where the scoreboard appears
     */
    private static final ScoreboardPanel scoreboard = new ScoreboardPanel(sc);
    /**
     * the layout of the screen attribute
     */
    public static CardLayout screenPanel = new CardLayout();
    /**
     * a variable which contains the name and the points of the player
     */
    private static Player player;

    /**
     * constructor of the class
     * private because the class is singleton
     * this function adds the components to the main panel
     */
    private Screen() {
        this.setLayout(new BorderLayout());
        screen.setLayout(screenPanel);
        screen.add(menu, "Menu");
        screen.add(name, "Name");
        screen.add(start, "Start");
        screen.add(game, "Game");
        screen.add(scoreboard, "Scoreboard");
        screen.add(gameOver, "Game over");
        this.add(screen, BorderLayout.CENTER);
        screenPanel.show(screen, "Menu");
    }

    /**
     * a function which sets the name of the player
     * @param text, the player's name
     */
    public static void setPlayerName(String text) {
        player = new Player(text);
    }

    /**
     * a function which sets the player's points
     * @param playerPoints, points to be set
     */
    public void setPlayerPoints(int playerPoints) {
        player.setPoints(playerPoints);
    }

    /**
     * getter for player attribute
     * @return player
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * to be sure that only one instance of the class exists
     * @return single, if there is no object from the class yet, create one and returns it
     * if there an object from the class exists, the function returns that object
     */
    public static Screen getInstance() {        // not to have more than one instance of the class
        if (single == null) {
            single = new Screen();
        }
        return single;
    }
    /**
     * a function which shows the main menu
     */
    public static void switchMenuScreen() {
        screenPanel.show(screen, "Menu");
        menu.requestFocus(true);
    }
    /**
     * a function which shows the screen where the player can give his name
     */
    public static void switchNameScreen() {
        screenPanel.show(screen, "Name");
        name.requestFocus(true);
    }
    /**
     * a function which shows the screen which prints "press space to begin"
     */
    public static void switchStartScreen() {
        screenPanel.show(screen, "Start");
        start.requestFocus(true);
    }
    /**
     * a function which shows the game
     */
    public static void switchGameScreen() {
        screenPanel.show(screen, "Game");
        game.requestFocus(true);
    }
    /**
     * a function which shows the game over screen
     */
    public static void switchGameOverScreen() {
        gameOver.setResultPoints(player.getPoints());
        screenPanel.show(screen, "Game over");
        gameOver.requestFocus(true);
    }

    /**
     * a function which shows the scoreboard
     */
    public static void switchScoreboardView() {
        scoreboard.updateScoreboard();
        scoreboard.setTablePanel();
        screenPanel.show(screen, "Scoreboard");
        scoreboard.requestFocus(true);
    }

}
