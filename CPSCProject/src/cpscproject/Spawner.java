/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpscproject;

/**
 *
 * @author ksl
 */
public class Spawner {
    
    public static void spawnEnemies(Map aMap, int numOfEnemies){
        int numEnemies = 0;
        while (numEnemies < numOfEnemies) {
            Location aLocation = new Location((int) (Math.random() * aMap.mapLayout[0].length), (int) (Math.random() * aMap.mapLayout.length));
            if (aMap.getElement(aLocation) == null) {
                Enemy theEnemy = new Enemy( (int) (Math.random() * 20) + 1, (int) (Math.random() * 10), new Location(aLocation));
                //Add each enemy into the map
                aMap.mapLayout[theEnemy.getLocation().getY()][theEnemy.getLocation().getX()] = theEnemy;
                aMap.addEnemy(theEnemy);
                numEnemies++;
            }
        }
    }
    
    public static Player spawnPlayer(Map aMap){
        boolean spawned = false;
        while (!spawned) {
            Location aLocation = new Location((int) (Math.random() * aMap.mapLayout[0].length), (int) (Math.random() * aMap.mapLayout.length));
            if (aMap.getElement(aLocation) == null) {
                spawned = true;
                //Create a new player instance in the spawned location
                return new Player(50, 10, new Location(aLocation));
            } 
        }
        return null;
    }
    
    public static void spawnConsumable(Map aMap, int numOfItems){
        int numItems = 0;
        while(numItems < numOfItems){
            Location aLocation = new Location((int) (Math.random() * aMap.mapLayout[0].length), (int) (Math.random() * aMap.mapLayout.length));
            if (aMap.getElement(aLocation) == null) {
                Consumable newConsumable = new Consumable(new Location(aLocation),'H',"Health Potion",(Math.random()*((30-10)+1))+10);
                aMap.replaceElement(newConsumable.getLocation(),newConsumable);
                numItems++;
            }
        }
    }

    public static void spawnWeapon(Map aMap, int numOfItems){
        int numItems = 0;
        while(numItems < numOfItems){
            Location aLocation = new Location((int) (Math.random() * aMap.mapLayout[0].length), (int) (Math.random() * aMap.mapLayout.length));
            if (aMap.getElement(aLocation) == null) {
                Weapon newWeapon = new Weapon(new Location(aLocation),'I',"Iron Sword",((Math.random()*((30-10)+1))+10),0.0);
                aMap.replaceElement(newWeapon.getLocation(),newWeapon);
                numItems++;
            }
        }
    }
    
}
