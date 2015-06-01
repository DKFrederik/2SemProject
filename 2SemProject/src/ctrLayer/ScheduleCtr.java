package ctrLayer;

import java.sql.Date;
import java.util.List;
import java.sql.Connection;

import javax.transaction.TransactionRolledbackException;

import modelLayer.*;
import dbLayer.*;

/**
 * 
 * @author peter, claus, frederik, nichlas
 * Ctr class handling the creation and deletion of schedules. 
 */
public class ScheduleCtr {

	private static ScheduleCtr instance;
	private TeamCtr tCtr;
	private PersonCtr pCtr;
	private FieldCtr fCtr;
	private Schedule schedule;
	private DBSchedule schDB;
	private DBAppointment appDB;

	private ScheduleCtr() {
		this.tCtr = TeamCtr.getInstance();
		this.fCtr = FieldCtr.getInstance();
		this.pCtr = PersonCtr.getInstance();
		this.appDB = new DBAppointment();
		schDB = new DBSchedule();
	}
	/**
	 * 
	 * @return an instance of the ScheduleCtr.
	 */
	public static ScheduleCtr getInstance() {
		if (instance == null) {
			instance = new ScheduleCtr();
		}
		return instance;
	}
	/**
	 * Instantiates the Schedule field in the ScheduleCtr to a Schedule object using a date.
	 * @param date The date for the schedule.
	 * @return -1 if failed. 1 if succeeded.
	 */
	public int createSchedule(java.sql.Date date) {
		int returnInt = -1;

		if(schDB.findSchedule(date,false) == null)
		{
			schedule = new Schedule(fCtr.getFields(), date);
			returnInt = 1;
		}
		return returnInt;
	}
	/**
	 * Adds a Team to the current Schedule in the ScheduleCtr.
	 * @param teamNumber
	 * @return True or false to indicate success.
	 */
	public boolean addTeam(String teamNumber) {
		try {
			if(tCtr.findTeam(teamNumber) != null) {
				schedule.addAppointment(tCtr.findTeam(teamNumber));
				return true;
			}
			else {
				return false;
			}
		} catch (NullPointerException e) {
			System.out.println("Ingen Schedule er i �jeblikket aktiv.");
			return false;
		}
	}
	/**
	 * Runs the methods for creating the graph and assigning fields and timeslots on the current Schedule.
	 */
	public void makeSchedule() {
		try {
			schedule.createGraph();
			schedule.makeSchedule();
		} catch (NullPointerException e) {
			System.out.println("Ingen Schedule er i �jeblikket aktiv.");
			throw new NullPointerException();
		}
	}
	/**
	 * Attempts to save the schedule to the database.
	 * @throws TransactionRolledbackException
	 */
	public void completeSchedule() throws TransactionRolledbackException {
		try {
			DBConnection.startTransaction();
			int size = schedule.getAppointments().size();
			this.schDB.insertSchedule(schedule);
			int scheduleId = schDB.findMaxId();
			System.out.println(size);
			System.out.println(scheduleId);
			for (int i = 0; i < size; i++) {
				appDB.insertAppointment(schedule.getAppointments().get(i),
						scheduleId);
				System.out.println(schedule.getAppointments().get(i).getTeam()
						.getTeamNumber());
			}
			this.schedule = null;
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new TransactionRolledbackException("Transaction rolled back");
		}
	}
	/**
	 * Returns the current schedule being worked on in the ScheduleCtr.
	 * @return the current Schedule.
	 */
	public Schedule getCurrentSchedule() {
		return this.schedule;
	}
	/**
	 * Delete a schedule from the database with the matching date.
	 * @param date The date of the Schedule to be deleted.
	 * @throws TransactionRolledbackException
	 */
	public void deleteSchedule(Date date) throws TransactionRolledbackException {
		try {
			DBConnection.startTransaction();
			schDB.deleteSchedule(date);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new TransactionRolledbackException("Transaction rolled back");
		}
	}
	/**
	 * Returns a Schedule from the database that exist for that date.
	 * @param date the schedules date.
	 * @param retrieveAssociation Determines if associations are to be build with the object.
	 * @return a Schedule or null.
	 */
	public Schedule getSchedule(Date date, boolean retrieveAssociation)
	{
		return schDB.findSchedule(date,retrieveAssociation);
	}
	/**
	 * Clears the schedule by setting it to null.
	 */
	public void removeCurrentSchedule()
	{
		this.schedule = null;
	}
	/**
	 * Set the schedule creator to the Person with that phone no if it matches an existing one.
	 * @param phone phone number of a Person.
	 * @return True or false to indicate success.
	 */
	public boolean setCreator(String phone) {
		Person p = pCtr.findPerson(phone);
		if(p instanceof Staff) {
			schedule.setCreator(p);
			return true;
		}
		else {
			return false;
		}
	}
}
