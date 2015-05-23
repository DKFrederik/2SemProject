package ctrLayer;
import java.sql.Date;
import java.util.Calendar;

import dbLayer.DBPerson;
import modelLayer.*;

public class Main {

	public static void main(String[] args) {
/*		Player p = new Player("Nichlas", "Pedersen", "Test@email.com", "41822949", "", "F");
		Calendar cal = Calendar.getInstance();
		cal.setTime(p.getBDay());
		System.out.println("Year " + cal.get(Calendar.YEAR));
		System.out.println("Month " + (cal.get(Calendar.MONTH) + 1));
		System.out.println("Day " + cal.get(Calendar.DAY_OF_MONTH));
		
		System.out.println(p.getAge());
		System.out.println(p.getBDay());*/
		
		ScheduleCtr sCtr = new ScheduleCtr();
		DBPerson pDB = new DBPerson();
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		sCtr.createSchedule(sqlDate);
		System.out.println(sCtr.createSchedule(sqlDate));
		sCtr.addTeam("a");
		sCtr.addTeam("b");
		sCtr.addTeam("c");
		sCtr.addTeam("d");
		sCtr.addTeam("e");
		sCtr.addTeam("f");
		sCtr.getCurrentSchedule().setCreator(pDB.findPerson("00000076", true));
		
		sCtr.makeSchedule();
		
		sCtr.completeSchedule();
	}

}
