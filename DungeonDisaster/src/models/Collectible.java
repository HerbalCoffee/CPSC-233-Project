package models;

//Collectible class that is used to store collectible data

/**
 *
 * 
 * 
 */
public class Collectible extends Entity {

    private String displayName;

    /**
     * Creates a new Collectible object 
     * 
     * @param aLoc the Location of the collectible on the map
     * @param c the character representation of the collectible
     * @param displayName the display name of the collectible
     */
    public Collectible(Location aLoc, char c, String displayName) {
        super(aLoc, c);
        this.displayName = displayName;
    }

    /**
     * Method that returns the display name of the collectible
     * 
     * @return the string representing the display name of the collectible
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Method that sets a new display name to the current collectible instance
     * 
     * @param displayName the string to set the new display name to
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
