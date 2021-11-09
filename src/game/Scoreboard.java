package game;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * a class for the scoreboard
 */
public class Scoreboard implements SizeManager, Serializable  {
    /**
     * the top10 list where the players will be stored with their name and their points
     */
    private List<Player> top10;

    /**
     * the constructor of the class
     */
    public Scoreboard() {
        //read(FILENAME);
        top10  = new ArrayList<Player>();
    }

    /**
     * this method checks if the player is in the top 10, and adds him if he is.
     * It sorts the list in a descending order.
     * @param p, Player to be added to the top 10 list
     */
    public void addElement(Player p) {
        if (top10.size() < 10) {
            top10.add(p);
        }
        else {
            insert(p);
        }
        sort();
        write(FILENAME);
    }

    /**
     * getter of the top10 list
     * @return top10 list
     */
    public List<Player> getTop10() {
        return top10;
    }

    /**
     * sort the list in descending order
     */
    public void sort() {
        Collections.sort(top10);
    }

    /**
     * insert the 'p' player in the list and remove the 11th item.
     * @param p, player to be inserted
     */
    public void insert(Player p) {

        for(int i=0;i<top10.size();i++) {
            if(top10.get(i).getPoints() <= p.getPoints()) {
                top10.add(i,p);
                top10.remove(10);
                break;
            }
        }
    }

    /**
     * serialize the list to the file which name is given as parameter
     * @param filename, the name of the file where the list will be saved
     */
    public void write(String filename) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(top10);
            oos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deserialize the list from the file which name is given as parameter
     * @param filename, the name of the file which contains the list
     */
    public void read(String filename) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            top10 = (ArrayList<Player>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
