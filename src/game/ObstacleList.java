package game;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * the storage class of the obstacles
 */
public class ObstacleList implements SizeManager {
    /**
     * the list where the obstacles are being stored
     */
    private final List<Obstacle> list = new ArrayList<Obstacle>();
    /**
     * the gap between the obstacles
     */
    private int gap;
    /**
     * if true, it decreases the gap between the obstacles
     */
    private boolean decreaseGap;        //to balance game, every 10sec gap-=5

    /**
     * constructor of the class
     * adds the first element to the list and initialize the attributes
     * @param o, the first obstacle in the list
     */
    public ObstacleList(Obstacle o) {
        if(o != null) {
            add(o);
            gap = o.getGap();
            decreaseGap = false;
        }
    }

    public List<Obstacle> getList() {
        return list;
    }

    public int getGap() {
        return gap;
    }

    /**
     * this function adds elements to the list
      * @param o, an obstacle to be added to the list
     */
    public void add(Obstacle o) {
        list.add(o);
    }

    /**
     * calls every elements' paint method for drawing them
     * @param g, 2D Graphics
     */
    public void drawList(Graphics2D g) {
        for (Obstacle o : list) {
            o.paint(g);
        }
    }

    /**
     * moves the obstacles with their speed
     */
    public void moveObstacles() {
        for (Obstacle o : list) {
            o.setX(o.getX() - o.getSpeed());
        }
    }

    /**
     * this method draws the obstacles, moves them
     * and decrease the gap between them if needed
     */
    public void maintainList() {      //draws the obstacles and moves them
        Obstacle earliest = list.get(list.size() - 1);   //last element
        if (BG_WIDTH - earliest.getX() >= SPACE_BETWEEN_OBS) {   //if the gap between 2 obs is more than or equal to 300, spawn a new obstacle
            if(!decreaseGap) {
                list.add(new Obstacle(BG_WIDTH, 0, earliest.getSpeed(), gap));  //create a new obstacle in the end of the map
            } else {
                if(gap >= 125) {
                    gap -= 5;
                }
                Obstacle o = new Obstacle(BG_WIDTH, 0, earliest.getSpeed(), gap);
                list.add(o);
                decreaseGap = false;
            }
        }
        list.removeIf(o -> o.getX() <= -OBSTACLE_WIDTH);       //if the obstacle disappears from the screen, remove it from the list, source: https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
    }

    /*public void printListHelp() {
        System.out.println(list.get(0).getX());
    }*/

    /**
     * check if the bird collides with the ground or an obstacle
     * @param bird, the bird controlled by the user
     * @return true if it collides and false otherwise
     */
    public boolean collideWith(Rectangle bird) {
        boolean withGround = collideWithGround(bird);
        boolean withObstacle = collideWithObstacle(bird);
        return withGround || withObstacle;
    }

    /**
     * remove all of the obstacles
     */
    public void removeObs() {
        list.clear();
    }

    /**
     * this method begins the decreasing of the gap
     */
    public void decrGap() {
        decreaseGap = true;
    }

    /**
     * check if the bird collides with the ground
     * @param bird, the bird controlled by the user
     * @return true if it collides, false otherwise
     */
    private boolean collideWithGround(Rectangle bird) {
        int yCoordinateOfGround = BG_GAMEFIELD_HEIGHT;
        return bird.y + BIRD_HEIGHT >= yCoordinateOfGround;       //bird collides with ground
    }

    /**
     * check if the bird collides with an obstacle
     * @param bird, the bird controlled by the user
     * @return true if it collides, false otherwise
     */
    private boolean collideWithObstacle(Rectangle bird) {       // source: https://www.geeksforgeeks.org/find-two-rectangles-overlap/
        return !birdNextToObs(bird) && !birdBetweenTopAndBottom(bird);

    }

    /**
     * check if the bird is next to the obstacles (required checking the collision)
     * @param bird, the bird controlled by the user
     * @return true if the bird is next to the obstacles, false otherwise
     */
    private boolean birdNextToObs(Rectangle bird) {
        Obstacle last = list.get(0);
        return (bird.x+20) > last.getX() + OBSTACLE_WIDTH || last.getX() > (bird.x-5) + BIRD_WIDTH;      //check if bird is next to the last obstacle,
    }

    /**
     * check if the bird is between the top and bottom obstacles (required checking the collision)
     * @param bird, bird controlled by the user
     * @return true if the bird is between the obstacles, false otherwise
     */
    private boolean birdBetweenTopAndBottom(Rectangle bird) {
        Obstacle last = list.get(0);
        return last.getTopObsY() < (bird.y+5) && (bird.y-5) + BIRD_HEIGHT < last.getBottomObsY();       ////check if bird is between the last top & bottom obstacles
    }

}