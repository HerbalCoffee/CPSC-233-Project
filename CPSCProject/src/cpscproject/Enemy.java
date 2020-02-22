package cpscproject;

//Creates an enemy class
public class Enemy {

	//Instance variables for enemy location, health and damage
	Location enemyLocation;
	double health;
	double damage;
	
	//Constructor that defines all neccassry enemy instance variables 
	public Enemy(Location enemyLocation, double health, double damage) {
		this.enemyLocation = enemyLocation;
		this.health = health;
		this.damage = damage;
	}
	
	//Returns the damage done by an Enemy
	public double doesDamage() {
		return damage;
	}
	
	//Modifies the health value of an enemy, based on the damage that is inflicted upon it
	public void getDamaged(double damageInflicted) {
		this.health = this.health - damageInflicted;
	}
	
	//Getter for Enemy Location
	public Location getLocation() {
		return new Location(enemyLocation);
	}
	
	//Getter for enemy Health
	public double getHealth() {
		return this.health;
	}
	
	//Setter for Enemy Location and on the Map
	public void setLocation(Map theMap, Location newLocation) {
		theMap.mapLayout[this.enemyLocation.getY()][this.enemyLocation.getX()] = ' ';
		this.enemyLocation = newLocation;
		theMap.mapLayout[this.enemyLocation.getY()][this.enemyLocation.getX()] = 'E';
	}
        
        
}
