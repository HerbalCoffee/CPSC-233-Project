package models;


public class Exit extends Entity {

    /**
     * Create a new Exit object
     *
     * @param exitLoc the Location object representing the exit of the current stage
     */
    public Exit(Location exitLoc){
        super(exitLoc, 'C');
    }
}
