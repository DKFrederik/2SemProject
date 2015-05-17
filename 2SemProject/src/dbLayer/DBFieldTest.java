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
	
//	@Before
//	public void setUp() throws Exception {
//		
//	}
	
	@Before
	public void setUp() throws Exception {
		dbfield = new DBField();
		f = new Field("1", "Kamp", 100, 64);
//		dbfield = new DBField("1", "kamp", 100, 64);
//		pl = new Player("Nichlas", "Pedersen", "Test@email.com", "11223344", "9800", "1993-12-30", "F");
//		r = new Referee("Ref", "Eree", "joe@email.com", "22334455", "9800");
//		tl = new TeamLeader("Team", "Leader", "Leading@email.com", "33445566", "9800", "Teamiie", "123456");
//		m = new Manager("Man", "Ager", "Man@email.com", "44556677", "9800", "Mana", "123456", 12000);
//		s = new Staff("Staff", "Orb", "Mailing@email.com", "55667788", "9800", "Staff", "123456");
//		dbP = new DBPerson();
	}
	

	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	public void insertPlayerTest() {
		try {
			assertEquals(1, dbfield.insertField(f));
			f.deleteField();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	@Test
	public void testDeleteField1() {
		assertEquals(1, dbfield.deleteField("1"));
	}
	
	@Test
	public void testDeleteField2() {
		assertEquals(1, dbfield.deleteField("2"));
	}
	
	@Test
	public void testDeleteField3() {
		assertEquals(1, dbfield.deleteField("3"));
	}
	
	@Test
	public void testDeleteField4() {
		assertEquals(1, dbfield.deleteField("4"));
	}
}
