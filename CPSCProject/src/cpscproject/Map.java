package cpscproject;


// Imports all necessary libraries
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;


// Creates Map Class 
public class Map {

	// Instantiates a 2D array of characters
	char[][] mapLayout = new char[10][10];
	
	
	// Constructor Method that uses the Scanner class to add individual characters from a text file and adds
	// them to the mapLayout instance variable.
	public Map(String filePath) throws FileNotFoundException {
		
		Scanner map = new Scanner(new FileReader(filePath));
		
		// Cycles through each row and column, and adds characters from the text files
		for( int row = 0; row < mapLayout.length;row++) {
			String line = map.nextLine();
			for ( int column = 0; column < mapLayout[0].length;column++) {
					char character = line.charAt(column);
					this.mapLayout[column][row] = character;
			}
		}		

		// Closes the scanner object
		map.close();
		
	}
	
	// Method that creates a copy of the mapLayout instance variable, and then returns the char 
	// present at the specified location. Returns a char
	public char getElement(int row, int column) {
		char[][] mapLayoutCopy = new char[this.mapLayout.length][this.mapLayout[0].length];
		for( int indexRow = 0; indexRow < mapLayout.length;indexRow++) {
			for ( int indexColumn = 0; indexColumn < mapLayout[0].length;indexColumn++) {
				mapLayoutCopy[indexRow][indexColumn] = this.mapLayout[indexRow][indexColumn];
			}
		}	
		return mapLayoutCopy[row][column];
	}
	
	// Method that checks whether the inputed location is a Wall, and returns a boolean
	public boolean isValidMove(int row, int column) {
		
		return this.mapLayout[row][column] != 'W';
	
	}
	
	// Void Method that sets the location of the player
	public void setPlayer(int row, int column) {
		this.mapLayout[row][column] = 'X';
	}
	
	// Void Method that replaces a location in the map with an inputted character
	public void replaceElement(int row, int column, char charToReplaceWith) {
		this.mapLayout[row][column] = charToReplaceWith;
		
	}
	
}
