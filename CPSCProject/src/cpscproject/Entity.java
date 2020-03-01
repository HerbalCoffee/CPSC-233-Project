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
	
	/**
	 * Calculates the distance between this entity and otherEntity, using the formula d(P, Q) = √((x2 − x1)^2 + (y2 − y1)^2)
	 *
	 */

	public double distanceFrom(Entity otherEntity) {
		int x1 = this.getLocation().getX();
		int y1 = this.getLocation().getY();
		int x2 = otherEntity.getLocation().getX();
		int y2 = otherEntity.getLocation().getY();
		double distance = Math.sqrt((Math.pow(((double)(x2 - x1)), 2)) + (Math.pow(((double)(y2 - y1)), 2)));
		return distance;
	}

}
