package models;

import java.util.*;

public class MovableEntity extends Entity {

    // CAN POSSIBLY PUT THESE IN A HASHTABLE LATER ON
    private double health;
    private final double baseHealth; // Initial health used to determine additional health gained by endurance
    private double baseDamage = 0; // Base damage
    private double accuracy = 0; // Accuracy, determines if an attack hits or misses NOT CURRENTLY USED
    private double defense = 0; // Defense stat, reduces damage taken. Determined by equipment or preset enemy type
    private int endurance = 0; // Health multiplier
    private int strength = 0; // Ability to use equipment without penalties, carrying capacity
    private int dexterity = 0; // Hit chance, dodge chance, crit chance percentage
    private int intelligence = 0; // Magic damage + hit chance, xp gain, perception ability

    /**
     * Creates a new MovableEntity object.
     *
     * Default values for stats are 0 and 100 for health.
     * Does not use given values if value is less than 0
     *
     * @param health the amount of health the Movable Entity has
     * @param baseDamage the base amount of damage the MovableEntity can inflict
     * @param endurance the amount of endurance the MovableEntity has 
     * @param strength the strength of the MovableEntity
     * @param dexterity the amount of dexterity the MovableEntity has
     * @param intelligence the amount of intelligence the MovableEntity has
     * @param location the location of the MovableEntity
     * @param character the character representation of the MovableEntity
     */
    public MovableEntity(double health, double baseDamage, int endurance, int strength,
            int dexterity, int intelligence, Location location, char character) {
        super(location, character);
        if (baseDamage >= 0) {
            this.baseDamage = baseDamage;
        }
        if (endurance >= 0) {
            this.endurance = endurance;
        }
        if (strength >= 0) {
            this.strength = strength;
        }
        if (dexterity >= 0) {
            this.dexterity = dexterity;
        }
        if (intelligence >= 0) {
            this.intelligence = intelligence;
        }
        
        if (health >= 0) {
            this.baseHealth = health;
        } else {
            this.baseHealth = 100;
        }
        if (health >= 0) {
            this.health = health;
            this.calculateActualHealth();
        } else {
            this.health = this.getBaseHealth();
            this.calculateActualHealth();
        }
    }

    /**
     * Calculates health using the endurance.
     *
     * Increases health by baseHealth * (endurance/baseHealth)
     */
    public void calculateActualHealth() {
        this.setHealth(this.getHealth() + (this.getBaseHealth() * (this.getEndurance() / this.getBaseHealth())));
    }

    //COMBAT DAMAGE METHODS
    /**
     * Calculates damage for a certain attack type based on appropriate stats.
     * 
     * Adds .5 to the multiplier per level of intelligence for magic attacks and 
     * multiplies physical damage by 2.5 on critical hits.
     *
     * @param damageType the string representing the damage type ("physical", "magic", or default) to get
     * @return a non negative double representing the amount of damage inflicted by the specified attack
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
        if (damageDealt < 0) {
            damageDealt = 0.0;
        }
        return damageDealt;
    }

    /**
     * Calculates damage taken and applies damage taken to health.
     * Reduces damage by subtracting (damage * 0.(defense)) from the damage taken
     *
     * @param damage the amount of damage to inflict on to the MovableEntity
     */
    public void takeDamage(double damage) {
        double damageTaken = damage;
        damageTaken = damage - (damage * (0.1 * this.defense));
        if (damage < 0) {
            damageTaken = 0;
        }
        this.health -= damageTaken;
    }

    /**
     * Gets the base amount of damage of the MovableEntity
     * 
     * @return a double representing the amount of base damage of the MovableEntity
     */
    public double getBaseDamage() {
        return this.baseDamage;
    }

    /**
     * Sets the MovableEntity's base damage amount
     * 
     * @param newDamage the new double amount of base damage to set
     */
    public void setBaseDamage(double newDamage) {
        if (newDamage >= 0) {
            this.baseDamage = newDamage;
        }
    }

    /**
     * Gets the amount of defense the MovableEntity has
     * 
     * @return a double representing the current amount of defense
     */
    public double getDefense() {
        return this.defense;
    }

    /**
     * Sets the amount of defense the MovableEntity has
     * 
     * @param newDefense a double representing the new amount of defense to set
     */
    public void setDefense(double newDefense) {
        if (newDefense >= 0) {
            this.defense = newDefense;
        }
    }

