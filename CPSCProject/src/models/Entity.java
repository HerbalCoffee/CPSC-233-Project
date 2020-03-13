package models;

/**
 *
 * @author klarshin
 */
public abstract class Entity {

    private Location entityLocation;
    private char entityAsChar;

    /**
     * The constructor for creating a new Entity instance
     *
     * @param aLoc the Location of the Entity
     * @param c the character representation of the Entity
     */
    public Entity(Location aLoc, char c) {
        this.entityLocation = new Location(aLoc);
        this.entityAsChar = c;
    }

    /**
     * Sets the location of a given entity instance
     * 
     * @param aLoc the new Location of the entity
     */
    public void setLocation(Location aLoc) {
        this.entityLocation = new Location(aLoc);
    }

    /**
     * Returns the location of this Entity instance
     * 
     * @return the current Location of the Entity
     */
    public Location getLocation() {
        return new Location(this.entityLocation);
    }

    /**
     * Sets the x-coordinate (column) of the Entity
     * 
     * @param x the new x-coordinate of the Entity
     */
    public void setX(int x) {
        this.entityLocation.setX(x);
    }

    /**
     * Sets the y-coordinate (row) of the Entity
     * 
     * @param y the new y-coordinate of the Entity
     */
    public void setY(int y) {
        this.entityLocation.setY(y);
    }

    /**
     * Returns the character representation of the current Entity
     * 
     * @return a char representing this Entity
     */
    public char getChar() {
        return this.entityAsChar;
    }

    /**
     * Calculates the distance between this entity and otherEntity, using the
     * formula d(P, Q) = √((x2 − x1)^2 + (y2 − y1)^2)
     *
     * @param otherEntity the Entity to measure the distance to
     * @return a double representing the euclidian distance between the Entities
     */
    public double distanceFrom(Entity otherEntity) {
        int x1 = this.getLocation().getX();
        int y1 = this.getLocation().getY();
        int x2 = otherEntity.getLocation().getX();
        int y2 = otherEntity.getLocation().getY();
        double distance = Math.sqrt((Math.pow(((double) (x2 - x1)), 2)) + (Math.pow(((double) (y2 - y1)), 2)));
        return distance;
    }

}
