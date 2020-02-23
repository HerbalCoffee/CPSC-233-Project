/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpscproject;

import java.util.ArrayList;

/**
 *
 * @author klarshin
 */
public class Player {
    
    private Location playerLocation;
    private int playerHealth;
    private int playerDamage;
    private ArrayList<Collectible> inventory;
    
    public Player(Location aLocation){
        this.playerLocation = new Location(aLocation);
        this.playerHealth = 50;
        this.playerDamage = 5;
    }
    
    public Location getLocation(){
        return new Location(this.playerLocation);
    }
    
	public void setLocation(Map theMap, Location location) {
		theMap.mapLayout[location.getY()][location.getX()] = 'X';
		this.playerLocation = location;
	}
    
    public void moveUp(Map aMap){
        if(aMap.isValidMove(new Location(playerLocation.getX(), playerLocation.getY() - 1))){
            playerLocation.setY(playerLocation.getY() - 1);
            aMap.replaceElement(new Location(playerLocation.getX(), playerLocation.getY() + 1), ' ');
        }
        
    }
    
    public void moveLeft(Map aMap){
        if(aMap.isValidMove(new Location(playerLocation.getX() - 1, playerLocation.getY()))){
            playerLocation.setX(playerLocation.getX() - 1);
            aMap.replaceElement(new Location(playerLocation.getX() + 1, playerLocation.getY()), ' ');
        }
        
    }
    
    public void moveDown(Map aMap){
        if(aMap.isValidMove(new Location(playerLocation.getX(), playerLocation.getY() + 1))){
            playerLocation.setY(playerLocation.getY() + 1);
            aMap.replaceElement(new Location(playerLocation.getX(), playerLocation.getY() - 1), ' ');
        }
        
    }
    
    public void moveRight(Map aMap){
        if(aMap.isValidMove(new Location(playerLocation.getX() + 1, playerLocation.getY()))){
            playerLocation.setX(playerLocation.getX() + 1);
            aMap.replaceElement(new Location(playerLocation.getX() - 1, playerLocation.getY()), ' ');
        }
        
    }
    
    public void addHealth(int healthToAdd){
        
        int tempHealth = this.playerHealth + healthToAdd;
        if(tempHealth > 100){
            this.playerHealth = 100;
        } else {
            this.playerHealth = tempHealth;
        }
        
    }
    
    public int getHealth(){
        return this.playerHealth;
    }
    
    public void attack(Enemy anEnemy){
        anEnemy.getDamaged(this.playerDamage);
    }
    
    public void getDamage(Enemy anEnemy){
        this.playerHealth -= anEnemy.doesDamage();
    }
    
    public void increaseAttack(int attackToAdd){
        this.playerDamage += attackToAdd;
    }
    
    public void addCollectible(Collectible collectible) {
    	this.inventory.add(collectible);
    }
    
    public void removeCollectible(Collectible collectible) {
    	this.inventory.remove(collectible);
    }
    
    public void useHealthPotion() {
		boolean healthPotionExists = false;
    	if (!inventory.isEmpty()) {
    		for(int index = 0;index < inventory.size();index++) {
    			if (inventory.get(index).getType() == 'H') {
    				healthPotionExists = true;
    			}
    		}
		}
    	if (healthPotionExists) {
    		this.addHealth(10);
    		System.out.println("Health Potion Used!");
 
    	}else {
    		System.out.println("No Health Potion was Found in the Inventory!");
    	}
    }
}
