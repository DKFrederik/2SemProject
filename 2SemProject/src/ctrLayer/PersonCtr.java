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
	
	/**
	 * Creates an instance of the PersonCtr.
	 */

	public static PersonCtr getInstance() {
		if (instance == null) {
			instance = new PersonCtr();
		}

		return instance;
	}
	
	/**
	 * Inserts a Manager into the DB
	 * 
	 * @param fname
	 *            the first name of the manager.
	 * @param lname
	 *            the last name of the manager.
	 * @param email
	 *            the email of the manager.
	 * @param phoneno
	 *            the phonenumber of the manager.
	 * @param zipcode
	 *            the zipcode of the manager.
	 * @param username
	 *			  the username assigned to the manager.
	 * @param password
	 *			  the password assigned to the manager.
	 * @param salary
	 *			  the salary of the manager.
	 * @return true or false depending on success.
	 */
	
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
	
	/**
	 * Inserts a Player into the DB
	 * 
	 * @param fname
	 *            the first name of the player.
	 * @param lname
	 *            the last name of the player.
	 * @param email
	 *            the email of the player.
	 * @param phoneno
	 *            the phonenumber of the player.
	 * @param zipcode
	 *            the zipcode of the player.
	 * @param sbDay
	 *			  the birthday of the player.
	 * @param position
	 *			  the position assigned to the player.
	 * @return true or false depending on success.
	 */
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
	/**
	 * Inserts a TeamLeader into the DB
	 * 
	 * @param fname
	 *            the first name of the teamleader.
	 * @param lname
	 *            the last name of the teamleader.
	 * @param email
	 *            the email of the teamleader.
	 * @param phoneno
	 *            the phonenumber of the teamleader.
	 * @param zipcode
	 *            the zipcode of the teamleader.
	 * @param username
	 *			  the username assigned to the teamleader.
	 * @param password
	 *			  the password assigned to the teamleader.
	 * @param salary
	 *			  the salary of the teamleader.
	 * @return true or false depending on success.
	 */
	public boolean createTeamLeader(String fname, String lname, String email,
			String phoneno, String zipcode, String username, String password)
			throws Exception {

		if(0 < dbP.insertPerson(new TeamLeader(fname, lname, email, phoneno, zipcode, username,
				password))) {
			return true;
		}
		else {
			return false;
		}

	}
	
	/**
	 * Inserts a Referee into the DB
	 * 
	 * @param fname
	 *            the first name of the referee.
	 * @param lname
	 *            the last name of the referee.
	 * @param email
	 *            the email of the referee.
	 * @param phoneno
	 *            the phonenumber of the referee.
	 * @param zipcode
	 *            the zipcode of the referee.

	 * @return true or false depending on success.
	 */
	public boolean createReferee(String fname, String lname, String email,
			String phoneno, String zipcode)
			throws Exception {

		if(0 < dbP.insertPerson(new Referee(fname, lname, email, phoneno, zipcode))) {
			return true;
		}
		else {
			return false;
		}

	}
	
	/**
	 * Finds a person from the DB.
	 * @param phoneno
	 * 			the parameter used to finding a person.
	 * @return a person.
	 */
	public Person findPerson(String phoneno) {
		return dbP.findPerson(phoneno, true);
	}
	
	/**
	 * Updates a referee in the DB
	 * @param fname
	 * 			updates the first name
	 * @param lname
	 * 			updates the last name
	 * @param email
	 * 			updates the email
	 * @param phoneno
	 * 			updates the phoneno
	 * @param zipcode
	 * 			updates the zipcode
	 * @param oldPhone
	 * 			uses the phoneno to update
	 * @return true or false depending on success.
	 */
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
	
/*	public boolean updateStaff(String fname, String lname, String email,
			String phoneno, String zipcode, String username, String password, String oldPhone) {
		Staff s = new Staff(fname, lname, email, phoneno, zipcode, username, password);
		
		if(0 < dbP.updatePerson(s, oldPhone)) {
			return true;
		}
		else {
			return false;
		}
	}*/
	
	/**
	 * Updates a teamleader in the DB
	 * @param fname
	 * 			updates the first name
	 * @param lname
	 * 			updates the last name
	 * @param email
	 * 			updates the email
	 * @param phoneno
	 * 			updates the phoneno
	 * @param zipcode
	 * 			updates the zipcode
	 * @param username
	 * 			updates the username
	 * @param password
	 * 			updates the password
	 * @param oldPhone
	 * 			uses the phoneno to update
	 * @return true or false depending on success.
	 */
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

	/**
	 * Updates a manager in the DB.
	 * @param fname
	 * 			updates the first name
	 * @param lname
	 * 			updates the last name
	 * @param email
	 * 			updates the email
	 * @param phoneno
	 * 			updates the phoneno
	 * @param zipcode
	 * 			updates the zipcode
	 * @param username
	 * 			updates the username
	 * @param password
	 * 			updates the password
	 * @param salary
	 * 			updates the salary
	 * @param oldPhone
	 * 			uses the phoneno to update
	 * @return true or false depending on success.
	 */
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

	/**
	 * Updates a player in the DB.
	 * @param fname
	 * 			updates the first name
	 * @param lname
	 * 			updates the last name
	 * @param email
	 * 			updates the email
	 * @param phoneno
	 * 			updates the phoneno
	 * @param zipcode
	 * 			updates the zipcode
	 * @param sbDay
	 * 			updates the sbDay
	 * @param position
	 * 			updates the position
	 * @param oldPhone
	 * 			uses the phoneno to update
	 * @return true or false depending on success.
	 */
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

	/**
	 * Deletes a person from the DB.
	 * @param phoneno
	 * 			used to finding the person
	 * @return true or false depending on success.
	 */
	public boolean deletePerson(String phoneno) {
		if (0 < dbP.deletePerson(phoneno)) {
			return true;
		} else {
			return false;
		}
	}
}
