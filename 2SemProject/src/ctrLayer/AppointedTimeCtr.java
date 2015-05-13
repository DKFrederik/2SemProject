package ctrLayer;

import java.util.List;

import modelLayer.*;

public class AppointedTimeCtr {
private static AppointedTimeCtr instance;
private TeamCtr tCtr;
	
	private AppointedTimeCtr()
	{
		this.tCtr = TeamCtr.getInstance();
	}

	public static AppointedTimeCtr getInstance(){
		if(instance == null)
		{
			instance = new AppointedTimeCtr();
		}
		return instance;
	}
	
	public void createAppointedTime()
	{

	}
	
	public void updateAppointedTime()
	{
			
	}
	
	public AppointedTime findAppointedTime()
	{
		return null;
	}
	
	
}
