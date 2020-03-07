/////////////////////TODO implement dodge chance, hit chance, carry weight, Unequiping current item in slot when equipping a new item
package cpscproject;

import java.util.ArrayList;

public class Player extends MovableEntity {
    private ArrayList<Collectible> inventory;
    private Collectible[] equippedHands = new Collectible[2]; // Equipment slots for main hand [0] and offhand [1]
    private Collectible[] equippedArmor = new Collectible[5]; // Equipment slots for armor. Head [0], Torso [1], Hands[2], Legs [3], Feet [4]
    private int level;
    // CAN ADD ANOTHER ARRAY FOR THINGS LIKE RINGS, AMULETS ETC.
    
    /**
     * Creates an instance of the Player class based on a given location
     * 
     * @param aLocation
     */
    public Player(double Health, double Damage, Location location) {
        super(Health, Damage, 0, 0, 0, 0, location, 'P');
        this.inventory = new ArrayList<Collectible>();
        this.level = 0;
    }

    public void setLevel(int newLevel){
        this.level = newLevel;
    }

    public int getLevel(){
        return this.level;
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
        if (!this.equippedArmor[0].equals(null)) {
            this.equippedArmor[0] = head;
        } else {
            this.unequipArmor("head");
            this.equippedArmor[0] = head;
        }
        //TODO Adjust stats according to armor; same with other equip methods
    }
    
    public void equipTorso(Armor torso) {
        //if (torso.type().equals("torso")) { //TODO
        if (!this.equippedArmor[1].equals(null)) {
            this.equippedArmor[1] = torso;
        } else {
            this.unequipArmor("torso");
            this.equippedArmor[1] = torso;
        }
    }
    
    public void equipHands(Armor hands) {
        //if (hands.type().equals("hands")) { //TODO
        if (!this.equippedArmor[2].equals(null)) {
            this.equippedArmor[2] = hands;
        } else {
            this.unequipArmor("hands");
            this.equippedArmor[2] = hands;
        }
    }
    
    public void equipLegs(Armor legs) {
        //if (legs.type().equals("legs")) { //TODO
        if (!this.equippedArmor[3].equals(null)) {
            this.equippedArmor[3] = legs;
        } else {
            this.unequipArmor("legs");
            this.equippedArmor[3] = legs;
        }
    }
    
    public void equipFeet(Armor feet) {
        //if (feet.type().equals("feet)) { //TODO
        if (!this.equippedArmor[4].equals(null)) {
            this.equippedArmor[4] = feet;
        } else {
            this.unequipArmor("feet");
            this.equippedArmor[4] = feet;
        }
    }
    
    public void equipArmor(Armor armor, String armorType) {
        switch (armorType) {
            //TODO Add stat modifications according to armor
            case "head":
                if (!this.equippedArmor[0].equals(null)) {
                    this.equippedArmor[0] = armor;
                } else {
                    this.unequipArmor("head");
                    this.equippedArmor[0] = armor;
                }
                break;
            case "torso":
                if (!this.equippedArmor[1].equals(null)) {
                    this.equippedArmor[1] = armor;
                } else {
                    this.unequipArmor("torso");
                    this.equippedArmor[1] = armor;
                }
                break;
            case "hands":
                if (!this.equippedArmor[2].equals(null)) {
                    this.equippedArmor[2] = armor;
                } else {
                    this.unequipArmor("hands");
                    this.equippedArmor[2] = armor;
                }
                break;
            case "legs":
                if (!this.equippedArmor[3].equals(null)) {
                    this.equippedArmor[3] = armor;
                } else {
                    this.unequipArmor("legs");
                    this.equippedArmor[3] = armor;
                }
                break;
            case "feet":
                if (!this.equippedArmor[4].equals(null)) {
                    this.equippedArmor[4] = armor;
                } else {
                    this.unequipArmor("feet");
                    this.equippedArmor[4] = armor;
                }
                break;
        }
    }
    
    /*
    * Unqeuip weapon from specified slot and return it to inventory
    * TODO make weapon unable to be returned to inventory if capacity is reached
    *
    */
    public void unequipWeapon(String slot) {
        switch (slot) {
            case "main" :
                this.addCollectible(this.equippedHands[0]);
                this.equippedHands[0] = null;
                break;
            case "off" :
                this.addCollectible(this.equippedHands[1]);
                this.equippedHands[0] = null;
                break;
        }
    }
    
    /*
    * Unequips armor from specified slot and returns it to the inventory
    * TODO Make armor unable to be returned to inventory if capactiy is reached
    */
    public void unequipArmor(String slot) {
        switch (slot) {
            case "head" : 
                this.addCollectible(this.equippedArmor[0]);
                this.equippedArmor[0] = null;
                break;
            case "torso" :
                this.addCollectible(this.equippedArmor[1]);
                this.equippedArmor[1] = null;
                break;
            case "hands" :
                this.addCollectible(this.equippedArmor[2]);
                this.equippedArmor[2] = null;
                break;
            case "legs" :
                this.addCollectible(this.equippedArmor[3]);
                this.equippedArmor[3] = null;
                break;
            case "feet" :
                this.addCollectible(this.equippedArmor[4]);
                this.equippedArmor[4] = null;
                break;
        }
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
