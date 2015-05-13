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

}
