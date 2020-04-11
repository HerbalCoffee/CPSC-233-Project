package models;

//Creates an enemy class

/**
 *
 * 
 * @author klarshin
 */
public class Enemy extends MovableEntity {
    /**
     * Constructor that defines all necessary enemy instance variables
     *
     * @param enemyLocation the Location object representing the enemy's location
     * @param baseDamage the base amount of damage an enemy can inflict
     * @param health the amount of health the enemy has
     */
	public Enemy(double health, double baseDamage, Location enemyLocation) {
		super(health, baseDamage, 0, 0, 0, 0, enemyLocation, 'E');
	}
}
