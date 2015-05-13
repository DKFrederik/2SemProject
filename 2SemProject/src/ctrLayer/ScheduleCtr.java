package ctrLayer;

import java.util.List;

import modelLayer.*;

public class ScheduleCtr {
private static ScheduleCtr instance;
private TeamCtr tCtr;
	
	private ScheduleCtr()
	{
		this.tCtr = TeamCtr.getInstance();
	}

	public static ScheduleCtr getInstance(){
		if(instance == null)
		{
			instance = new ScheduleCtr();
		}
		return instance;
	}
	
	public void createSchedule()
	{

	}
	
	public void updateSchedule()
	{
			
	}
	
	public Schedule findSchedule()
	{
		return null;
	}
	
	
}
