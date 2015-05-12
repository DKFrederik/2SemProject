package modelLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {
	private Player p;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		p = new Player("Nichlas", "Pedersen", "Test@email.com", "9800", "11223344", "1993-12-30", "F");

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getAgeTest() {
		assertEquals(21, p.getAge());
		p.stringSetBDay("1990-05-01");
		assertEquals(25, p.getAge());
	}
}
