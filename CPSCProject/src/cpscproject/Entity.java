package cpscproject;

public class Entity {
	
	private Location entityLoc;
	private char entityAsChar;
	
	public Entity(Location aLoc, char c) {
		this.entityLoc = new Location(aLoc);
		this.entityAsChar = c;
	}
	
	public void setLocation(Location aLoc) {
		this.entityLoc = new Location(aLoc);
	}
	
	public Location getLocation() {
		return new Location(this.entityLoc);
		
	}
	
	public void setX(int x) {
		this.entityLoc.setX(x);
	}
	
	public void setY(int y) {
		this.entityLoc.setY(y);
	}
	
	public char getChar() {
		return this.entityAsChar;
	}
	

}
