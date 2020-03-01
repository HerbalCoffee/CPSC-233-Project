package cpscproject;

public class MovableEntity extends Entity {

    private double Health;
    private double Damage;

    public MovableEntity(double Health, double Damage, Location location, char character) {
        super(location, character);
        this.Health = Health;
        this.Damage = Damage;
    }

    public void moveUp(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX(), this.getLocation().getY() - 1))) {
            this.getLocation().setY(this.getLocation().getY() - 1);
            aMap.replaceElement(new Location(this.getLocation().getX(), this.getLocation().getY() + 1), ' ');
        } else {
            System.out.println("Invalid Move!");
        }

    }

    /**
     * Sets the player's location one square left in a given map instance
     *
     * @param aMap a map instance
     */
    public void moveLeft(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX() - 1, this.getLocation().getY()))) {
            this.getLocation().setX(this.getLocation().getX() - 1);
            aMap.replaceElement(new Location(this.getLocation().getX() + 1, this.getLocation().getY()), ' ');
        } else {
            System.out.println("Invalid Move!");
        }

    }

    /**
     * Sets the player's location one square down in a given map instance
     *
     * @param aMap a map instance
     */
    public void moveDown(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX(), this.getLocation().getY() + 1))) {
            this.getLocation().setY(this.getLocation().getY() + 1);
            aMap.replaceElement(new Location(this.getLocation().getX(), this.getLocation().getY() - 1), ' ');
        } else {
            System.out.println("Invalid Move!");
        }

    }

    /**
     * Sets the player's location one square right in a given map instance
     *
     * @param aMap a map instance
     */
    public void moveRight(Map aMap) {
        if (aMap.isValidMove(new Location(this.getLocation().getX() + 1, this.getLocation().getY()))) {
            this.getLocation().setX(this.getLocation().getX() + 1);
            aMap.replaceElement(new Location(this.getLocation().getX() - 1, this.getLocation().getY()), ' ');
        } else {
            System.out.println("Invalid Move!");
        }

    }

    /**
     * Adds a given amount to the player's health
     * Ensures health does not exceed 100
     *
     * @param healthToAdd an integer representing the amount of health to give the player
     */
    public void setHealth(double newHealth) {this.Health = newHealth;}

    /**
     * Returns the amount of health the player currently has
     *
     * @return an integer representing the player's current health level
     */
    public double getHealth() {return this.Health;}

    public void setDamage(double newDamage){this.Damage = newDamage;}

    public double getDamage(){return this.Damage}

}