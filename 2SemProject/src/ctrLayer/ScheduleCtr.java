package ctrLayer;

import java.sql.Date;
import java.util.List;
import java.sql.Connection;

import javax.transaction.TransactionRolledbackException;

import modelLayer.*;
import dbLayer.*;

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

	public static ScheduleCtr getInstance() {
		if (instance == null) {
			instance = new ScheduleCtr();
		}
		return instance;
	}

	public int createSchedule(java.sql.Date date) {
		int returnInt = -1;

		if(schDB.findSchedule(date,false) == null)
		{
			schedule = new Schedule(fCtr.getFields(), date);
			returnInt = 1;
		}
		return returnInt;
	}

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
			System.out.println("Ingen Schedule er i øjeblikket aktiv.");
			return false;
		}
	}

	public void makeSchedule() {
		try {
			schedule.createGraph();
			schedule.makeSchedule();
		} catch (NullPointerException e) {
			System.out.println("Ingen Schedule er i øjeblikket aktiv.");
			throw new NullPointerException();
		}
	}

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

	public Schedule getCurrentSchedule() {
		return this.schedule;
	}

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
	
	public Schedule getSchedule(Date date, boolean retrieveAssociation)
	{
		return schDB.findSchedule(date,retrieveAssociation);
	}
	
	public void removeCurrentSchedule()
	{
		this.schedule = null;
	}
	
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
