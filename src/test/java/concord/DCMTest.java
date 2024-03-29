package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DCMTest {
	User a, b, c;
	ArrayList<User> test = new ArrayList<User>(), test2 = new ArrayList<User>();
	DirectConversationManager dcm;
	ArrayList<DirectConversation> testdcm = new ArrayList<DirectConversation>();
	DirectConversation dc1, dc2;
	
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
		
		test.add(a);
		test.add(b);
		test.add(c);
		
		test2.add(a);
		test2.add(c);
		
		dcm = new DirectConversationManager();
		dc1 = dcm.createDC(test);
		dc2 = dcm.createDC(test2);
	}

	@Test
	void testDirectConversationManager() {
		assertEquals(2, dcm.getDcs().size());
	}

	@Test
	void testGetPastConversations() {
		ArrayList<DirectConversation> dclist;
		dclist = dcm.getPastConversations(a);
		assertEquals(2, dclist.size());
		
	}

}
