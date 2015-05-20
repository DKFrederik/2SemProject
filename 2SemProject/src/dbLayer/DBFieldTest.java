package dbLayer;
import java.util.ArrayList;

import modelLayer.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DBFieldTest {

	private DBField dbfield;
	private Field f;
	
	@Before
	public void setUp() throws Exception {
		dbfield = new DBField();
		f = new Field("1", "Kamp", 100, 64);
//		f = new Field("2", "Træning", 100, 64);
//		f = new Field("3", "Kamp", 100, 64);
//		f = new Field("4", "Træning", 100, 64);
	}
	
	@After
	public void tearDown() throws Exception {
			assertEquals(1, dbfield.deleteField("1"));
//			assertEquals(1, dbfield.deleteField("2"));
//			assertEquals(1, dbfield.deleteField("3"));
//			assertEquals(1, dbfield.deleteField("4"));
	}
	
	@Test 
	public void insertPlayerTest() {
		try {
			assertEquals(1, dbfield.insertField(f));
			f.deleteField();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public void testInsertField() {
//		Field field = new Field("1", "Kamp", 100, 64);
//		try {
//			assertEquals(1, dbfield.insertField(field));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testInsertField1() {
//		Field field = new Field("2", "Træning", 100, 64);
//		try {
//			assertEquals(1, dbfield.insertField(field));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testInsertField2() {
//		Field field = new Field("3", "Kamp", 100, 64);
//		try {
//			assertEquals(1, dbfield.insertField(field));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testInsertField3() {
//		Field field = new Field("4", "Træning", 100, 64);
//		try {
//			assertEquals(1, dbfield.insertField(field));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testDeleteField1() {
//		assertEquals(1, dbfield.deleteField("1"));
//	}
//	
//	@Test
//	public void testDeleteField2() {
//		assertEquals(1, dbfield.deleteField("2"));
//	}
//	
//	@Test
//	public void testDeleteField3() {
//		assertEquals(1, dbfield.deleteField("3"));
//	}
//	
//	@Test
//	public void testDeleteField4() {
//		assertEquals(1, dbfield.deleteField("4"));
//	}
}
