package ctrLayer;
import java.sql.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date utilDate = new java.util.Date();
		try {
			utilDate = format.parse("2022-02-02");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date((2200-1900), 8, 22);
		sCtr.createSchedule(sqlDate);
		System.out.println(sCtr.createSchedule(sqlDate) + " " + sCtr.getCurrentSchedule().getDate());
		sCtr.addTeam("a");
		sCtr.addTeam("b");
		sCtr.addTeam("c");
		sCtr.addTeam("d");
		sCtr.addTeam("e");
		sCtr.addTeam("f");
		sCtr.getCurrentSchedule().setCreator(pDB.findPerson("00000076", true));
		
		sCtr.makeSchedule();
		
		sCtr.completeSchedule();
		System.out.println(sCtr.getSchedule(sqlDate).getCreator().getFname());
	}

}
