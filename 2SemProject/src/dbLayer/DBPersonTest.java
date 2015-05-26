package dbLayer;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelLayer.*;

public class DBPersonTest {
	private Player pl;
	private Referee r;
	private TeamLeader tl;
	private Manager m;
	private Staff s;
	private IFPerson dbP;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		pl = new Player("Nichlas", "Pedersen", "Test@email.com", "11223344", "9800", "1993-12-30", "F");
		r = new Referee("Ref", "Eree", "joe@email.com", "22334455", "9800");
		tl = new TeamLeader("Team", "Leader", "Leading@email.com", "33445566", "9800", "Teamiie", "123456");
		m = new Manager("Man", "Ager", "Man@email.com", "44556677", "9800", "Mana", "123456", 12000);
		s = new Staff("Staff", "Orb", "Mailing@email.com", "55667788", "9800", "Staff", "123456");
		dbP = new DBPerson();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void insertPlayerTest() {
		try {
			assertEquals(1, dbP.insertPerson(pl));
			dbP.deletePerson(pl.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();

		}
	}
	@Test
	public void insertRefTest() {
		try {
			assertEquals(1, dbP.insertPerson(r));
			dbP.deletePerson(r.getPhone());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	@Test
	public void insertTeamLeaderTest() {
		try {
			assertEquals(1, dbP.insertPerson(tl));
			dbP.deletePerson(tl.getPhone());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	@Test
	public void insertManagerTest() {
		try {
			assertEquals(1, dbP.insertPerson(m));
			dbP.deletePerson(m.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	@Test
	public void insertStaffTest() {
		try {
			assertEquals(1, dbP.insertPerson(s));
			dbP.deletePerson(s.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void updatePlayer() {
		try {
			Player pl2 = new Player("Carlos", "Slim", "Test@email.com", "11112222", "9800", "1993-12-30", "F");
			dbP.insertPerson(pl2);
			pl2.setFname("John");
			assertEquals(1, dbP.updatePerson(pl2, pl2.getPhone()));
			System.out.println(pl2.getFname());
			dbP.deletePerson(pl2.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		dbP.updatePerson(pl, pl.getPhone());
	}

	@Test
	public void buildPlayerTest() {		
		try {
			dbP.insertPerson(pl);
			Player pl2 = (Player) dbP.findPerson(pl.getPhone(), false);
			assertTrue((pl2.getFname() + pl2.getLname()).equals(pl.getFname() + pl.getLname()));
			dbP.deletePerson(pl.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void buildManagerTest() {		
		try {
			dbP.insertPerson(m);
			Manager m2 = (Manager) dbP.findPerson(m.getPhone(), false);
			assertTrue((m2.getFname() + m2.getLname()).equals(m.getFname() + m.getLname()));
			dbP.deletePerson(m.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void buildStaffTest() {		
		try {
			dbP.insertPerson(s);
			Staff s2 = (Staff) dbP.findPerson(s.getPhone(), false);
			assertTrue((s2.getFname() + s2.getLname()).equals(s.getFname() + s.getLname()));
			dbP.deletePerson(s.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void buildTeamLeaderTest() {		
		try {
			dbP.insertPerson(tl);
			TeamLeader tl2 = (TeamLeader) dbP.findPerson(tl.getPhone(), false);
			assertTrue((tl2.getFname() + tl2.getLname()).equals(tl.getFname() + tl.getLname()));
			dbP.deletePerson(tl.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void buildRefereeTest() {		
		try {
			dbP.insertPerson(r);
			Referee r2 = (Referee) dbP.findPerson(r.getPhone(), false);
			assertTrue((r2.getFname() + r2.getLname()).equals(r.getFname() + r.getLname()));
			dbP.deletePerson(r.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void getAllPersonsTest() {
		try {
			dbP.insertPerson(pl);
			dbP.insertPerson(m);
			ArrayList<Person> list = new ArrayList<Person>();
			list = dbP.getAllPersons(false);
			assertTrue(pl.getFname().equals(list.get(0).getFname()));
			dbP.deletePerson(pl.getPhone());
			dbP.deletePerson(m.getPhone());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
