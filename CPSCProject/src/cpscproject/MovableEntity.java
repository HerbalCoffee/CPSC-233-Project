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
    
    
    /**
    * Calculates damage for a certain attack type based on appropriate stat
    * Currently adds .5 to the multiplier per level of stat
    */
    public double getDamage(String damageType) {
        double damageDealt
        switch (damageType) {
            case "melee" :
                damageDealt = this.baseDamage + (this.baseDamage * (this.strength/2));
                break;
            case "ranged" :
                damageDealt = this.baseDamage + (this.baseDamage * (this.dexterity/2));
                break;
            case "magic":
                damageDealt = this.baseDamage + (this.baseDamage * (this.intelligence/2));
                break:
            default:
                damageDealt = this.baseDamage;
                break;
        }
        return damageDealt;
    }
    
    
    // GETTERS AND SETTERS
    
    public void setBaseDamage(double newDamage) { this.baseDamage = newDamage; }

    public double getHealth() { return this.health; }
    public void setHealth(double new) { this.health = new; }
    
    public int getEndurance() { return this.endurance; }
    public int setEndurance(int new) { this.endurance = new; }
    
    public int getStrength() { return this.strength; }
    public int setStrength(int new) { this.strength = new; }
    
    public int getDexterity() { return this.dexterity; }
    public int setDexterity(int new) { this.dexterity = new; }
    
    public int getIntelligence() { return this.intelligence; }
    public int setIntelligence(int new) { this.intelligence = new; }
    

     /** FOLLOWING 4 MOVE METHODS
     * Sets the player's location one square in the specified direction in a given map instance
     *
     * @param aMap a map instance
     */
    public void moveUp(Map aMap) {
        if (aMap.isValidMove(new Location(this.getX(), this.getY() - 1))) {
            this.getLocation().setY(this.getY() - 1);
            aMap.replaceElement(new Location(this.getX(), this.getY() + 1), ' ');
        } else {
            System.out.println("Invalid Move!");
        }
    }
    public void moveLeft(Map aMap) {
        if (aMap.isValidMove(new Location(this.getX() - 1, this.getY()))) {
            this.getLocation().setX(this.getX() - 1);
            aMap.replaceElement(new Location(this.getX() + 1, this.getY()), ' ');
        } else {
            System.out.println("Invalid Move!");
        }
    }
    public void moveDown(Map aMap) {
        if (aMap.isValidMove(new Location(this.getX(), this.getY() + 1))) {
            this.getLocation().setY(this.getY() + 1);
            aMap.replaceElement(new Location(this.getX(), this.getY() - 1), ' ');
        } else {
            System.out.println("Invalid Move!");
        }
    }
    public void moveRight(Map aMap) {
        if (aMap.isValidMove(new Location(this.getX() + 1, this.getY()))) {
            this.getLocation().setX(this.getX() + 1);
            aMap.replaceElement(new Location(this.getX() - 1, this.getY()), ' ');
        } else {
            System.out.println("Invalid Move!");
        }
    }
}
