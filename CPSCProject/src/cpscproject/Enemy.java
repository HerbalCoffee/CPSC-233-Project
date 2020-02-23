package cpscproject;

//Creates an enemy class

/**
 *
 * 
 */
public class Enemy {

	//Instance variables for enemy location, health and damage
	private Location enemyLocation;
	private double health;
	private double damage;
	
	

    /**
     * Constructor that defines all necessary enemy instance variables
     *
     * @param enemyLocation the Location object representing the enemy's location
     * @param health the amount of health the enemy has
     * @param damage the amount of damage an enemy can inflict
     */
	public Enemy(Location enemyLocation, double health, double damage) {
		this.enemyLocation = enemyLocation;
		this.health = health;
		this.damage = damage;
	}
	
	

    /**
     * Returns the damage done by an Enemy
     * 
     * @return a double representing the amount of damage done by an enemy
     */
	public double doesDamage() {
		return damage;
	}
	
	

    /**
     * Modifies the health value of an enemy, based on the damage that is inflicted upon it
     * 
     * @param damageInflicted a double representing the amount of damage an enemy will take
     */
	public void getDamaged(double damageInflicted) {
		this.health = this.health - damageInflicted;
	}
	
	

    /**
     * Getter for Enemy Location
     *
     * @return the Location of the enemy
     */
	public Location getLocation() {
		return new Location(enemyLocation);
	}
	
	//

    /**
     * Getter for enemy health
     * 
     * @return a double representing the enemy's current health level
     */
	public double getHealth() {
		return this.health;
	}
	
	//

    /**
     * Sets the enemy's location, and places its character representation on the map
     * 
     * @param theMap the map object that the enemy will be placed on
     * @param newLocation the Location object representing the new position of the enemy
     */
	public void setLocation(Map theMap, Location newLocation) {
		theMap.mapLayout[this.enemyLocation.getY()][this.enemyLocation.getX()] = ' ';
		this.enemyLocation = newLocation;
		theMap.mapLayout[this.enemyLocation.getY()][this.enemyLocation.getX()] = 'E';
	}
        
        
}
