package cpscproject;
import java.util.*;

public class MovableEntity extends Entity {

    // CAN POSSIBLY PUT THESE IN A HASHTABLE LATER ON
    private double health;
    private final double baseHealth; // Initial health used to determine additional health gained by endurance
    private double baseDamage; // Base damage 
    private double accuracy; // Accuracy, determines if an attack hits or misses
    private double defense; // Defense stat, reduces damage taken. Determined by equipment or preset enemy type
    private int endurance; // Health multiplier
    private int strength; // Ability to use equipment without penalties, carrying capacity
    private int dexterity; // Hit chance, dodge chance, crit chance percentage
    private int intelligence; // Magic damage + hit chance, xp gain, perception ability

    public MovableEntity(double health, double baseDamage, int endurance, int strength,
            int dexterity, int intelligence, Location location, char character) {
        super(location, character);
        this.baseHealth = health;
        this.calculateActualHealth();
        this.baseDamage = baseDamage;
        this.endurance = endurance;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }
    
    /**
    * Calculates health using the endurance
    * 
    * Increases health by baseHealth * (endurance/baseHealth)
    */
    public void calculateActualHealth() {
        this.setHealth( this.getHealth() + (this.getBaseHealth() * (this.getEndurance()/this.getBaseHealth())) );
    }

    //COMBAT DAMAGE METHODS
    /**
     * Calculates damage for a certain attack type based on appropriate stat
     * Adds .5 to the multiplier per level of intelligence for magic attacks and multiplies physical damage by 2.5 on critical hits.
     * 
     */
    public double getDamage(String damageType) {
        double damageDealt;
        switch (damageType) {
            case "physical":
                damageDealt = this.baseDamage;
                Random random = new Random();
                int chance = random.nextInt(101); // Generates an integer between 0 and 100 (both inclusive) to compare to crit chance
                if (chance <= this.getDexterity()) {
                    damageDealt *= 2.5;
                }
                break;
            case "magic":
                damageDealt = this.baseDamage + (this.baseDamage * (this.intelligence / 2));
                break;
            default:
                damageDealt = this.baseDamage;
        }
        return damageDealt;
    }

    /*
    * Calculates damage taken and applies damage taken to health
    * Reduces damage by subtracting (damage * 0.(defense)) from the damage taken
    *
     */
    public void takeDamage(double damage) {
        double damageTaken = damage;
        damageTaken = damage - (damage * (0.1 * this.defense));
        this.health -= damageTaken;
    }

    // GETTERS AND SETTERS
    public double getBaseDamage() {
        return this.baseDamage;
    }
    
    public void setBaseDamage(double newDamage) {
        this.baseDamage = newDamage;
    }
    
    public double getDefense() {
        return this.defense;
    }
    
    public void setDefense(double newDefense) {
        this.defense = newDefense;
    }
    
    public double getAccuracy() {
        return this.accuracy;
    }
    
    public void setAccuracy(double newAccuracy) {
        this.accuracy = newAccuracy;
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    
    public double getBaseHealth() {
        return this.baseHealth;
    }

    public int getEndurance() {
        return this.endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public void setDexterity(int dext) {
        this.dexterity = dext;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(int intel) {
        this.intelligence = intel;
    }

    /**
     * FOLLOWING 4 MOVE METHODS Sets the player's location one square in the
     * specified direction in a given map instance
     *
     * @param aMap a map instance
     */
    public void moveUp(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX(), this.getLocation().getY() - 1))) {
            this.setY(this.getLocation().getY() - 1);
            //aMap.mapLayout[this.getLocation().getY()][this.getLocation().getX()] = this;
            aMap.replaceElement(new Location(this.getLocation().getX(), this.getLocation().getY() + 1), null);
        } else {
            System.out.println("Invalid Move!");
        }
    }

    public void moveLeft(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX() - 1, this.getLocation().getY()))) {
            this.setX(this.getLocation().getX() - 1);
            //aMap.mapLayout[this.getLocation().getY()][this.getLocation().getX()] = this;
            aMap.replaceElement(new Location(this.getLocation().getX() + 1, this.getLocation().getY()), null);
        } else {
            System.out.println("Invalid Move!");
        }
    }

    public void moveDown(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX(), this.getLocation().getY() + 1))) {
            this.setY(this.getLocation().getY() + 1);
            //aMap.mapLayout[this.getLocation().getY()][this.getLocation().getX()] = this;
            aMap.replaceElement(new Location(this.getLocation().getX(), this.getLocation().getY() - 1), null);
        } else {
            System.out.println("Invalid Move!");
        }
    }

    public void moveRight(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX() + 1, this.getLocation().getY()))) {
            this.setX(this.getLocation().getX() + 1);
            //aMap.mapLayout[this.getLocation().getY()][this.getLocation().getX()] = this;
            aMap.replaceElement(new Location(this.getLocation().getX() - 1, this.getLocation().getY()), null);
        } else {
            System.out.println("Invalid Move!");
        }
    }
}
