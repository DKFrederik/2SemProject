package ctrLayer;

import modelLayer.Person;
import modelLayer.Player;
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
	
	public void createPlayer(String fname, String lname, String email, String phoneno, String zipcode, 
			String sbDay, String position) throws Exception {

		dbP.insertPerson(new Player(fname, lname, email, phoneno, zipcode, sbDay, position));

	}
	
	public Person findPerson(String phoneno) {
		return dbP.findPerson(phoneno, true);
	}
	
	public boolean updatePerson(String fname, String lname, String email, String phoneno, String zipcode, 
			String sbDay, String position, char type, String oldPhone) {
		Person p = null;
		if(type == 'T') {
			//Teamleader
		}
		else if(type == 'M') {
			//Manager
		}
		else if(type == 'S') {
			//STAFF
		}
		else if(type == 'P') {
			p = new Player(fname, lname, email, phoneno, zipcode, sbDay, position);
		}
		if(0 < dbP.updatePerson(p, oldPhone)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean deletePerson(String phoneno) {
		if(0 < dbP.deletePerson(phoneno)) {
			return true;
		}
		else {
			return false;
		}
	}
}
