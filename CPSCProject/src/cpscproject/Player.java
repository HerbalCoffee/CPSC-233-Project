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

    /**
     * Creates an instance of the Player class based on a given location
     * 
     * @param aLocation
     */
    public Player(Location aLocation) {
        this.playerLocation = new Location(aLocation);
        this.playerHealth = 50;
        this.playerDamage = 5;
        this.inventory = new ArrayList<Collectible>();
    }

    /**
     * Returns a copy of the location instance 
     * 
     * @return a copy of the Location object attributed to this player
     */
    public Location getLocation() {
        return new Location(this.playerLocation);
    }

    /**
     * Sets the player's location one square up in a given map instance
     * 
     * @param aMap a map instance
     */
    public void moveUp(Map aMap) {
        if (aMap.isValidMove(new Location(playerLocation.getX(), playerLocation.getY() - 1))) {
            playerLocation.setY(playerLocation.getY() - 1);
            aMap.replaceElement(new Location(playerLocation.getX(), playerLocation.getY() + 1), ' ');
        }else {
            System.out.println("Invalid Move!");
        }

    }

    /**
     * Sets the player's location one square left in a given map instance
     * 
     * @param aMap a map instance
     */
    public void moveLeft(Map aMap) {
        if (aMap.isValidMove(new Location(playerLocation.getX() - 1, playerLocation.getY()))) {
            playerLocation.setX(playerLocation.getX() - 1);
            aMap.replaceElement(new Location(playerLocation.getX() + 1, playerLocation.getY()), ' ');
        }else {
            System.out.println("Invalid Move!");
        }

    }

    /**
     * Sets the player's location one square down in a given map instance
     * 
     * @param aMap a map instance
     */
    public void moveDown(Map aMap) {
        if (aMap.isValidMove(new Location(playerLocation.getX(), playerLocation.getY() + 1))) {
            playerLocation.setY(playerLocation.getY() + 1);
            aMap.replaceElement(new Location(playerLocation.getX(), playerLocation.getY() - 1), ' ');
        }else {
            System.out.println("Invalid Move!");
        }

    }

    /**
     * Sets the player's location one square right in a given map instance
     * 
     * @param aMap a map instance
     */
    public void moveRight(Map aMap) {
        if (aMap.isValidMove(new Location(playerLocation.getX() + 1, playerLocation.getY()))) {
            playerLocation.setX(playerLocation.getX() + 1);
            aMap.replaceElement(new Location(playerLocation.getX() - 1, playerLocation.getY()), ' ');
        } else {
            System.out.println("Invalid Move!");
        }

    }

    /**
     * Adds a given amount to the player's health
     * Ensures health does not exceed 100
     *
     * @param healthToAdd an integer representing the amount of health to give the player
     */
    public void addHealth(int healthToAdd) {

        int tempHealth = this.playerHealth + healthToAdd;
        if (tempHealth > 100) {
            this.playerHealth = 100;
        } else {
            this.playerHealth = tempHealth;
        }

    }

    /**
     * Returns the amount of health the player currently has
     *
     * @return an integer representing the player's current health level
     */
    public int getHealth() {
        return this.playerHealth;
    }

    /**
     * Attacks a given enemy based on the amount of damage a player can inflict
     * 
     * @param anEnemy the enemy to attack
     */
    public void attack(Enemy anEnemy) {
        anEnemy.getDamaged(this.playerDamage);
    }

    /**
     * 
     * Inflicts damage to the player based on a given enemy
     *
     * @param anEnemy the enemy that attacks the player
     */
    public void getDamage(Enemy anEnemy) {
        this.playerHealth -= anEnemy.doesDamage();
    }

    /**
     * Increases the amount of damage a player can inflict based on a given integer
     *
     * @param attackToAdd the amount of attack to add to the player
     */
    public void increaseAttack(int attackToAdd) {
        this.playerDamage += attackToAdd;
    }

    /**
     * Adds a collectible object to the player's inventory
     * 
     * @param collectible the collectible object to be added
     */
    public void addCollectible(Collectible collectible) {
        this.inventory.add(collectible);
    }

    /**
     * Removes the given collectible object from the player's inventory 
     * 
     * @param collectible the collectible object to remove
     */
    public void removeCollectible(Collectible collectible) {
        this.inventory.remove(collectible);
    }

    /**
     * Uses a health potion in the player's inventory, if one exists
     *
     */
    public void useHealthPotion() {
        boolean healthPotionExists = false;
        Collectible potion = null;
        if (!inventory.isEmpty()) {
            for (int index = 0; index < inventory.size(); index++) {
                if (inventory.get(index).getType() == 'H') {
                    potion = this.inventory.get(index);
                    healthPotionExists = true;
                }
            }
        }
        if (healthPotionExists) {
            this.addHealth(10);
            this.removeCollectible(potion);
            System.out.println("Health Potion Used!");
            System.out.println("Player Health: " + this.getHealth());

        } else {
            System.out.println("No Health Potion was Found in the Inventory!");
        }
    }
}
