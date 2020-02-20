
// Imports all necessary libraries
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;


// Creates Map Class 
public class Map {

	// Instantiates a 2D array of characters
	char[][] mapLayout = new char[10][15];
	private int exitX;
	private int exitY;
	
	
	// Constructor Method that uses the Scanner class to add individual characters from a text file and adds
	// them to the mapLayout instance variable.
	public Map(String filePath) throws FileNotFoundException {
		
		Scanner map = new Scanner(new FileReader(filePath));
		
		// Cycles through each row and column, and adds characters from the text files
		for( int row = 0; row < mapLayout.length;row++) {
			String line = map.nextLine();
			for ( int column = 0; column < mapLayout[0].length;column++) {
					char character = line.charAt(column);
					
					if (character == 'C') {
						this.exitX = column;
						this.exitY = row;
					}
					
					if (character != 'O') {
						this.mapLayout[column][row] = character;
					} else {
						this.mapLayout[column][row] = ' ';
					}
			}
		}		

		// Closes the scanner object
		map.close();
		
	}
	
	// Method that returns the element present in the map, at the given location, as a char
	public char getElement(int row, int column) {	
		return mapLayout[row][column];
	}
	
	// Method that checks whether the inputed location is a Wall, and returns a boolean
	public boolean isValidMove(int row, int column) {
		
		return this.mapLayout[row][column] != 'W';
	
	}
	
	// Void Method that sets the location of the player
	public void setPlayer(int row, int column) {
		this.mapLayout[row][column] = 'X';
	}
	
	// Void Method that replaces a location in the map with an inputed character
	public void replaceElement(int row, int column, char charToReplaceWith) {
		this.mapLayout[row][column] = charToReplaceWith;
		
	}
	
	// Getter for the Exit Coordinates
	public int getExitX() {
		return this.exitX;
	}
	
	public int getExitY() {
		return this.exitY;
	}
}
