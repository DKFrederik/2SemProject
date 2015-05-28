package dbLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Peter, Frederik, Claus og Nichlas.
 * @version 12.05.2015. 
 * Database class for Person and childre of Person. Handles
 * insertion, delete, update and find and find all.
 */
public class DBPerson implements IFPerson {
	private Connection con;

	/**
	 * Constructor for DBPerson() class.
	 */
	public DBPerson() {
		con = DBConnection.getInstance().getDBcon();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbLayer.IFPerson#getAllPersons(boolean)
	 */
	@Override
	public ArrayList<Person> getAllPersons(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbLayer.IFPerson#findPerson(java.lang.String, boolean)
	 */
	@Override
	public Person findPerson(String phone, boolean retriveAssociation) {
		String wClause = "  phoneno = '" + phone + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbLayer.IFPerson#insertPerson(modelLayer.Person)
	 */
	@Override
	public int insertPerson(Person p) throws Exception {
		int rc = -1;
		String query = "INSERT INTO Person(firstname, lastname, email, phoneno, zipcode, birthday, position, username, password, salary, type)  VALUES('"
				+ p.getFname()
				+ "','"
				+ p.getLname()
				+ "','"
				+ p.getEmail()
				+ "','" + p.getPhone() + "','" + p.getZipcode() + "','";
		if (p instanceof Player) {
			Player pl = (Player) p;
			query += pl.getBDay() + "','" + pl.getPosition() + "','" + "null"
					+ "','" + "null" + "','" + "-1" + "','" + "P";
		} else if (p instanceof TeamLeader) {
			TeamLeader tl = (TeamLeader) p;
			query += "null" + "','" + "null" + "','" + tl.getUsername() + "','"
					+ tl.getPassword() + "','" + "-1" + "','" + "T";
		} else if (p instanceof Manager) {
			Manager m = (Manager) p;
			query += "null" + "','" + "null" + "','" + m.getUsername() + "','"
					+ m.getPassword() + "','" + m.getSalary() + "','" + "M";
		} else if (p instanceof Staff) {
			Staff s = (Staff) p;
			query += "null" + "','" + "null" + "','" + s.getUsername() + "','"
					+ s.getPassword() + "','" + "-1" + "','" + "S";
		} else if (p instanceof Referee) {
			query += "null" + "','" + "null" + "','" + "null" + "','" + "null"
					+ "','" + "-1" + "','" + "R";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbLayer.IFPerson#updatePerson(modelLayer.Person, java.lang.String)
	 */
	@Override
	public int updatePerson(Person p, String oldPhone) {
		Person personObj = p;
		int rc = -1;

		String query = "UPDATE Person SET " + "firstName = '"
				+ personObj.getFname() + "', " + "lastName = '"
				+ personObj.getLname() + "', " + "email = '"
				+ personObj.getEmail() + "', " + "phoneno = '"
				+ personObj.getPhone() + "', ";
		if (p instanceof Player) {
			Player pl = (Player) personObj;
			query += "birthday = '" + pl.getBDay() + "', " + "position = '"
					+ pl.getPosition() + "', " + "username = '" + "null"
					+ "', " + "password = '" + "null" + "', " + "salary = '"
					+ "-1" + "', " + "type = '" + "P";
		}
		if (p instanceof TeamLeader) {
			TeamLeader tl = (TeamLeader) personObj;
			query += "birthday = '" + "null" + "', " + "position = '" + "null"
					+ "', " + "username = '" + tl.getUsername() + "', "
					+ "password = '" + tl.getPassword() + "', " + "salary = '"
					+ "-1" + "', " + "type = '" + "T";
		} else if (p instanceof Manager) {
			Manager m = (Manager) personObj;
			query += "birthday = '" + "null" + "', " + "position = '" + "null"
					+ "', " + "username = '" + m.getUsername() + "', "
					+ "password = '" + m.getPassword() + "', " + "salary = '"
					+ "-1" + "', " + "type = '" + "M";
		} else if (p instanceof Staff) {
			Staff s = (Staff) personObj;
			query += "birthday = '" + "null" + "', " + "position = '" + "null"
					+ "', " + "username = '" + s.getUsername() + "', "
					+ "password = '" + s.getPassword() + "', " + "salary = '"
					+ "-1" + "', " + "type = '" + "S";
		}
		if (p instanceof Referee) {
			query += "birthday = '" + "null" + "', " + "position = '" + "null"
					+ "', " + "username = '" + "null" + "', " + "password = '"
					+ "null" + "', " + "salary = '" + "-1" + "', " + "type = '"
					+ "R";
		}

		query += "'" + " WHERE phoneno = '" + oldPhone + "'";
		System.out.println("Update query:" + query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Update exception in person db: " + ex);
		}
		return (rc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbLayer.IFPerson#deletePerson(java.lang.String)
	 */
	@Override
	public int deletePerson(String phone) {
		int rc = -1;

		String query = "DELETE FROM Person WHERE phoneno = '" + phone + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Delete exception in person db: " + ex);
		}
		return (rc);
	}

	/**
	 * Returns all Persons in the database
	 * 
	 * @param wClause
	 *            where clause for the query.
	 * @param retrieveAssociation
	 *            whether or not to retrieve associations.
	 * @return An ArrayList of persons.
	 */
	private ArrayList<Person> miscWhere(String wClause,
			boolean retrieveAssociation) {
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
				if (retrieveAssociation) {
					if (personObj instanceof Player) {
						((Player) personObj).setTeams(getTeam(
								results.getString("id"), false));
					}
				}
				list.add(personObj);
			}// end while

			stmt.close();

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Finds a single Person in the database using the wClause.
	 * 
	 * @param wClause
	 *            where clause.
	 * @param retriveAssoc
	 *            .iation Determines if associations should be retrieved or not.
	 * @return A Person Object if found, null if not.
	 */
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
				if (retrieveAssociation) {
					if (personObj instanceof Player) {
						((Player) personObj).setTeams(getTeam(
								results.getString("phoneno"), false));
					}
				}

				stmt.close();

			} else {
				personObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return personObj;
	}

	/**
	 * Builds a SQL query
	 * 
	 * @param wClause
	 *            where clause.
	 * @return the build SQL query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT id, firstName, lastName, email, phoneno"
				+ ", zipcode, birthday, position, username, password, salary, type FROM Person";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	/**
	 * Builds a person from a ResultSet.
	 * 
	 * @param results
	 *            the results returned by the DMBS
	 * @return a Person object.
	 */
	private Person buildPerson(ResultSet results) {
		Person personObj = new Person();
		IFPostalCode post = new DBPostalcode();

		try {
			if (results.getString("type").equals("P")) {
				personObj = buildPlayer(results);
			} else if (results.getString("type").equals("T")) {
				personObj = buildTeamLeader(results);
			} else if (results.getString("type").equals("M")) {
				personObj = buildManager(results);
			} else if (results.getString("type").equals("S")) {
				personObj = buildStaff(results);
			} else if (results.getString("type").equals("R")) {
				personObj = buildReferee(results);
			}

			personObj.setFname(results.getString("firstName"));
			personObj.setLname(results.getString("lastName"));
			personObj.setEmail(results.getString("email"));
			personObj.setPhone(results.getString("phoneno"));
			personObj.setZipcode(results.getString("zipcode"));
			personObj.setCity(post.findCity(results.getString("zipcode")));

		} catch (Exception e) {
			System.out.println("error in building the Person object");
			e.printStackTrace();
		}
		return personObj;
	}

	/**
	 * Builds a Player object using the ResultSet
	 * 
	 * @param results
	 *            a ResultSet used to build the object.
	 * @return A Player object.
	 */
	private Player buildPlayer(ResultSet results) {
		Player pl = new Player();
		try {
			pl.setBDay(results.getString("birthday"));
			pl.setPosition(results.getString("position"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pl;
	}

	/**
	 * Builds a TeamLeader object using the ResultSet
	 * 
	 * @param results
	 *            a ResultSet used to build the object.
	 * @return A TeamLeader object.
	 */
	private TeamLeader buildTeamLeader(ResultSet results) {
		TeamLeader tl = new TeamLeader();
		try {
			tl.setUsername(results.getString("username"));
			tl.setPassword(results.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tl;
	}

	/**
	 * Builds a Manager object using the ResultSet
	 * 
	 * @param results
	 *            a ResultSet used to build the object.
	 * @return A Manager object.
	 */

	private Manager buildManager(ResultSet results) {
		Manager m = new Manager();

		try {
			m.setUsername(results.getString("username"));
			m.setPassword(results.getString("password"));
			m.setSalary(Double.parseDouble(results.getString("salary")));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return m;
	}

	/**
	 * Builds a Staff object using the ResultSet
	 * 
	 * @param results
	 *            a ResultSet used to build the object.
	 * @return A Staff object.
	 */

	private Staff buildStaff(ResultSet results) {
		Staff s = new Staff();

		try {
			s.setUsername(results.getString("username"));
			s.setPassword(results.getString("password"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s;
	}

	/**
	 * Builds a Referee object using the ResultSet
	 * 
	 * @param results
	 *            a ResultSet used to build the object.
	 * @return A Referee object.
	 */

	private Referee buildReferee(ResultSet results) {
		Referee r = new Referee();

		return r;
	}

	/**
	 * Gets all the Teams that a Player plays on. The method uses the id field
	 * from a Player. This method requires that the Player p is build from an
	 * object in the database, otherwise there wouldn't be an id.
	 * 
	 * @param personId
	 *            the id of the Player.
	 * @param retrieveAssociation
	 *            determines if associations should be build.
	 * @return ArrayList<Team> the Player plays on.
	 */

	private ArrayList<Team> getTeam(String phoneno, Boolean retrieveAssociation) {

		ResultSet results;
		ArrayList<Team> list = new ArrayList<Team>();
		String query = "SELECT teamNumber FROM PlayerAssociation WHERE personId = "
				+ "(SELECT id FROM Person WHERE phoneno = '" + phoneno + "')";

		System.out.println(query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			IFTeam dbt = new DBTeam();
			Team t = new Team();
			while (results.next()) {
				t = dbt.findTeam(results.getString("teamNumber"),
						retrieveAssociation);
				list.add(t);
			}// end while
			stmt.close();

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;

	}

}