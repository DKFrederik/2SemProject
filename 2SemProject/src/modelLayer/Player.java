package modelLayer;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Player extends Person{
	
	private Date bDay;
	private String position;
	
	public Player(String fname, String lname, String email, String phone, String sbDay, String position) {
		super(fname, lname, email, phone);
		
		setBDay(sbDay);
		this.position = position;
	}
	
	public Player(){
		
	}

	public Date getBDay() {
		return bDay;
	}

	public void setBDay(String sbDay) {
		
		if (sbDay != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			try {
				bDay = sdf.parse(sbDay);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else {
			throw new NullPointerException("Bday is Null");
		}
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public int getAge() {
		Calendar dob = Calendar.getInstance();
		dob.setTime(bDay);
		
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
			age--;
		return age;
	}
}