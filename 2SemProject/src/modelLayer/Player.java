package modelLayer;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Player extends Person{
	
	private Date bDay;
	private String position;
	private ArrayList<Team> teams;
	
	public Player(String fname, String lname, String email, String phone, String zipcode, String sbDay, String position) {
		super(fname, lname, email, phone, zipcode);
		
		teams = new ArrayList<Team>();
		stringSetBDay(sbDay);
		this.position = position;
	}
	
	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}
	
	public void addTeam(Team t) {
		teams.add(t);
	}

	public void removeTeam(Team t) {
		teams.remove(t);
	}
	
	public Player() {
		super();
	}

	public Date getBDay() {
		return bDay;
	}
	
	public void setBday(Date bDay) {
		this.bDay = bDay;
	}

	public void stringSetBDay(String sbDay) {
		if (sbDay != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			try {
		        java.util.Date parsed = sdf.parse(sbDay);
		        bDay = new java.sql.Date(parsed.getTime());
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