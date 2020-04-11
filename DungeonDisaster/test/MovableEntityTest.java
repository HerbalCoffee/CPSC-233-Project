import models.*; 
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
 
public class MovableEntityTest {
	
	@Test
	public void test_ConstructorWithStats_default(){
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    assertThat("Testing expected health", testMoveableEntity.getBaseHealth(), is(equalTo(expectedHealth)));
	    assertThat("Testing expected baseDamage", testMoveableEntity.getBaseDamage(), is(equalTo(expectedBaseDamage)));
	    assertThat("Testing expected Character", testMoveableEntity.getChar(), is(equalTo(expectedChar)));
	    assertThat("Testing expected Location of coordinate x", testMoveableEntity.getLocation().getX(), is(equalTo(expectedLocation.getX())));
	    assertThat("Testing expected Location of coordinate y", testMoveableEntity.getLocation().getY(), is(equalTo(expectedLocation.getY())));
	    assertThat("Testing expected Endurance", testMoveableEntity.getEndurance(), is(equalTo(expectedEndurance)));
	    assertThat("Testing expected Strength", testMoveableEntity.getStrength(), is(equalTo(expectedStrength)));
	    assertThat("Testing expected Dexterity", testMoveableEntity.getDexterity(), is(equalTo(expectedDexterity)));
	    assertThat("Testing expected Intelligence", testMoveableEntity.getIntelligence(), is(equalTo(expectedIntelligence)));
	    
	}
	
	@Test
	public void test_ConstructorWithStats_invalidHealth() {
		double expectedHealth = -50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    assertThat("Testing expected health", testMoveableEntity.getBaseHealth(), is(equalTo(100.00)));
	}
	
	@Test
	public void test_ConstructorWithStats_invalidBaseDamage() {
		double expectedHealth = 50;
	    double expectedBaseDamage = -75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    assertThat("Testing expected baseDamage", testMoveableEntity.getBaseDamage(), is(equalTo(0.00)));
	}
	
