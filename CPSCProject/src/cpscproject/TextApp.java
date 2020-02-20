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
        while(!spawned){
            int spawnPointX = (int)(Math.random() * 10);
            int spawnPointY = (int)(Math.random() * 10);
            if(theMap.getElement(spawnPointY, spawnPointX) == ' '){
                spawned = true;
                playerX = spawnPointX;
                playerY = spawnPointY;
            }
        }
        
        //Instantiate the game loop
        boolean run = true;
        Scanner control = new Scanner(System.in);
        String direction;
        int oldPlayerX = -1;
        int oldPlayerY = -1;
        
        
        do{
            // Set the player's location
            theMap.setPlayer(playerY, playerX);
            oldPlayerX = playerX;
            oldPlayerY = playerY;
            
            //Print the map
            for(int i = 0; i < theMap.mapLayout.length; i++){
                System.out.println(theMap.mapLayout[i]);
            }
            
            //Change player location based on user input
            System.out.println("Select a direction: w = up, a = left, s = down, d = right (q to quit)");
            direction = control.next();
            switch(direction){
                case "q":
                    System.out.println("Thanks for playing!");
                    run = false;
                    break;
                case "w":
                    if(theMap.isValidMove(playerY - 1, playerX)){
                        playerY -= 1;
                    } else {
                        System.out.println("Invalid Move!");
                    }
                    break;
                case "a":
                    if(theMap.isValidMove(playerY, playerX - 1)){
                        playerX -= 1;
                    } else {
                        System.out.println("Invalid Move!");
                    }
                    break;
                case "s":
                    if(theMap.isValidMove(playerY + 1, playerX)){
                        playerY += 1;
                    } else {
                        System.out.println("Invalid Move!");
                    }
                    break;
                case "d":
                    if(theMap.isValidMove(playerY, playerX + 1)){
                        playerX += 1;
                    } else {
                        System.out.println("Invalid Move!");
                    }
                    break;
                default:
                    System.out.println("Enter only a w, a, s, d, or q!");
            }
            
            doSpecialActions(theMap, playerX, playerY);
            theMap.replaceElement(oldPlayerY, oldPlayerX, ' ');
        
        } while(run);
        
    }
    
    public static void doSpecialActions(Map aMap, int xLoc, int yLoc){
        char currInLoc = aMap.getElement(yLoc, xLoc);
        if(currInLoc == 'I'){
            System.out.println("You picked up an Iron Sword!");
        }
        if(currInLoc == 'H'){
            System.out.println("You picked up a health potion!");
        }
        if(currInLoc == 'E'){
            System.out.println("You killed an enemy!");
        }
    }
    
    
    
}
