package cpscproject;
// Imports all necessary libraries
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


// Creates Map Class 
public class Map {

	// Instantiates a 2D array of characters
	char[][] mapLayout = new char[10][15];
	private Location exit;
	ArrayList<Enemy> enemyList;
	
	
	// Constructor Method that uses the Scanner class to add individual characters from a text file and adds
	// them to the mapLayout instance variable.
	public Map(String filePath) throws FileNotFoundException {
		
		Scanner map = new Scanner(new FileReader(filePath));
		
		// Cycles through each row and column, and adds characters from the text files
		for( int row = 0; row < mapLayout.length;row++) {
			String line = map.nextLine();
			for ( int column = 0; column < mapLayout[0].length;column++) {
					char character = line.charAt(column);
					
					/*if (character == 'C') {
						exit.setX(column);
						exit.setY(row);
					}*/
					
					if (character != 'O') {
						this.mapLayout[row][column] = character;
					} else {
						this.mapLayout[row][column] = ' ';
					}
			}
		}		

		// Closes the scanner object
		map.close();
		
	}
	
	// Method that returns the element present in the map, at the given location, as a char
	public char getElement(Location location) {	
		return mapLayout[location.getY()][location.getX()];
	}
	
	// Method that checks whether the inputed location is a Wall, and returns a boolean
	public boolean isValidMove(Location location) {
		
		return this.mapLayout[location.getY()][location.getX()] != 'W';
	
	}
	
	// Void Method that sets the location of the player
	public void setPlayer(Location location) {
		this.mapLayout[location.getY()][location.getX()] = 'X';
	}
	
	// Void Method that sets the location of the enemy
	public void setEnemy(Location location) {
		this.mapLayout[location.getY()][location.getX()] = 'E';
	}
	
	// Void Method that replaces a location in the map with an inputed character
	public void replaceElement(Location location, char charToReplaceWith) {
		this.mapLayout[location.getY()][location.getX()] = charToReplaceWith;
		
	}
	
	// Getter for the Exit Coordinates
	public Location getExit() {
		return this.exit;
	}
	
	// Method that adds an enemy to the list, as well as adds enemy location on the character map
	public void addEnemy(Enemy newEnemy) {
		enemyList.add(newEnemy);
		this.mapLayout[newEnemy.getLocation().getY()][newEnemy.getLocation().getX()] = 'E';
	}
	
	// Method that removes an enemy to the list, as well as adds enemy location on the character map
	public void removeEnemy(Enemy newEnemy) {
		enemyList.remove(newEnemy);
		this.mapLayout[newEnemy.getLocation().getY()][newEnemy.getLocation().getX()] = ' ';
	}
	
}
