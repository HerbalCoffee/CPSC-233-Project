package models;

/**
 *
 * @author klarshin
 */
public class Consumable extends Collectible {

    private double restoreAmount;

    /**
     * Creates a new Consumable object
     * 
     * @param aLoc the Location of the Consumable on the map
     * @param c the character representation of the consumable
     * @param displayName the display name of the consumable
     * @param restoreAmount the amount of health, damage, etc that the consumable will restore
     */
    public Consumable(Location aLoc, char c, String displayName, double restoreAmount) {
        super(aLoc, c, displayName);
        this.restoreAmount = restoreAmount;
    }

    /**
     * Returns the restore amount of the current Consumable instance
     *
     * @return a double representing the amount that this consumable is able to restore
     */
    public double getRestoreAmount() {
        return this.restoreAmount;
    }

    /**
     * Sets a new restore amount to the current Consumable instance
     * 
     * @param restoreAmount the new amount of restoration to set to the Consumable
     */
    public void setRestoreAmount(double restoreAmount) {
        this.restoreAmount = restoreAmount;
    }
}