    /**
     * Gets the attack accuracy of the current MovableEntity
     * 
     * @return a double representing the accuracy of the current MovableEntity
     */
    public double getAccuracy() {
        return this.accuracy;
    }

    /**
     * Sets a new attack accuracy to the current MovableEntity
     * 
     * @param newAccuracy a double representing the new attack accuracy to set
     */
    public void setAccuracy(double newAccuracy) {
        if (newAccuracy >= 0) {
            this.accuracy = newAccuracy;
        }
    }

    /**
     * Gets the current amount of health
     *
     * @return a double representing the MovableEntity's current health level
     */
    public double getHealth() {
        return this.health;
    }

    /**
     * Sets a new health level to the current MovableEntity
     *
     * @param health a double representing the new level of health to set
     */
    public void setHealth(double health) {
        if (health >= 0) {
            this.health = health;
        } else {
            this.health = 0;
        }
    }

    /**
     * Gets the amount of base health belonging to the current MovableEntity
     *
     * @return the double amount of base health of the MovableEnity
     */
    public double getBaseHealth() {
        return this.baseHealth;
    }

    /**
     * Gets the endurance of the current MovableEntity
     *
     * @return an integer representing the MovableEntity's endurance
     */
    public int getEndurance() {
        return this.endurance;
    }

    /**
     * Sets a new amount of endurance to the current MovableEntity
     *
     * @param endurance an integer representing a new endurance amount to set
     */
    public void setEndurance(int endurance) {
        if (endurance >= 0) {
            this.endurance = endurance;
        }
    }

    /**
     * Gets the current MovableEntity's strength
     *
     * @return an integer representing the strength of the current MovableEntity
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * Sets a new amount of strength to the current MovableEntity
     * 
     * @param strength an integer representing a new strength to set
     */
    public void setStrength(int strength) {
        if (strength >= 0) {
            this.strength = strength;
        }
    }

    /**
     * Gets the amount of dexterity of the current MovableEntity
     *
     * @return an integer representing the current dexterity
     */
    public int getDexterity() {
        return this.dexterity;
    }

    /**
     * Sets a new amount of dexterity to the current MovableEntity instance
     *
     * @param dexterity an integer representing the new dexterity to set
     */
    public void setDexterity(int dexterity) {
        if (dexterity >= 0) {
            this.dexterity = dexterity;
        }
    }

    /**
     * Gets the current amount of intelligence of the current MovableEntity
     * 
     * @return an integer representing the current Intelligence
     */
    public int getIntelligence() {
        return this.intelligence;
    }

    /**
     * Sets a new intelligence amount to the current MovableEntity
     *
     * @param intelligence an integer representing the new intelligence amount to set
     */
    public void setIntelligence(int intelligence) {
        if (intelligence >= 0) {
            this.intelligence = intelligence;
        }
    }

    /**
     * Sets the MovableEntity's location one square up in a given map instance
     * 
     * @param aMap a map instance
     */
    public boolean moveUp(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX(), this.getLocation().getY() - 1))) {
            this.setY(this.getLocation().getY() - 1);
            aMap.replaceElement(new Location(this.getLocation().getX(), this.getLocation().getY() + 1), null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the MovableEntity's location one square left in a given map instance
     * 
     * @param aMap a map instance
     */
    public boolean moveLeft(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX() - 1, this.getLocation().getY()))) {
            this.setX(this.getLocation().getX() - 1);
            aMap.replaceElement(new Location(this.getLocation().getX() + 1, this.getLocation().getY()), null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the MovableEntity's location one square down in a given map instance
     * 
     * @param aMap a map instance
     */
    public boolean moveDown(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX(), this.getLocation().getY() + 1))) {
            this.setY(this.getLocation().getY() + 1);
            aMap.replaceElement(new Location(this.getLocation().getX(), this.getLocation().getY() - 1), null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the MovableEntity's location one square right in a given map instance
     * 
     * @param aMap a map instance
     */
    public boolean moveRight(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX() + 1, this.getLocation().getY()))) {
            this.setX(this.getLocation().getX() + 1);
            aMap.replaceElement(new Location(this.getLocation().getX() - 1, this.getLocation().getY()), null);
            return true;
        } else {
            return false;
        }
    }
}
