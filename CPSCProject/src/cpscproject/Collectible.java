package cpscproject;
import java.util.ArrayList;


public class Collectible {

	//	Collectibles at the moment are either Iron Swords or Potions 
	private char Weapon = 'I';
	private char Potion = 'H';
	
	//	Stores the players collection of inventory
	private ArrayList<Character> Inventory = new ArrayList<Character>();
	
	// A method that takes as input the players current position where they picked up an item and adds that item to their inventory and removes that item from the map
	public void addWeapon(Map theMap, Location weaponLocation) {
		Inventory.add(Weapon);
		theMap.replaceElement(weaponLocation, ' ');
		
	}
	
	// A method that takes as input the players current position where they picked up an item and adds that item to their inventory and removes that item from the map
	public void addPotion(Map theMap, Location potionLocation) {
		Inventory.add(Potion);
		theMap.replaceElement(potionLocation, ' ');
	}
	
	// A method that returns the players current inventory as an arraylist.
	public ArrayList<Character> getInventory() {
		ArrayList<Character> copyList = new ArrayList<Character>();
		for(int arrayIndex = 0; arrayIndex<Inventory.size(); arrayIndex++) 
		{
			copyList.add(Inventory.get(arrayIndex));
		}
		return copyList;
	}
}
