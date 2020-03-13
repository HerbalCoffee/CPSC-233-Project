package models;

//Location Class that is used to locate all enemy and player objects

/**
 *
 * 
 */
public class Location {
	
	//Instance variables for X and Y Coordinates
	private int x;
	private int y;
        

    /**
     * Constructor that takes x and y coordinates as integers
     *
     * @param x an integer representing the x-coordinate
     * @param y an integer representing the y-coordinate
     */
	public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    

    /**
     * Copy Constructor
     * 
     * @param aLoc the Location instance to be copied
     */
        public Location(Location aLoc){
            this.x = aLoc.getX();
            this.y = aLoc.getY();
        }
	
	
	

    /**
     * Getter for Location X-Coordinate
     *
     * @return the integer representing the x-coordinate
     */
	public int getX() {
		return x;
	}
	

    /**
     * Getter for Location Y-Coordinate
     *
     * @return an integer representing the location y-coordinate
     */
	public int getY() {
		return y;
	}
	

    /**
     * Setter for Location X-Coordinate
     *
     * @param x the integer representing the x-coordinate to set
     */
	public void setX(int x) {
		this.x = x;
	}


    /**
     * Setter for Location Y-Coordinate
     *
     * @param y an integer representing the y-coordinate to set
     */
	public void setY(int y) {
		this.y = y;
	}
}
