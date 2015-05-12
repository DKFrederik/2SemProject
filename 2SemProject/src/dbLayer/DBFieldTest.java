package dbLayer;
import modelLayer.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DBFieldTest {

	private DBField dbfield;
	
	@Before
	public void setUp() throws Exception {
		dbfield = new DBField();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertField() {
		Field field = new Field("1", "Kamp", 100, 64);
		try {
			assertEquals(1, dbfield.insertField(field));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertField1() {
		Field field = new Field("2", "Træning", 100, 64);
		try {
			assertEquals(1, dbfield.insertField(field));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertField2() {
		Field field = new Field("3", "Kamp", 100, 64);
		try {
			assertEquals(1, dbfield.insertField(field));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertField3() {
		Field field = new Field("4", "Træning", 100, 64);
		try {
			assertEquals(1, dbfield.insertField(field));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchField() {
		Field field= dbfield.findField("5");
		if(field !=null){
			System.out.println(field.getFieldNumber());
			assertEquals("1",field.getType());
			System.out.println(field.getType());
		}
		else {
			fail("Field not found!");
		}
	}
}
