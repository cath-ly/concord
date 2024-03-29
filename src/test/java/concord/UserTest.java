package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	User a, b, c;
	@BeforeEach
	void setUp() throws Exception {
		a = new User();
		a.setUserName("blimey");
		a.setRealName("Emily");
		a.setPassword("123");
		
		b = new User();
		b.setUserName("chaiknees");
		b.setRealName("Hana");
		b.setPassword("456");
		
		c = new User();
		c.setUserName("iamsofriendlyandnice");
		c.setRealName("Lorelei");
		c.setPassword("789");
	}

	@Test
	void testUser() {
		assertEquals("blimey", a.getUserName());
		assertEquals("chaiknees", b.getUserName());
		assertEquals("iamsofriendlyandnice", c.getUserName());
		
		assertEquals("Emily", a.getRealName());
		assertEquals("Hana", b.getRealName());
		assertEquals("Lorelei", c.getRealName());
	
		a.setUserName("blime");
		assertEquals("blime", a.getUserName());
		
		b.setProfileData("bing chilling");
		assertEquals("bing chilling", b.getProfileData());
		
		assertEquals("123", a.getPassword());
		
	}

	@Test
	void testBlock() {
		//add and remove
		ArrayList<User> test = new ArrayList<User>();
		
		a.addBlock(b);
		test.add(b);
		assertEquals(test, a.getBlocks());
		
		a.addBlock(c);
		test.add(c);
		assertEquals(test, a.getBlocks());
		
		a.removeBlock(b);
		test.remove(b);
		assertEquals(test, a.getBlocks());
	}


	/*@Test
	void testSetUrlPic() {
		fail("Not yet implemented");
	}*/
}