	@Test
	public void test_ConstructorWithStats_invalidEndurance() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = -50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    assertThat("Testing expected Endurance", testMoveableEntity.getEndurance(), is(equalTo(0)));
	}
	
	@Test
	public void test_ConstructorWithStats_invalidStrength() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = -15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    assertThat("Testing expected Strength", testMoveableEntity.getStrength(), is(equalTo(0)));
	}
	
	@Test
	public void test_ConstructorWithStats_invalidDexterity() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = -5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    assertThat("Testing expected Dexterity", testMoveableEntity.getDexterity(), is(equalTo(0)));
	}
	
	@Test
	public void test_ConstructorWithStats_invalidIntelligence() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = -25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    assertThat("Testing expected Intelligence", testMoveableEntity.getIntelligence(), is(equalTo(0)));
	}
	
	@Test
	public void test_CalculateActualHealth() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    double expectedCalculatedHealth = expectedHealth + (expectedHealth * (expectedEndurance / expectedHealth));
	    assertThat("Testing the calculated health that is adjusted for endurance", testMoveableEntity.getHealth(), is(equalTo(expectedCalculatedHealth)));
	}
	
	@Test
	public void test_GetDamage_Physical() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    double expectedDamage = expectedBaseDamage*2.5;
	    assertTrue("Testing the getDamage for physical attacks", testMoveableEntity.getDamage("physical") <= expectedDamage);
	}
	
	@Test
	public void test_GetDamage_Magic() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    double expectedDamage = expectedBaseDamage + (expectedBaseDamage * (expectedIntelligence / 2));
	    assertThat("Testing the getDamage for magical attacks", testMoveableEntity.getDamage("magic"), is(equalTo(expectedDamage)));
	}
	
	@Test
	public void test_GetDamage_default() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    assertThat("Testing the default is used for misspelled/capitalised magic", testMoveableEntity.getDamage("Magic"), is(equalTo(expectedBaseDamage)));
	    assertThat("Testing the default is used for misspelled/capitalised magic", testMoveableEntity.getDamage("mgaic"), is(equalTo(expectedBaseDamage)));
	    assertThat("Testing the default is used for misspelled/capitalised physical", testMoveableEntity.getDamage("Physical"), is(equalTo(expectedBaseDamage)));
	    assertThat("Testing the default is used for misspelled/capitalised magic", testMoveableEntity.getDamage("physicel"), is(equalTo(expectedBaseDamage)));
	    assertThat("Testing the default is used for any string not magic or physical", testMoveableEntity.getDamage("CPSC233%^*^^&SHJDKL234"), is(equalTo(expectedBaseDamage)));
	}
	
	@Test
	public void test_TakeDamage_default() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    double defense = 0;
	    double damage = 10;
	    double expectedHealthAfterDamage = testMoveableEntity.getHealth()- (damage - (damage * (0.1*defense)));
	    
	    testMoveableEntity.takeDamage(10);
	    
	    assertThat("Testing the takeDamage method", testMoveableEntity.getHealth(), is(equalTo(expectedHealthAfterDamage)));
	}
	
	@Test
	public void test_TakeDamage_invalidDamage() {
		double expectedHealth = 50;
	    double expectedBaseDamage = 75;   
	    int expectedEndurance = 50;
	    int expectedStrength = 15; 
	    int expectedDexterity = 5; 
	    int expectedIntelligence = 25; 
	    Location expectedLocation = new Location(5,5);
	    char expectedChar = 'j';
	    
	    MovableEntity testMoveableEntity = new MovableEntity(expectedHealth, expectedBaseDamage, expectedEndurance, expectedStrength, expectedDexterity, expectedIntelligence, expectedLocation, expectedChar);
	    
	    double defense = 0;
	    double damage = 0;
	    double expectedHealthAfterDamage = testMoveableEntity.getHealth()- (damage - (damage * (0.1*defense)));
	    
	    testMoveableEntity.takeDamage(0);
	    
	    assertThat("Testing the takeDamage method with damage not greater than 0", testMoveableEntity.getHealth(), is(equalTo(expectedHealthAfterDamage)));
	}
	
	@Test
	public void test_MoveUp() {
	    try {
			Map theMap = new Map(MovableEntityTest.class.getResource("/resources/Map1.txt").getPath());
			Player thePlayer = null;
	        thePlayer = Spawner.spawnPlayer(theMap);
	        theMap.replaceElement(thePlayer.getLocation(), thePlayer);
	        
	        int originalX_Location = thePlayer.getLocation().getX();
	        int originalY_Location = thePlayer.getLocation().getY();
	        thePlayer.moveUp(theMap);
	        
	        assertEquals("The players X Location is the same as originally", thePlayer.getLocation().getX(), originalX_Location);
	        assertEquals("The players Y Location is one smaller than it was originally", thePlayer.getLocation().getY(), originalY_Location-1, 2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error is in the moveUp test.");
		}
	}
	
	@Test
	public void test_MoveDown() {
		try {
			Map theMap = new Map(MovableEntityTest.class.getResource("/resources/Map2.txt").getPath());
			Player thePlayer = null;
	        thePlayer = Spawner.spawnPlayer(theMap);
	        theMap.replaceElement(thePlayer.getLocation(), thePlayer);
	        
	        int originalX_Location = thePlayer.getLocation().getX();
	        int originalY_Location = thePlayer.getLocation().getY();
	        thePlayer.moveDown(theMap);
	        
	        assertEquals("The players X Location is the same as originally", thePlayer.getLocation().getX(), originalX_Location);
	        assertEquals("The players Y Location is one larger than it was originally", thePlayer.getLocation().getY(), originalY_Location+1, 2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error is in the moveDown test.");
		}
	}
	
	@Test
	public void test_MoveRight() {
		try {
			Map theMap = new Map(MovableEntityTest.class.getResource("/resources/Map3.txt").getPath());
			Player thePlayer = null;
	        thePlayer = Spawner.spawnPlayer(theMap);
	        theMap.replaceElement(thePlayer.getLocation(), thePlayer);
	        
	        int originalX_Location = thePlayer.getLocation().getX();
	        int originalY_Location = thePlayer.getLocation().getY();
	        thePlayer.moveRight(theMap);
	        
	        assertEquals("The players X Location is one larger than it was originally", thePlayer.getLocation().getX(), originalX_Location+1, 2);
	        assertEquals("The players Y Location is the same as originally", thePlayer.getLocation().getY(), originalY_Location);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error is in the moveRight test.");
		}
	}
	
	@Test
	public void  test_MoveLeft() {
		try {
			Map theMap = new Map(MovableEntityTest.class.getResource("/resources/Map1.txt").getPath());
			Player thePlayer = null;
	        thePlayer = Spawner.spawnPlayer(theMap);
	        theMap.replaceElement(thePlayer.getLocation(), thePlayer);
	        
	        int originalX_Location = thePlayer.getLocation().getX();
	        int originalY_Location = thePlayer.getLocation().getY();
	        thePlayer.moveRight(theMap);
	        
	        assertEquals("The players X Location is one smaller than it was originally", thePlayer.getLocation().getX(), originalX_Location-1, 2);
	        assertEquals("The players Y Location is the same as originally", thePlayer.getLocation().getY(), originalY_Location);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error is in the moveLeft test.");
		}
	}
	
	
}
