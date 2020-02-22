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
 * @author ksl
 */
public class TextApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        //Declare new map instance
        Map theMap = new Map("src/cpscproject/Map1.txt");

        //(Temporarily) create variables for player x and y location
        
        
        Player thePlayer = new Player(new Location(-1,-1));;

        //Randomly select a place for the player to spawn
        boolean spawned = false;
        while (!spawned) {
            Location aLocation = new Location((int) (Math.random() * theMap.mapLayout[0].length), (int) (Math.random() * theMap.mapLayout.length));
            if (theMap.getElement(aLocation) == ' ') {
                spawned = true;
                thePlayer = new Player(new Location(aLocation));
            }
        }
        theMap.setPlayer(thePlayer.getLocation());
        
        int numEnemies = 0;
        while(numEnemies < 3){
            Location aLocation = new Location((int)(Math.random()* theMap.mapLayout[0].length), (int)(Math.random()* theMap.mapLayout.length));
            if (theMap.getElement(aLocation) == ' ') {
                Enemy theEnemy = new Enemy(new Location(aLocation), (int)(Math.random() * 10), (int)(Math.random() * 3));
                theMap.addEnemy(theEnemy);
                numEnemies++;
            }
        }

        //Instantiate the game loop
        boolean run = true;
        Scanner control = new Scanner(System.in);
        String direction;
        String special = "";
        
        
        do {

            // Set the player's location
            theMap.setPlayer(thePlayer.getLocation());

            //Print the map
            for (int i = 0; i < theMap.mapLayout.length; i++) {
                System.out.println(theMap.mapLayout[i]);
            }

            System.out.println(special);

            if (special.contains("exit")) {
                run = false;
            } else if(special.contains("enemy")){
                Enemy anEnemy = null;
                for(Enemy e : theMap.enemyList){
                    if((e.getLocation().getX() == thePlayer.getLocation().getX()) && (e.getLocation().getY() == thePlayer.getLocation().getY()) ){
                        anEnemy = e;
                    }
                }
                while(anEnemy.health > 0){
                    System.out.println("Enemy Health: " + anEnemy.health);
                    System.out.println("Press A to attack!");
                    direction = control.next();
                    if(direction.equalsIgnoreCase("a")){
                        thePlayer.attack(anEnemy);
                        System.out.println("The enemy attacked!");
                        thePlayer.getDamage(anEnemy);
                        System.out.println("Player Health: " + thePlayer.getHealth());
                        if(thePlayer.getHealth() <= 0){
                            anEnemy.health = 0;
                            run = false;
                        }
                    }
                }
                System.out.println("You killed the enemy!");
                special = doSpecialActions(theMap, thePlayer);
            } else {
                //Change player location based on user input
                System.out.println("Select a direction: w = up, a = left, s = down, d = right (q to quit)");
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
                    default:
                        System.out.println("Enter only a w, a, s, d, or q!");
                }

                special = doSpecialActions(theMap, thePlayer);

               
            }
            
            
            
        } while (run);
        
        System.out.println("Thanks for playing!");
    }

    public static String doSpecialActions(Map aMap, Player aPlayer) {
        char currInLoc = aMap.getElement(aPlayer.getLocation());
        if (currInLoc == 'I') {
            aPlayer.increaseAttack(2);
            return "You picked up an Iron Sword!";
        }
        if (currInLoc == 'H') {
            aPlayer.addHealth(5);
            return "You picked up a health potion!";
        }
        if (currInLoc == 'E') {
            return "You encountered an enemy!";
        }
        if (currInLoc == 'C') {
            return "You reached the exit";
        }
        return "";
    }

}
