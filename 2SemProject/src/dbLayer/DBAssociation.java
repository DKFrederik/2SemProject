/**
 * 
 */
package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Person;
import modelLayer.Player;

/**
 * @author nichlas
 * @version 12.05.2015
 * Class for handling team-player associations in the db.
 */
public class DBAssociation {

	private Connection con;
	
	/**
	 * Constructor for DBPerson() class. 
	 */
	public DBAssociation() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * 
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return An ArrayList of Person objects.
	 */
	public ArrayList<Person> getAllPersonsInTeam(String teamNumber ,boolean retriveAssociation) {
		
		return miscWherePerson(teamNumber, retriveAssociation);
	}
	
	
	private ArrayList<Person> miscWherePerson(String teamNumber, boolean Association) {
		
		ResultSet results;
		ArrayList<Person> list = new ArrayList<Person>();
		String query = "SELECT phoneno FROM Person WHERE id = (SELECT personId FROM Association WHERE teamNumber = '" 
					+ teamNumber + "')";
		
		System.out.println(query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			DBPerson dbp = new DBPerson();
			Person p = new Person();
			while (results.next()) {
				p = dbp.findPerson(results.getString("phoneno"), true);
				list.add(p);
			}// end while
			stmt.close();
			
		}
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;

	}
}
