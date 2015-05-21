package ctrLayer;

import java.sql.Date;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

public class ScheduleCtr {
	
private static ScheduleCtr instance;
private TeamCtr tCtr;
private FieldCtr fCtr;
Schedule schedule;
private DBSchedule schDB;
	
	private ScheduleCtr()
	{
		this.tCtr = TeamCtr.getInstance();
		this.fCtr = FieldCtr.getInstance();
		schDB = new DBSchedule();
	}

	public static ScheduleCtr getInstance(){
		if(instance == null)
		{
			instance = new ScheduleCtr();
		}
		return instance;
	}
	
	public int createSchedule(Date date)
	{
		int returnInt = 0;
		if(schDB.findSchedule(date) == null)
		{
			schedule = new Schedule(fCtr.getFields());
			returnInt = 1;
		}
		return returnInt;
	}
	
	public void addTeam(String teamNumber)
	{	
		schedule.addAppointment(tCtr.findTeam(teamNumber));
	}
	
	public void makeSchedule()
	{
		schedule.makeSchedule();
	}
	
	public void completeSchedule()
	{
		schDB.insertSchedule(this.schedule);
	}
}
