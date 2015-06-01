package ctrLayer;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.TransactionRolledbackException;

import modelLayer.Manager;
import modelLayer.Person;
import modelLayer.Player;
import modelLayer.Referee;
import modelLayer.Staff;
import modelLayer.TeamLeader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dbLayer.DBPerson;

public class ScheduleCtrTest {
	private static java.util.Date utilDate;
	private static java.sql.Date testDate;
	private static ScheduleCtr schCtr;
	private static TeamCtr teamCtr;
	private static Person person;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testDate = new java.sql.Date(223,5,20);
		schCtr = ScheduleCtr.getInstance();
		teamCtr = TeamCtr.getInstance();
		person = new Person();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//schCtr.deleteSchedule(testDate);
	}

	@Before
	public void setUp() throws Exception {
		schCtr.createSchedule(testDate);

	}

	@After
	public void tearDown() throws Exception {
		schCtr.removeCurrentSchedule();
	}
	

	@Test
	public void testCreateSchedule() {
		assertNotNull(schCtr.getCurrentSchedule());
	}

	@Test
	public void testAddTeam() {
		schCtr.addTeam("a");
		assertFalse(schCtr.getCurrentSchedule().getAppointments().isEmpty());
	}

	@Test
	public void testMakeSchedule() {
		schCtr.addTeam("a");
		schCtr.addTeam("b");
		schCtr.makeSchedule();
		assertNotNull(schCtr.getCurrentSchedule().getAppointments().get(0).getField());
	}

	@Test
	public void testCompleteSchedule() {
		schCtr.addTeam("a");
		schCtr.addTeam("b");
		schCtr.getCurrentSchedule().setCreator(person);
		schCtr.makeSchedule();
		try {
			schCtr.completeSchedule();
		} catch (TransactionRolledbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(schCtr.getSchedule(testDate, false));
	}

}
