package game;

/**
 * an interface for the constant numbers and filename
 */
public interface SizeManager {
    /**
     * height of the background
     */
    int BG_HEIGHT = 800;
    /**
     * width of the background
     */
    int BG_WIDTH = 600;
    /**
     * the height of the background which does not include the ground
     */
    int BG_GAMEFIELD_HEIGHT = 645;
    /**
     * the height of the bird
     */
    int BIRD_HEIGHT = 58;
    /**
     * the width of the bird
     */
    int BIRD_WIDTH = 75 ;
    /**
     * the width of the obstacles
     */
    int OBSTACLE_WIDTH = 100;
    /**
     * the space between the obstacles
     */
    int SPACE_BETWEEN_OBS = 300;
    /**
     * the number of pixels that the bird goes down in every tick
     */
    int DOWN_PER_TICKS = 5;
    /**
     * the number of pixels that the bird goes up when pressing space (or up arrow)
     */
    int MOVE_UP = 11;
    /**
     * the initial gap between the obstacles
     */
    int BEGIN_GAP_HEIGHT = 200;
    /**
     * the name of the file where the scoreboard is being serialized
     */
    String FILENAME = "src/game/scoreboard.txt";
}
