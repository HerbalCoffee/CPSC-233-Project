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
	
	public int getX() {
		return this.getLocation.getX();
	}
	
	public int getY() {
		return this.getLocation.getY();
	}
	
	public void setY(int y) {
		this.entityLocation.setY(y);
	}
	
	public char getChar() {
		return this.entityAsChar;
	}
	
	/**
	 * Calculates the distance between this entity and otherEntity, using the formula d(P, Q) = √((x2 − x1)^2 + (y2 − y1)^2)
	 *
	 */

	public double distanceFrom(Entity otherEntity) {
		int x1 = this.getX();
		int y1 = this.getY();
		int x2 = otherEntity.getX();
		int y2 = otherEntity.getY();
		double distance = Math.sqrt((Math.pow(((double)(x2 - x1)), 2)) + (Math.pow(((double)(y2 - y1)), 2)));
		return distance;
	}

}
