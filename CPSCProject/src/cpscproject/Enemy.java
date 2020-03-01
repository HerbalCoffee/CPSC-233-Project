package cpscproject;

//Creates an enemy class

/**
 *
 * 
 */
public class Enemy extends MovableEntity {
    /**
     * Constructor that defines all necessary enemy instance variables
     *
     * @param enemyLocation the Location object representing the enemy's location
     * @param health the amount of health the enemy has
     * @param damage the amount of damage an enemy can inflict
     */
	public Enemy(double health, double baseDamage, Location enemyLocation) {
		super(health, baseDamage, 0, 0, 0, 0, enemyLocation, 'E');
	}
}
