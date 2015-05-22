package ctrLayer;

import modelLayer.Person;
import modelLayer.Player;
import dbLayer.DBPerson;

public class PlayerCtr {
	
	private static PlayerCtr instance = null;
	private DBPerson dbP;
	
	private PlayerCtr() {
		dbP = new DBPerson();
	}
	public static PlayerCtr getInstance() {
		if (instance == null) {
			instance = new PlayerCtr();
		}

		return instance;
	}
	
	public void createPerson(String fname, String lname, String email, String phoneno, String zipcode, 
			String sbDay, String position) throws Exception {

		dbP.insertPerson(new Player(fname, lname, email, phoneno, zipcode, sbDay, position));
		
	}
	
	public Person findPerson(String phoneno) {
		return dbP.findPerson(phoneno, true);
	}
	
	public boolean updatePerson(Person p, String oldPhone) {
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
