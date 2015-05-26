package ctrLayer;

import modelLayer.Manager;
import modelLayer.Person;
import modelLayer.Player;
import modelLayer.Referee;
import modelLayer.Staff;
import modelLayer.TeamLeader;
import dbLayer.DBPerson;

public class PersonCtr {

	private static PersonCtr instance = null;
	private DBPerson dbP;

	private PersonCtr() {
		dbP = new DBPerson();
	}

	public static PersonCtr getInstance() {
		if (instance == null) {
			instance = new PersonCtr();
		}

		return instance;
	}
	
	public boolean createManager(String fname, String lname, String email,
			String phoneno, String zipcode, String username, String password, double salary)
			throws Exception {

		if(0 < dbP.insertPerson(new Manager(fname, lname, email, phoneno, zipcode, username, password, salary))) {
			return true;
		}
		else {
			return false;
		}
	}


	public boolean createPlayer(String fname, String lname, String email,
			String phoneno, String zipcode, String sbDay, String position)
			throws Exception {

		if(0 < dbP.insertPerson(new Player(fname, lname, email, phoneno, zipcode,
				sbDay, position))) {
			return true;
		}
		else {
			return false;
		}

	}
	
	public Person findPerson(String phoneno) {
		return dbP.findPerson(phoneno, true);
	}
	
	public boolean updateReferee(String fname, String lname, String email,
			String phoneno, String zipcode, String oldPhone) {
		Referee r = new Referee(fname, lname, email, phoneno, zipcode);
		
		if(0 < dbP.updatePerson(r, oldPhone)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean updateStaff(String fname, String lname, String email,
			String phoneno, String zipcode, String username, String password, String oldPhone) {
		Staff s = new Staff(fname, lname, email, phoneno, zipcode, username, password);
		
		if(0 < dbP.updatePerson(s, oldPhone)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean updateTeamLeader(String fname, String lname, String email,
			String phoneno, String zipcode, String username, String password, String oldPhone) {
		TeamLeader tl = new TeamLeader(fname, lname, email, phoneno, zipcode, username, password);
		
		if(0 < dbP.updatePerson(tl, oldPhone)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean updateManager(String fname, String lname, String email,
			String phoneno, String zipcode, String username, String password,
			double salary, String oldPhone) {
		Manager m = new Manager(fname, lname, email, phoneno, zipcode, username, password, salary);
		
		if(0 < dbP.updatePerson(m, oldPhone)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean updatePlayer(String fname, String lname, String email,
			String phoneno, String zipcode, String sbDay, String position,
			String oldPhone) {
		Player p = new Player(fname, lname, email, phoneno, zipcode, sbDay,
				position);
		if (0 < dbP.updatePerson(p, oldPhone)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deletePerson(String phoneno) {
		if (0 < dbP.deletePerson(phoneno)) {
			return true;
		} else {
			return false;
		}
	}
}
