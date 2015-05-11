package ctrLayer;
import java.util.Calendar;

import modelLayer.*;

public class Main {

	public static void main(String[] args) {
		Player p = new Player("Nichlas", "Pedersen", "Test@email.com", "41822949", "", "F");
		Calendar cal = Calendar.getInstance();
		cal.setTime(p.getBDay());
		System.out.println("Year " + cal.get(Calendar.YEAR));
		System.out.println("Month " + (cal.get(Calendar.MONTH) + 1));
		System.out.println("Day " + cal.get(Calendar.DAY_OF_MONTH));
		
		System.out.println(p.getAge());
		System.out.println(p.getBDay());

	}

}
