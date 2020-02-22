package cpscproject;

public class Location {
	
	// Could alternatively change  x  and y to row and column if that's easier to understand. 
	// With this format, the x coordinate is the column and the  y coordinate is the row.
	 
	private int x;
	private int y;
        
        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public Location(Location aLoc){
            this.x = aLoc.getX();
            this.y = aLoc.getY();
        }
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
