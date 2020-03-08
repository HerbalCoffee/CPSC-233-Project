package cpscproject;
// Imports all necessary libraries

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

// Creates Map Class 
/**
 *
 *
 * 
 */
public class Map {

    public Entity[][] mapLayout = new Entity[10][15];
    private Exit exit = null;
    public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

    /**
     * Constructor Method that uses the Scanner class to add individual
     * characters from a text file and adds them to the mapLayout instance
     * variable.
     *
     * @param filePath the path to a text file containing a a rectangular map
     * @throws FileNotFoundException
     */
    public Map(String filePath) throws FileNotFoundException {

        Scanner map = new Scanner(new FileReader(filePath));

        // Cycles through each row and column, and adds characters from the text files
        for (int row = 0; row < mapLayout.length; row++) {
            String line = map.nextLine();
            for (int column = 0; column < mapLayout[0].length; column++) {
                char character = line.charAt(column);

                if (character == 'C') {
                    exit = new Exit(new Location(column, row));
                    this.mapLayout[row][column] = new Exit(new Location(column, row));
                } else if (character == 'W') {
                    this.mapLayout[row][column] = new Wall(new Location(column, row));
                }
            }
        }

        // Closes the scanner object
        map.close();

    }

    /**
     * Method that returns the element present in the map, at the given
     * location, as a reference to an Entity object
     *
     * @param location the location where the character is
     * @return the Entity that is contained within the given location
     */
    public Entity getElement(Location location) {
        return mapLayout[location.getY()][location.getX()];
    }

    /**
     * Method that checks whether the inputed location is a Wall, and returns a
     * boolean
     *
     * @param location a Location object indicating the location to check
     * @return a boolean indicating whether the indicated location is a valid
     * move
     */
    public boolean isValidMove(Location location) {

        if (this.mapLayout[location.getY()][location.getX()] instanceof Wall) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Void Method that replaces a location in the map with a given character
     *
     * @param location the location in which the Entity must be replaced
     * @param theEntity the new Entity object to replace at the given location
     */
    public void replaceElement(Location location, Entity theEntity) {
        this.mapLayout[location.getY()][location.getX()] = theEntity;
    }

    /**
     * Getter for the Exit Coordinates
     *
     * @return a Location object representing the location of the exit
     */
    public Entity getExit() {
        return this.exit;
    }

    /**
     * Method that adds an enemy to the list, as well as adds enemy location on
     * the character map
     *
     * @param newEnemy the enemy to add to the map, and the enemyList
     */
    public void addEnemy(Enemy newEnemy) {
        enemyList.add(newEnemy);
    }

    /**
     * Method that removes an enemy from the list, as well as removes the
     * enemy's location from the character map
     *
     * @param newEnemy the enemy to be removed from the map and enemyList
     */
    public void removeEnemy(Enemy newEnemy) {
        enemyList.remove(newEnemy);
    }

    /**
     *  Method that prints the whole Map out to the console
     * 
     */
    public void printMap() {

        for (int row = 0; row < mapLayout.length; row++) {
            for (int column = 0; column < mapLayout[0].length; column++) {
                if (this.mapLayout[row][column] == null) {
                    System.out.print(' ');
                } else {
                    System.out.print(this.mapLayout[row][column].getChar());
                }
            }
            System.out.println();
        }
    }

    /**
     * Returns the reference to the ArrayList of enemies in the current map instance 
     * 
     * @return the reference to the Enemy ArrayList within this Map instance
     */
    public ArrayList<Enemy> getEnemyList(){
        return this.enemyList;
    }

}
