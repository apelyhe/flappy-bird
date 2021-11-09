package game;

import java.io.Serializable;

/**
 * a class which stores the player's name and points
 */
public class Player implements Comparable<Player>, Serializable {
    private static final long serialVersionUID = 4365079005825857649L;
    /**
     * player's name
     */
    private String name;
    /**
     * player's points
     */
    private int points;

    /**
     * constructor of the class
     * @param n the name of the player
     */
    public Player(String n) {
        name = n;
    }

    /**
     * getter for the name attribute
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the name attribute
     * @param name, player's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the points attribute
     * @return player's points
     */
    public int getPoints() {
        return points;
    }

    /**
     * setter for the points attribute
     * @param points, player's points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * an override of the Comparable class compareTo method,
     * this method compares two players by their points
     * @param o, the second player
     * @return the first player's points minus the second player's point with a negative sign
     * with this compareTo method the players will be in descending order
     */
    @Override
    public int compareTo(Player o) {
        int comparePoints = o.getPoints();
        return -1*(this.points-comparePoints);          //descending order
    }
}
