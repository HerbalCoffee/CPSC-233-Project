package cpscproject;

public class Spawner {
    
    /**
     * Static method for selecting a random location on a map
     *
     * @param aMap the Map object to determine the Location from
     * @return a Location object representing the randomly-selected location
     */
    public static Location randomLocation(Map aMap) {
         return new Location((int) (Math.random() * aMap.mapLayout[0].length), (int) (Math.random() * aMap.mapLayout.length));
    }
    
    /**
     * Spawns a given number of enemies onto the given Map
     *
     * @param aMap the Map instance to spawn enemies on
     * @param numOfEnemies an integer number of enemies to spawn on the map 
     */
    public static void spawnEnemies(Map aMap, int numOfEnemies){
        int numEnemies = 0;
        while (numEnemies < numOfEnemies) {
            Location aLocation = Spawner.randomLocation(aMap);
            if (aMap.getElement(aLocation) == null) {
                Enemy theEnemy = new Enemy( (int) (Math.random() * 20) + 1, (int) (Math.random() * 10), new Location(aLocation));
                //Add each enemy into the map
                aMap.mapLayout[theEnemy.getLocation().getY()][theEnemy.getLocation().getX()] = theEnemy;
                aMap.addEnemy(theEnemy);
                numEnemies++;
            }
        }
    }
    
    /**
     * Spawns and returns a Player instance on a given Map
     *
     * @param aMap the Map to spawn the Player onto
     * @return the spawned Player instance
     */
    public static Player spawnPlayer(Map aMap){
        boolean spawned = false;
        while (!spawned) {
            Location aLocation = Spawner.randomLocation(aMap);
            if (aMap.getElement(aLocation) == null) {
                spawned = true;
                //Create a new player instance in the spawned location
                return new Player(50, 10, new Location(aLocation));
            } 
        }
        return null;
    }
    
    /**
     * Spawns a given number of consumables onto a given map.
     * (Currently only spawns one type of consumable)
     *
     * @param aMap the Map object to spawn consumables onto
     * @param numOfItems the number of consumables to spawn
     */
    public static void spawnConsumable(Map aMap, int numOfItems){
        int numItems = 0;
        while(numItems < numOfItems){
            Location aLocation = Spawner.randomLocation(aMap);
            if (aMap.getElement(aLocation) == null) {
                Consumable newConsumable = new Consumable(new Location(aLocation),'H',"Health Potion",(Math.random()*((30-10)+1))+10);
                aMap.replaceElement(newConsumable.getLocation(),newConsumable);
                numItems++;
            }
        }
    }

    /**
     * Spawns a given number of weapons onto a given map instance.
     * (Currently only spawns Iron Swords)
     *
     * @param aMap the Map object to spawn weapons onto
     * @param numOfItems the number of Weapons to spawn on the Map
     */
    public static void spawnWeapon(Map aMap, int numOfItems){
        int numItems = 0;
        while(numItems < numOfItems){
            Location aLocation = Spawner.randomLocation(aMap);
            if (aMap.getElement(aLocation) == null) {
                Weapon newWeapon = new Weapon(new Location(aLocation),'I',"Iron Sword",((Math.random()*((30-10)+1))+10),0.0);
                aMap.replaceElement(newWeapon.getLocation(),newWeapon);
                numItems++;
            }
        }
    }
    
}
