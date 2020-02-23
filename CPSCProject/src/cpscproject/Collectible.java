package cpscproject;

//Collectible Class that is used to store collectible data

/**
 *
 * 
 */
public class Collectible {

	//Collectible at the moment are either Iron Swords or Potions 
	private char type;

    /**
     * Constructor that takes a type of Collectible
     *
     * @param type a character representing the type of the collectible
     */
	public Collectible(char type) {
		this.type = type;
	}
	

    /**
     * Getter for Type
     *
     * @return a character representing the type of the collectible
     */
	public char getType() {
		return this.type;
	}

}
