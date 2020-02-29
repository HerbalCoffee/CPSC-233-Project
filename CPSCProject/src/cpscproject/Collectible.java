package cpscproject;

//Collectible class that is used to store collectible data

/**
 *
 * 
 */
public class Collectible extends Entity {

    private String displayName;

    public Collectible(Location aLoc, char c, String displayName) {
        super(aLoc, c);
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
