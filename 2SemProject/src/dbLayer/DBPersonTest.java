package dbLayer;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelLayer.*;

public class DBPersonTest {
	private Player pl;
	private DBPerson dbP;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		pl = new Player("Nichlas", "Pedersen", "Test@email.com", "11223344", "9800", "1993-12-30", "F");
		dbP = new DBPerson();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void insertTest() {
		try {
			assertEquals(0, dbP.insertPerson(pl));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("");

		}
	}

}
