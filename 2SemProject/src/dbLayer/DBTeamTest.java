package dbLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelLayer.Team;

public class DBTeamTest {
	
	DBTeam dbt;
	Team t; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbt = new DBTeam();
		t = new Team("1234");
		t.setLeague(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void insertionTest() {
		try {
			assertEquals(1, dbt.insertTeam(t));
			dbt.deleteTeam(t.getNumber());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findTeamTest() {
		try {
			dbt.insertTeam(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Team t2 = dbt.findTeam(t.getNumber(), false);
		assertTrue(t2.getNumber().equals(t.getNumber()));
		assertEquals(1, dbt.deleteTeam(t2.getNumber()));
		
	}
	
	@Test
	public void updateTeamTest() {
		try {
			dbt.insertTeam(t);
			t.setNumber("4321");
			dbt.updateTeam(t, "1234");
			assertNotNull(dbt.findTeam("4321", false));
			dbt.deleteTeam("4321");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void findAllTeam() {
		Team t2 = new Team("4321");
		t2.setLeague(4);
		try {
			dbt.insertTeam(t2);
			dbt.insertTeam(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Team> teams = dbt.getAllTeams(false);
		assertEquals(2, teams.size());
		
		dbt.deleteTeam(t.getNumber());
		dbt.deleteTeam(t2.getNumber());
	}

}
