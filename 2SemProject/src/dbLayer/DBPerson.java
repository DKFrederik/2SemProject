package dbLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * DBPerson.java
 * 
 * @author Peter, Frederik, Claus og Nichlas.
 * @version 20.03.2015
 *
 */
public class DBPerson {
	private Connection con;

	/**
	 * Constructor for DBPerson() class. 
	 */
	public DBPerson() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * 
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return An ArrayList of Person objects.
	 */
	public ArrayList<Person> getAllPersons(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	/**
	 * 
	 * @param phone The phone number of the person that you wish to find.
	 * @param retriveAssoc.iation Determines if associations should be retrieved or not. 
	 * @return A Person Object if found, null if not.
	 */
	public Person findPerson(String phone, boolean retriveAssociation) {
		String wClause = "  phoneno = '" + phone + "'";
		return singleWhere(wClause, retriveAssociation);
	}
	
	/**
	 * 
	 * @param pro The person that is to be inserted
	 * @return 
	 * @throws Exception 
	 * firstName
	 * lastName
	 * email
	 * phone
	 * zipCode 
	 * city
	 * Player: birthday, position
	 * Manager: salary
	 * staff: username, password
	 * TYPES: Player, Staff --> TeamLeader, Manager
	 */
	public int insertPerson(Person p) throws Exception {

		int rc = -1;
		String query = "INSERT INTO Person(firstname, lastname, email, phoneno, zipcode, birthday, position, username, password, salary, type)  VALUES('"
				+ p.getFname() + "','" + p.getLname() + "','"
				+ p.getEmail() + "','" + p.getPhone() + "','"
				+ p.getZipcode() + "','";
		if(p instanceof Player) {
			Player pl = (Player) p;
			query += pl.getBDay() + "','" + pl.getPosition() + "','" 
					+ "null" + "','" + "null" + "','"
					+ "-1" + "','" + "P";
		}
		
		if(p instanceof TeamLeader) {
			TeamLeader tl = (TeamLeader) p;
			query += "null" + "','" + "null" + "','"
					+ tl.getUsername() + "','" + tl.getPassword() + "','" 
					+ "-1" + "','" + "T";
		}
		else if(p instanceof Manager) {
			Manager m = (Manager) p;
			query += "null" + "','" + "null" + "','" 
					+ m.getUsername() + "','" + m.getPassword() + "','"
					+ m.getSalary() + "','" + "M";
		}
		else if(p instanceof Staff) {
			Staff s = (Staff) p;
			query += "null" + "','" + "null" + "','" 
					+ s.getUsername() + "','" + s.getPassword() + "','"
					+ "-1" + "','" + "S";
		}
		if(p instanceof Referee) {
			Referee r = (Referee) p;
			query += "null" + "','" + "null" + "','"
					+ "null" + "','" + "null" + "','" 
					+ "-1" + "','" + "R";
		}
		
		query += "')";
		System.out.println("insert : " + query);
		try { // insert new Person
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Person not created");
			System.out.println(ex.getErrorCode());
			System.out.println(ex.getMessage());
			throw new Exception("Person is not inserted correct");
		}
		return (rc);
	}

	/**
	 * 
	 * @param cus The customer object that is to be updated in the database.
	 * @return
	 */
	public int updatePerson(Person p, String phone) {
		Person personObj = p;
		int rc = -1;

		String query = "UPDATE Person SET " 
				+ "fname ='" + personObj.getFname() + "', " 
				+ "lname ='" + personObj.getLname() + "', " 
				+ "email ='" + personObj.getEmail() + "', " 
				+ "phone ='" + personObj.getPhone() + "', ";
		if(p instanceof Player) {
			Player pl = (Player) personObj;
			query += pl.getBDay() + "','" + pl.getPosition() + "','" 
					+ "null" + "','" + "null" + "','"
					+ "-1" + "','" + "P";
		}
		
		if(p instanceof TeamLeader) {
			TeamLeader tl = (TeamLeader) personObj;
			query += "null" + "','" + "null" + "','"
					+ tl.getUsername() + "','" + tl.getPassword() + "','" 
					+ "-1" + "','" + "T";
		}
		else if(p instanceof Manager) {
			Manager m = (Manager) personObj;
			query += "null" + "','" + "null" + "','" 
					+ m.getUsername() + "','" + m.getPassword() + "','"
					+ m.getSalary() + "','" + "M";
		}
		else if(p instanceof Staff) {
			Staff s = (Staff) personObj;
			query += "null" + "','" + "null" + "','" 
					+ s.getUsername() + "','" + s.getPassword() + "','"
					+ "-1" + "','" + "S";
		}
		if(p instanceof Referee) {
			Referee r = (Referee) personObj;
			query += "null" + "','" + "null" + "','"
					+ "null" + "','" + "null" + "','" 
					+ "-1" + "','" + "R";
		}

		query += " WHERE phone = '" + phone + "'";
		System.out.println("Update query:" + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		
		catch (Exception ex) {
			System.out.println("Update exception in person db: " + ex);
		}
		return (rc);
	}

	public int deletePerson(String phone) {
		int rc = -1;

		String query = "DELETE FROM Person WHERE phoneno = '" + phone + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Delete exception in person db: " + ex);
		}
		return (rc);
	}

	/**
	 * 
	 * @param wClause where clause for the query.
	 * @param retrieveAssociation whether or not to retrieve associations.
	 * @return An ArrayList of persons.
	 */
	private ArrayList<Person> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Person> list = new ArrayList<Person>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Person personObj = new Person();
				personObj = buildPerson(results);
				list.add(personObj);
			}// end while
			stmt.close();
			
			// Get suppliers around here

		}
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	private Person singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Person personObj = new Person();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				personObj = buildPerson(results);
				stmt.close();
				
				// get supplier should be implemented around here.
				
			} else {
				personObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return personObj;
	}

	/**
	 * Builds a SQL query
	 * @param wClause where clause.
	 * @return the build SQL query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT fname, lname, email, phone, birthday, position FROM Person";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	/**
	 * Builds a person from a ResultSet.
	 * 
	 * @param results the results returned by the DMBS
	 * @return a person
	 */
	private Person buildPerson(ResultSet results){
		Person personObj = new Person();
		
		try {
			if(results.getString("type").equals("P")) {
				buildPlayer(results);
			}
			else if(results.getString("type").equals("T")) {
				buildTeamLeader(results);
			}
			else if(results.getString("type").equals("M")) {
				buildManager(results);
			}
			else if(results.getString("type").equals("S")) {
				buildStaff(results);
			}
			else if(results.getString("type").equals("R")) {
				buildReferee(results);
			}

		}
		try 
		{
			personObj.setFname(results.getString("fname"));
			personObj.setLname(results.getString("lname"));
			personObj.setEmail(results.getString("email"));
			personObj.setPhone(results.getString("phone"));
			personObj.setZipcode(results.getString("zipcode"));
			personObj.setCity(results.getString(""));
			personObj.stringSetBDay(results.getString("birthday"));
			personObj.setPosition(results.getString("position"));
		} 
		catch (Exception e) {
			System.out.println("error in building the customer object");
		}
		return personObj;
	}
	
	private Player buildPlayer(ResultSet results) {
		Player p = new Player();
		
		return p;
	}
	private TeamLeader buildTeamLeader(ResultSet results) {
		TeamLeader tl = new TeamLeader();
	}
	
	private Manager buildManager(ResultSet results) {
		
	}
	
	private Staff buildStaff(ResultSet results) {
		
	}
	
	private Referee buildReferee(ResultSet results) {
		
	}

}

