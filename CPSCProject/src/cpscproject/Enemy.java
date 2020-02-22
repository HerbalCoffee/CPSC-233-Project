package cpscproject;

public class Enemy {

	Location enemyLocation;
	double health;
	double damage;
	double attackChance;
	
	public Enemy(Location enemyLocation, double health, double damage, double attackChance) {
		this.enemyLocation = enemyLocation;
		this.health = health;
		this.damage = damage;
		this.attackChance = attackChance;
	}
	
}
