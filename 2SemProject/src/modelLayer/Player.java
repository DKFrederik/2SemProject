package modelLayer;

import java.sql.Date;
import java.util.Calendar;

public class Player extends Person{
	
	private Date bDay;
	private String position;
	
	public Player(String fname, String lname, String email, String phone, Date bDay, String position) {
		super(fname, lname, email, phone);
		this.bDay = bDay;
		this.position = position;
	}
	
	public Player(){
		
	}

	public Date getBDay() {
		return bDay;
	}

	public void setBDay(Date bDay) {
		this.bDay = bDay;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public int getAge() {
		Calendar bday = Calendar.getInstance();
		bday.setTime(bDay);
		
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
		
		if (today.get(Calendar.DAY_OF_YEAR) < bday.get(Calendar.DAY_OF_YEAR));
			age--;
		return age;
	}

}
