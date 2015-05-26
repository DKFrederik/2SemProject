package ctrLayer;

import static org.junit.Assert.*;

import java.util.List;

import modelLayer.Manager;
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
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testDate = new java.sql.Date(223,5,20);
		schCtr = ScheduleCtr.getInstance();
		teamCtr = TeamCtr.getInstance();
		schCtr.createSchedule(testDate);
		schCtr.completeSchedule();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//schCtr.deleteSchedule(testDate);
	}

	@Before
	public void setUp() throws Exception {
		schCtr.createSchedule(testDate);
		schCtr.completeSchedule();
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testCreateSchedule() {
		schCtr.createSchedule(testDate);
		schCtr.completeSchedule();
		//assert(schCtr.createSchedule(testDate));
	}

	@Test
	public void testAddTeam() {
		schCtr.addTeam("a");
		assertFalse(schCtr.getCurrentSchedule().getAppointments().isEmpty());
	}

	@Test
	public void testMakeSchedule() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompleteSchedule() {
		fail("Not yet implemented");
	}

}
