package cpscproject;
import java.util.ArrayList;


public class Collectible {

	//	Collectibles at the moment are either Iron Swords or Potions 
	private String name;
	private char type;
	
	//	Stores the players collection of inventory
	
	public Collectible(String name, char type) {
		this.name = name;
		this.type = type;
	}
	
	public char getType() {
		return this.type;
	}

}
