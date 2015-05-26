package dbLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBPostalcodeTest {
	private DBPostalcode dbP;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbP = new DBPostalcode();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindCity() {
		assertTrue(dbP.findCity("9900").equals("Frederikshavn"));
	}

}
