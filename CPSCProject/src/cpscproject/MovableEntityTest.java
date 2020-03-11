package cpscproject;
import static org.junit.Assert.*;
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
	    
	    assertThat("Testing expected health", testMoveableEntity.getHealth(), is(equalTo(expectedHealth)));
	    assertThat("Testing expected baseDamage", testMoveableEntity.getBaseDamage(), is(equalTo(expectedBaseDamage)));
	    assertThat("Testing expected Character", testMoveableEntity.getChar(), is(equalTo(expectedChar)));
//	    assertThat("Testing expected Location", testMoveableEntity.getLocation(), is(equalTo(expectedLocation)));
	    assertThat("Testing expected Endurance", testMoveableEntity.getEndurance(), is(equalTo(expectedEndurance)));
	    assertThat("Testing expected Strength", testMoveableEntity.getStrength(), is(equalTo(expectedStrength)));
	    assertThat("Testing expected Dexterity", testMoveableEntity.getDexterity(), is(equalTo(expectedDexterity)));
	    assertThat("Testing expected Intelligence", testMoveableEntity.getIntelligence(), is(equalTo(expectedIntelligence)));
	    
	}
}
