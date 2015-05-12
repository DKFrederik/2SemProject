package dbLayer;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelLayer.*;

public class DBFieldTest {
	private Field f;
	private DBField dbF;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		f = new Field("1", "Kamp", "100", "64");
		dbF = new DBField();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void insertTest() {
		try {
			assertEquals(0, dbF.insertField(f));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("");

		}
	}

}
