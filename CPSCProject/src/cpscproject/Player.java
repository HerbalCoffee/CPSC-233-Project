/////////////////////TODO implement dodge chance, hit chance, carry weight, Unequiping current item in slot when equipping a new item
package cpscproject;

import java.util.*;

public class Player extends MovableEntity {
    private ArrayList<Collectible> inventory;
    private Hashtable equipment = new Hashtable();
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
        equipment.put("main", null);
        //equipment.put("off", null); CURRENTLY NO OFF HAND
        equipment.put("head", null);
        equipment.put("armor", null);
    }

    public void setLevel(int newLevel){
        this.level = newLevel;
    }

    public int getLevel(){
        return this.level;
    }
  
    // EQUIPMENT RELATED METHODS
    public void equipMainHand(Weapon weapon) {
        if (!this.equipment.get("main").equals(null)) {
            this.unequipWeapon("main");
            this.equipment.put("main", weapon);
        } else {
            this.equipment.put("main", weapon);
        }
        
        //TODO Adjust stats according to weapon; change base damage to weapon's base damage
    }
    
    /*
    public void equipOffHand(Weapon weapon) {
        if (!this.equipment.get("off").equals(null)) {
            this.unequipWeapon("off");
            this.equipment.put("off", weapon);
        } else {
            this.equipment.put("off", weapon);
        }
    }
    */
    
    public void equipHead(Armor head) {
        if (!this.equipment.get("head").equals(null)) {
            this.unequipWeapon("head");
            this.equipment.put("head", head);
        } else {
            this.equipment.put("head", head);
        }
        
        //TODO Adjust stats according to armor; same with other equip methods
    }
    
    public void equipArmor(Armor armor) {
        if (!this.equipment.get("armor").equals(null)) {
            this.unequipWeapon("armor");
            this.equipment.put("armor", armor);
        } else {
            this.equipment.put("armor", armor);
        }
    }
    
   
    /*
    * Unqeuip weapon from specified slot and return it to inventory
    * TODO make weapon unable to be returned to inventory if capacity is reached
    *
    */
   public void unequipWeapon(String slot) {
        if (slot.equals("main") || slot.equals("off"))  {
            if (!this.equipment.get(slot).equals(null)) {
                this.addCollectible(this.equipment.get(slot));
                this.equipment.put(slot, null);
            }
        }
    }
    
    /*
    * Unequips armor from specified slot and returns it to the inventory
    * TODO Make armor unable to be returned to inventory if capactiy is reached
    */
    public void unequipArmor(String slot) {
        if (slot.equals("head") || slot.equals("armor"))  {
            if (!this.equipment.get(slot).equals(null)) {
                this.addCollectible(this.equipment.get(slot));
                this.equipment.put(slot, null);
            }
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

    public void addWeapon(Weapon weapon){
        this.inventory.add(weapon);
        double tempDamage = 0.0;
        for(Collectible item:inventory){
            if (item instanceof Weapon && ((Weapon) item).getWeaponDamage() > tempDamage){
                this.setBaseDamage(this.getDamage("default")+((Weapon) item).getWeaponDamage());
            }
        }
    }

    public void removeWeapon(Weapon weapon){
        this.inventory.remove(weapon);
        this.setBaseDamage(this.getDamage("default")-((Weapon) weapon).getWeaponDamage());
    }

    /**
     * Uses a health potion in the player's inventory, if one exists
     *
     */
    public void useHealthPotion() {
        boolean healthPotionExists = false;
        Consumable potion = null;
        if (!inventory.isEmpty()) {
            for (int index = 0; index < inventory.size(); index++) {
                if (inventory.get(index) instanceof Consumable) {
                    potion = (Consumable) this.inventory.get(index);
                    healthPotionExists = true;
                }
            }
        }
        if (healthPotionExists) {
            this.setHealth(this.getHealth() + potion.getRestoreAmount());
            this.removeCollectible(potion);
            System.out.println("Health Potion Used!");
            System.out.println("Player Health: " + this.getHealth());

        } else {
            System.out.println("No Health Potion was Found in the Inventory!");
        }
    }
}
