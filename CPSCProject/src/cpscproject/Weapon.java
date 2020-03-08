package cpscproject;


public class Weapon extends Collectible {

    private double weaponDamage;
    private double weight;

    /**
     * Creates a new Weapon instance
     * 
     * @param aLoc the Location of the weapon on the Map
     * @param c the character representation of the weapon
     * @param displayName the display name of the weapon
     * @param weaponDamage the amount of damage the weapon can inflict
     * @param weight the weight of the weapon
     */
    public Weapon(Location aLoc, char c, String displayName, double weaponDamage, double weight) {
        super(aLoc, c, displayName);
        this.weaponDamage = weaponDamage;
        this.weight = weight;
    }

    /**
     * Returns the amount of damage that the current weapon can inflict
     * 
     * @return a double representing the amount of damage the weapon can deal
     */
    public double getWeaponDamage() {
        return this.weaponDamage;
    }

    /**
     * Returns the amount of weight of the current weapon
     *
     * @return a double representing the amount of weight the weapon has
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Sets the new weight of the current Weapon instance
     * 
     * @param weight a double representing the new weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Sets the new damage amount to the current Weapon instance
     *
     * @param weaponDamage a double representing the amount of damage to set
     */
    public void setWeaponDamage(double weaponDamage) {
        this.weaponDamage = weaponDamage;
    }
}
