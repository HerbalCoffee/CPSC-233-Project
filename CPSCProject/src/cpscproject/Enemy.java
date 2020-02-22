package cpscproject;

public class Enemy {

	Location enemyLocation;
	double health;
	double damage;
	
	public Enemy(Location enemyLocation, double health, double damage) {
		this.enemyLocation = enemyLocation;
		this.health = health;
		this.damage = damage;
	}
	
	public double doesDamage() {
		return damage;
	}
	
	public void getDamaged(double damageInflicted) {
		this.health = this.health - damageInflicted;
	}
        
        
}
