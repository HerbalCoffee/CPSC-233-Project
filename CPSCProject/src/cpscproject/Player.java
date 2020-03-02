/////////////////////TODO implement dodge chance, hit chance
package cpscproject;

import java.util.ArrayList;

public class Player extends MovableEntity {
    private ArrayList<Collectible> inventory;
    private Collectible[] equippedHands = new Collectible[2]; // Equipment slots for main hand [0] and offhand [1]
    private Collectible[] equippedArmor = new Collectible[5]; // Equipment slots for armor. Head [0], Torso [1], Hands[2], Legs [3], Feet [4]
    // CAN ADD ANOTHER ARRAY FOR THINGS LIKE RINGS, AMULETS ETC.
    
    /**
     * Creates an instance of the Player class based on a given location
     * 
     * @param aLocation
     */
    public Player(double Health, double Damage, Location location) {
        super(Health, Damage, 0, 0, 0, 0, location, 'P');
        this.inventory = new ArrayList<Collectible>();
    }
    
    // EQUIPMENT RELATED METHODS
    public void equipMainHand(Weapon weapon) {
        this.equippedHands[0] = weapon;
        //TODO Adjust stats according to weapon; change base damage to weapon's base damage
    }
    
    public void equipOffhand(Weapon weapon) {
        this.equippedHands[1] = weapon;
        //TODO Adjust stats according to weapon
        //Also adjust added damage/stats, strength requirements, and add shields
    }
    
    public void equipHead(Armor head) {
        //if (head.type().equals("head")) { //TODO, check if head is a headgear and if can be equipped
        this.equippedArmor[0] = head;
        //TODO Adjust stats according to armor; same with other equip methods
    }
    
    public void equipTorso(Armor torso) {
        //if (torso.type().equals("torso")) { //TODO
        this.equippedArmor[1] = torso;
    }
    
    public void equipHands(Armor hands) {
        //if (hands.type().equals("hands")) { //TODO
        this.equippedArmor[2] = hands;
    }
    
    public void equipLegs(Armor legs) {
        //if (legs.type().equals("legs")) { //TODO
        this.equippedArmor[3] = legs;
    }
    
    public void equipFeet(Armor feet) {
        //if (feet.type().equals("feet)) { //TODO
        this.equippedArmor[4] = feet;
    }
    
    public void equipArmor(Armor armor, String armorType) {
        switch (armorType) {
                //TODO Add stat modifications according to armor
            case "head" : 
                this.equippedArmor[0] = armor;
                break;
            case "torso" :
                this.equippedArmor[1] = armor;
                break;
            case "hands" :
                this.equippedArmor[2] = armor;
                break;
            case "legs" :
                this.equippedArmor[3] = armor;
                break;
            case "feet" :
                this.equippedArmor[4] = armor;
                break;
                
    }
    
    

    /**
     * Attacks a given enemy based on the amount of damage a player can inflict
     * 
     * @param anEnemy the enemy to attack
     */
    public void attack(Enemy anEnemy) {
        anEnemy.takeDamage(this.getDamage(""));
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
                if (inventory.get(index).getChar()== 'H') {
                    potion = this.inventory.get(index);
                    healthPotionExists = true;
                }
            }
        }
        if (healthPotionExists) {
            this.setHealth(this.getHealth() + 10);
            this.removeCollectible(potion);
            System.out.println("Health Potion Used!");
            System.out.println("Player Health: " + this.getHealth());

        } else {
            System.out.println("No Health Potion was Found in the Inventory!");
        }
    }
}
