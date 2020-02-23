package cpscproject;

//Collectible Class that is used to store collectible data
public class Collectible {

	//	Collectible at the moment are either Iron Swords or Potions 
	private char type;

	//Constructor that Takes a type of Collectible
	public Collectible(char type) {
		this.type = type;
	}
	
	//Getter for Type
	public char getType() {
		return this.type;
	}

}
