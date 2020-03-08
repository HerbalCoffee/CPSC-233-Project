package cpscproject;

public class Wall extends Entity {

    /**
     * Creates a new Wall object
     *
     * @param wallLocation the Location of the Wall object on the Map
     */
    public Wall(Location wallLocation){
        super(wallLocation, 'W');
    }
}
