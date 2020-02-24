/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpscproject;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author klarshin
 */
public class TextApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
         
        //Declare new map instance
        Map theMap = new Map(TextApp.class.getResource("Map1.txt").getPath());

        //(Temporarily) create variables for player x and y location
        Player thePlayer = null;

        //Randomly select a place for the player to spawn
        boolean spawned = false;
        while (!spawned) {
            Location aLocation = new Location((int) (Math.random() * theMap.mapLayout[0].length), (int) (Math.random() * theMap.mapLayout.length));
            if (theMap.getElement(aLocation) == ' ') {
                spawned = true;
                //Create a new player instance in the spawned location
                thePlayer = new Player(new Location(aLocation));
            }
        }

        theMap.setPlayer(thePlayer.getLocation());
        
        //Randomly spawn three enemies on the screen
        int numEnemies = 0;
        while (numEnemies < 3) {
            Location aLocation = new Location((int) (Math.random() * theMap.mapLayout[0].length), (int) (Math.random() * theMap.mapLayout.length));
            if (theMap.getElement(aLocation) == ' ') {
                Enemy theEnemy = new Enemy(new Location(aLocation), (int) (Math.random() * 20) + 1, (int) (Math.random() * 10));
                //Add each enemy into the map
                theMap.addEnemy(theEnemy);
                numEnemies++;
            }
        }

        //Instantiate the game loop control
        boolean run = true;
        //Intantiate variables for game control and output
        Scanner control = new Scanner(System.in);
        String direction;
        String special = "";
        boolean deathNotification = false;

        do {

            // Set the player's location
        	theMap.setPlayer(thePlayer.getLocation());

            //Print the map
            for (int i = 0; i < theMap.mapLayout.length; i++) {
                System.out.println(theMap.mapLayout[i]);
            }

            System.out.println(special);
            
            //Check to see if the player is at an exit, and only let them leave if all enemies have been defeated
            if (special.contains("exit")) {
                if (theMap.enemyList.isEmpty()) {
                    run = false;
                } else {
                    //Otherwise the player will move to the previous position
                    thePlayer.moveDown(theMap);
                    theMap.replaceElement(new Location(thePlayer.getLocation().getX(), thePlayer.getLocation().getY() - 1), 'C');
                    special = "";
                }
            } else if (special.contains("enemy")) {
                //Set-up for enemy encounters
                Enemy anEnemy = null;
                //Find the enemy in the list that the player encountered
                for (Enemy e : theMap.enemyList) {
                    if ((e.getLocation().getX() == thePlayer.getLocation().getX()) && (e.getLocation().getY() == thePlayer.getLocation().getY())) {
                        anEnemy = e;
                    }
                }
                //Start of enemy battle
                while (anEnemy.getHealth() > 0 && thePlayer.getHealth() >= 0) {
                    System.out.println("Player Health: " + thePlayer.getHealth());
                    System.out.println("Enemy Health: " + anEnemy.getHealth());
                    System.out.println("Press A to attack!");
                    direction = control.next();
                    if (direction.equalsIgnoreCase("a")) {
                        thePlayer.attack(anEnemy);
                        System.out.println("The enemy attacked!");
                        thePlayer.getDamage(anEnemy);

                    } else {
                        System.out.println("Invalid Move! The enemy attacked when you were waiting!");
                        thePlayer.getDamage(anEnemy);
                    }

                    if (thePlayer.getHealth() <= 0) {
                        anEnemy.getDamaged(anEnemy.getHealth());
                        run = false;
                        deathNotification = true;
                    }
                }
                if (anEnemy.getHealth() <= 0 && thePlayer.getHealth() > 0) {
                    System.out.println("You killed the enemy!");
                    System.out.println("Player Health: " + thePlayer.getHealth());
                    theMap.removeEnemy(anEnemy);
                    special = doSpecialActions(theMap, thePlayer);
                }
            } else {
                //Change player location based on user input
                System.out.println("Select as direction: w = up, a = left, s = down, d = right, h = health potion (q to quit)");
                direction = control.next();
                switch (direction) {
                    case "q":
                        run = false;
                        break;
                    case "w":
                        thePlayer.moveUp(theMap);
                        break;
                    case "a":
                        thePlayer.moveLeft(theMap);
                        break;
                    case "s":
                        thePlayer.moveDown(theMap);
                        break;
                    case "d":
                        thePlayer.moveRight(theMap);
                        break;
                    case "h":
                        thePlayer.useHealthPotion();
                        break;
                    default:
                        System.out.println("Enter only a w, a, s, d, h or q!");
                }

                special = doSpecialActions(theMap, thePlayer);

            }

        } while (run);

        if (deathNotification) {
            System.out.println("You died! Thanks for Playing!");
        } else {
            System.out.println("Thanks for playing!");
        }
    }
   /**
    *  doSpecialActions 
    *  A method that returns special strings based on events as the game is played
    *
    *  @param Map aMap (a Map object)
    *  @param Player aPlayer (a Player object)
    *
    *  @return String special, a string representing the special action to execute or print
    */

    public static String doSpecialActions(Map theMap, Player aPlayer) {
        char currInLoc = theMap.getElement(aPlayer.getLocation());
        if (currInLoc == 'I') {
            aPlayer.increaseAttack(2);
            return "You picked up an Iron Sword!";
        }
        if (currInLoc == 'H') {
            Collectible HealthPotion = new Collectible('H');
            aPlayer.addCollectible(HealthPotion);
            return "You picked up a health potion!";
        }
        if (currInLoc == 'E') {
            return "You encountered an enemy!";
        }
        if (currInLoc == 'C') {
            if (theMap.enemyList.isEmpty()) {
                return "You reached the exit";
            } else {
                return "You cannot use the exit! Not all enemies have been defeated!";
            }
        }
        return "";
    }

}
