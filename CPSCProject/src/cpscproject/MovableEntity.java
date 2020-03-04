package cpscproject;

public class MovableEntity extends Entity {

    // CAN POSSIBLY PUT THESE IN A HASHTABLE LATER ON
    private double health;
    private double baseDamage; // Base damage 
    private int endurance; // Health multiplier, damage reduction
    private int strength; // Melee damage + hit chance, carrying capacity
    private int dexterity; // Ranged damage + hit chance, dodge chance
    private int intelligence; // Magic damage + hit chance, xp gain, perception ability

    public MovableEntity(double health, double baseDamage, int endurance, int strength,
            int dexterity, int intelligence, Location location, char character) {
        super(location, character);
        this.health = health;
        this.baseDamage = baseDamage;
        this.endurance = endurance;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    //COMBAT DAMAGE METHODS
    /**
     * Calculates damage for a certain attack type based on appropriate stat
     * Currently adds .5 to the multiplier per level of stat
     */
    public double getDamage(String damageType) {
        double damageDealt;
        switch (damageType) {
            case "melee":
                damageDealt = this.baseDamage + (this.baseDamage * (this.strength / 2));
                break;
            case "ranged":
                damageDealt = this.baseDamage + (this.baseDamage * (this.dexterity / 2));
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
    * Reduces damage by subtracting (damage * 0.(endurance)) from the damage taken
    *
     */
    public void takeDamage(double damage) {
        double damageTaken = damage;
        damageTaken = damage - (damage * (0.1 * this.endurance));
        this.health -= damageTaken;
    }

    // GETTERS AND SETTERS
    public void setBaseDamage(double newDamage) {
        this.baseDamage = newDamage;
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
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
