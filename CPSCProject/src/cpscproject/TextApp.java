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
        int playerX = -1;
        int playerY = -1;

        //Randomly select a place for the player to spawn
        boolean spawned = false;
        while (!spawned) {
            int spawnPointX = (int) (Math.random() * theMap.mapLayout[0].length);
            int spawnPointY = (int) (Math.random() * theMap.mapLayout.length);
            if (theMap.getElement(spawnPointY, spawnPointX) == ' ') {
                spawned = true;
                playerX = spawnPointX;
                playerY = spawnPointY;
            }
        }
        theMap.setPlayer(playerY, playerX);
        
        int numEnemies = 0;
        while(numEnemies < 3){
            int x = (int)(Math.random()* theMap.mapLayout[0].length);
            int y = (int)(Math.random()* theMap.mapLayout.length);
            if(theMap.isValidMove(y, x)){
                theMap.mapLayout[y][x] = 'E';
                numEnemies++;
            }
        }

        //Instantiate the game loop
        boolean run = true;
        Scanner control = new Scanner(System.in);
        String direction;
        int oldPlayerX = -1;
        int oldPlayerY = -1;
        String special = "";
        int playerHealth = 20;
        int playerDamage = 5;
        
        
        do {

            // Set the player's location
            theMap.setPlayer(playerY, playerX);
            oldPlayerX = playerX;
            oldPlayerY = playerY;

            //Print the map
            for (int i = 0; i < theMap.mapLayout.length; i++) {
                System.out.println(theMap.mapLayout[i]);
            }

            System.out.println(special);

            if (special.contains("exit")) {
                run = false;
            } else if(special.contains("enemy")){
                int enemyHealth = (int)(Math.random() * 10);
                int enemyDamage = (int)(Math.random() * 3);
                while(enemyHealth > 0){
                    System.out.println("Enemy Health: " + enemyHealth);
                    System.out.println("Press A to attack!");
                    direction = control.next();
                    if(direction.equalsIgnoreCase("a")){
                        enemyHealth -= playerDamage;
                        System.out.println("The enemy attacked!");
                        playerHealth -= enemyDamage;
                        System.out.println("Player Health: " + playerHealth);
                        if(playerHealth <= 0){
                            enemyHealth = 0;
                            run = false;
                        }
                    }
                }
                System.out.println("You killed the enemy!");
                special = doSpecialActions(theMap, playerX, playerY, playerHealth, playerDamage);
            } else {
                //Change player location based on user input
                System.out.println("Select a direction: w = up, a = left, s = down, d = right (q to quit)");
                direction = control.next();
                switch (direction) {
                    case "q":
                        run = false;
                        break;
                    case "w":
                        if (theMap.isValidMove(playerY - 1, playerX)) {
                            playerY -= 1;
                        } else {
                            System.out.println("Invalid Move!");
                        }
                        break;
                    case "a":
                        if (theMap.isValidMove(playerY, playerX - 1)) {
                            playerX -= 1;
                        } else {
                            System.out.println("Invalid Move!");
                        }
                        break;
                    case "s":
                        if (theMap.isValidMove(playerY + 1, playerX)) {
                            playerY += 1;
                        } else {
                            System.out.println("Invalid Move!");
                        }
                        break;
                    case "d":
                        if (theMap.isValidMove(playerY, playerX + 1)) {
                            playerX += 1;
                        } else {
                            System.out.println("Invalid Move!");
                        }
                        break;
                    default:
                        System.out.println("Enter only a w, a, s, d, or q!");
                }

                special = doSpecialActions(theMap, playerX, playerY, playerHealth, playerDamage);

               
            }
            
             theMap.replaceElement(oldPlayerY, oldPlayerX, ' ');
            
        } while (run);
        
        System.out.println("Thanks for playing!");
    }

    public static String doSpecialActions(Map aMap, int xLoc, int yLoc, int playHealth, int playDamage) {
        char currInLoc = aMap.getElement(yLoc, xLoc);
        if (currInLoc == 'I') {
            playDamage += 2;
            return "You picked up an Iron Sword!";
        }
        if (currInLoc == 'H') {
            playHealth += 5;
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
