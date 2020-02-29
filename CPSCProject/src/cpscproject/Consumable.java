package cpscproject;

public class Consumable extends Collectible {

    private double restoreAmount;

    public Consumable(Location aLoc, char c, String displayName, double restoreAmount) {
        super(aLoc, c, displayName);
        this.restoreAmount = restoreAmount;
    }

    public double getRestoreAmount() {
        return this.restoreAmount;
    }

    public void setRestoreAmount(double restoreAmount) {
        this.restoreAmount = restoreAmount;
    }
}
