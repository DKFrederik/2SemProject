package ctrLayer;

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
}
