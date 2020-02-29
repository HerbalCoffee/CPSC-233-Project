package cpscproject;

public class Weapon extends Collectible {

    private double weaponDamage;
    private double weight;

    public Weapon(Location aLoc, char c, String displayName, double weaponDamage, double weight) {
        super(aLoc, c, displayName);
        this.weaponDamage = weaponDamage;
        this.weight = weight;
    }

    public double getWeaponDamage() {
        return this.weaponDamage;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWeaponDamage(double weaponDamage) {
        this.weaponDamage = weaponDamage;
    }
}
