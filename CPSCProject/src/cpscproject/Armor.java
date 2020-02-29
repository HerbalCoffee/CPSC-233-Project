package cpscproject;

public class Armor extends Collectible {

    private double protection;
    private double weight;

    public Armor(Location aLoc, char c, String displayName, double protection, double weight) {
        super(aLoc, c, displayName);
        this.protection = protection;
        this.weight = weight;
    }

    public double getProtection() {
        return this.protection;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setProtection(double protection_value) {
        this.protection = protection_value;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
