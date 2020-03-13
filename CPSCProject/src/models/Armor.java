package models;


public class Armor extends Collectible {

    private double protection;
    private double weight;

    /**
     * Creates a new Armor object 
     * 
     * 
     * @param aLoc the Location of the Armor on the map
     * @param c The character representation of the armor
     * @param displayName the display name of the Armor
     * @param protection the amount of protection the Armor has
     * @param weight the weight of the Armor
     */
    public Armor(Location aLoc, char c, String displayName, double protection, double weight) {
        super(aLoc, c, displayName);
        this.protection = protection;
        this.weight = weight;
    }

    /**
     * Returns the amount of protection the current Armor has
     * 
     * @return a double representing the current amount of protection offered by the Armor
     */
    public double getProtection() {
        return this.protection;
    }

    /**
     * Returns the current weight of the Armor
     * 
     * @return a double representing how heavy the Armor is
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Sets a new protection value to the current Armor instance
     * 
     * @param protection_value a double representing the amount of protection to set
     */
    public void setProtection(double protection_value) {
        this.protection = protection_value;
    }

    /**
     * Sets a new weight to the current Armor instance
     * 
     * @param weight a double representing the amount of weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

}
