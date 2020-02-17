/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpscproject;

import java.io.FileNotFoundException;

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
        
        //Print the map
        for(int i = 0; i < theMap.mapLayout.length; i++){
            System.out.println(theMap.mapLayout[i]);
        }
    }
    
}
