/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.io.FileNotFoundException;
import java.util.Scanner;
import models.*;

/**
 *
 * @author klarshin
 */
public class TextApp {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        //Declare new map instance
        Map theMap = new Map(TextApp.class.getResource("/resources/Map1.txt").getPath());

        //(Temporarily) create variables for player x and y location
        Player thePlayer = null;

        thePlayer = Spawner.spawnPlayer(theMap);

        theMap.replaceElement(thePlayer.getLocation(), thePlayer);

        Spawner.spawnEnemies(theMap, 3);

        Spawner.spawnConsumable(theMap, 2);
        Spawner.spawnWeapon(theMap, 1);

        //Instantiate the game loop control
        boolean run = true;
        boolean success;
        //Intantiate variables for game control and output
        Scanner control = new Scanner(System.in);
        String direction;
        String special = "";
        boolean deathNotification = false;

        do {

            // Set the player's location
            theMap.replaceElement(thePlayer.getLocation(), thePlayer);

            theMap.printMap();

            System.out.println(special);

            //Check to see if the player is at an exit, and only let them leave if all enemies have been defeated
            if (special.contains("exit")) {
                if (theMap.enemyList.isEmpty()) {
                    run = false;
                } else {
                    //Otherwise the player will move to the previous position
                    thePlayer.moveDown(theMap);

                    theMap.replaceElement(new Location(thePlayer.getLocation().getX(), thePlayer.getLocation().getY() - 1), new Exit(new Location(thePlayer.getLocation().getX(), thePlayer.getLocation().getY() - 1)));
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
                        thePlayer.takeDamage(anEnemy.getDamage(direction));

                    } else {
                        System.out.println("Invalid Move! The enemy attacked when you were waiting!");
                        thePlayer.takeDamage(anEnemy.getDamage(direction));
                    }

                    if (thePlayer.getHealth() <= 0) {
                        anEnemy.setHealth(0);
                        run = false;
                        deathNotification = true;
                    }
                }
                if (anEnemy.getHealth() <= 0 && thePlayer.getHealth() > 0) {
                    System.out.println("You killed the enemy!");
                    System.out.println("Player Health: " + thePlayer.getHealth());
                    theMap.removeEnemy(anEnemy);
                    thePlayer.setLevel(thePlayer.getLevel() + 1);
                    System.out.println("The Player's Level is: " + thePlayer.getLevel());
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
                        success = thePlayer.moveUp(theMap);
                        if (!success) {
                            System.out.println("Invalid Move!");
                        }
                        break;
                    case "a":
                        success = thePlayer.moveLeft(theMap);
                        if (!success) {
                            System.out.println("Invalid Move!");
                        }
                        break;
                    case "s":
                        success = thePlayer.moveDown(theMap);
                        if (!success) {
                            System.out.println("Invalid Move!");
                        }
                        break;
                    case "d":
                        success = thePlayer.moveRight(theMap);
                        if (!success) {
                            System.out.println("Invalid Move!");
                        }
                        break;
                    case "h":
                        success = thePlayer.useHealthPotion();
                        if(success){
                            System.out.println("Health Potion Used!");
                            System.out.println("Player Health: " + thePlayer.getHealth());
                        } else {
                            System.out.println("No health potion found!");
                        }
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
     * doSpecialActions A method that returns special strings based on events as
     * the game is played
     *
     * @param theMap the main map object
     * @param aPlayer the main player instance
     *
     * @return String special, a string representing the special action to
     * execute or print
     */
    public static String doSpecialActions(Map theMap, Player aPlayer) {
        if (theMap.getElement(aPlayer.getLocation()) != null) {
            char currInLoc = theMap.getElement(aPlayer.getLocation()).getChar();
            if (currInLoc == 'I') {
                aPlayer.addWeapon((Weapon) theMap.getElement(aPlayer.getLocation()));
                return "You picked up an Iron Sword!";
            }
            if (currInLoc == 'H') {
                aPlayer.addCollectible((Collectible) theMap.getElement(aPlayer.getLocation()));
                return "You picked up a health potion!";
            }
            if (currInLoc == 'E') {
                System.out.println("enemy");
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

        return "";
    }

}
