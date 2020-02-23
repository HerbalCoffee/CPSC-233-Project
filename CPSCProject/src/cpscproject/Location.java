package cpscproject;

//Location Class that is used to locate all enemy and player objects
public class Location {
	
	//Instance variables for X and Y Coordinates
	private int x;
	private int y;
        
    //Constructor that takes x and y coordinates as ints    
	public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    
	//Copy Constructor
    public Location(Location aLoc){
            this.x = aLoc.getX();
            this.y = aLoc.getY();
        }
	
	
	
	//Getter for Location X-Coordinate
	public int getX() {
		return x;
	}
	
	//Getter for Location Y-Coordinate
	public int getY() {
		return y;
	}
	
	//Setter for Location X-Coordinate
	public void setX(int x) {
		this.x = x;
	}

	//setter for Location Y-Coordinate
	public void setY(int y) {
		this.y = y;
	}
}
