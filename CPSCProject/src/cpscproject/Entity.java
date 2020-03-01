package cpscproject;

public class Entity {
	
	private Location entityLocation;
	private char entityAsChar;
	
	public Entity(Location aLoc, char c) {
		this.entityLocation = new Location(aLoc);
		this.entityAsChar = c;
	}
	
	public void setLocation(Location aLoc) {
		this.entityLocation = new Location(aLoc);
	}
	
	public Location getLocation() { return new Location(this.entityLocation);
		
	}
	
	public void setX(int x) {
		this.entityLocation.setX(x);
	}
	
	public void setY(int y) {
		this.entityLocation.setY(y);
	}
	
	public char getChar() {
		return this.entityAsChar;
	}
	

}
