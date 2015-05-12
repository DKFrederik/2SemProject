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
	private Referee r;
	private TeamLeader tl;
	private Manager m;
	private Staff s;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void insertRefTest() {
		try {
			assertEquals(1, dbP.insertPerson(r));
			dbP.deletePerson(r.getPhone());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void insertTeamLeaderTest() {
		try {
			assertEquals(1, dbP.insertPerson(tl));
			dbP.deletePerson(tl.getPhone());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void insertManagerTest() {
		try {
			assertEquals(1, dbP.insertPerson(m));
			dbP.deletePerson(m.getPhone());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void insertStaffTest() {
		try {
			assertEquals(1, dbP.insertPerson(s));
			dbP.deletePerson(s.getPhone());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbP.updatePerson(pl, pl.getPhone());
	}

	@Test
	public void buildPlayerTest() {
		try {
			dbP.insertPerson(pl);
			Player pl2 = (Player) dbP.findPerson(pl.getPhone(), false);
			System.out.println(pl2.getFname());
			System.out.println(pl2.getBDay());
			dbP.deletePerson(pl.getPhone());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
