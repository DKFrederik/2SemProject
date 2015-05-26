package ctrLayer;

import java.sql.Date;
import java.util.List;
import java.sql.Connection;

import modelLayer.*;
import dbLayer.*;

public class ScheduleCtr {

	private static ScheduleCtr instance;
	private TeamCtr tCtr;
	private FieldCtr fCtr;
	private Schedule schedule;
	private DBSchedule schDB;
	private DBAppointment appDB;

	private ScheduleCtr() {
		this.tCtr = TeamCtr.getInstance();
		this.fCtr = FieldCtr.getInstance();
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

	public void addTeam(String teamNumber) {
		try {
			schedule.addAppointment(tCtr.findTeam(teamNumber));
		} catch (NullPointerException e) {
			System.out.println("Ingen Schedule er i øjeblikket aktiv.");
		}
	}

	public void makeSchedule() {
		try {
			schedule.createGraph();
			schedule.makeSchedule();
		} catch (NullPointerException e) {
			System.out.println("Ingen Schedule er i øjeblikket aktiv.");
		}
	}

	public void completeSchedule() {
		try {
			DBConnection.startTransaction();
			int size = schedule.getAppointments().size();
			schDB.insertSchedule(schedule);

			int scheduleId = schDB.findMaxId();
			Appointment app = schedule.getAppointments().get(1);
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
		}
	}

	public Schedule getCurrentSchedule() {
		return this.schedule;
	}

	public void deleteSchedule(Date date) {
		try {
			DBConnection.startTransaction();
			schDB.deleteSchedule(date);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
	}
	
	public Schedule getSchedule(Date date, boolean retrieveAssociation)
	{
		return schDB.findSchedule(date,retrieveAssociation);
	}
}
