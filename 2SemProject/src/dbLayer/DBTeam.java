package dbLayer;

import java.sql.Connection;
import java.util.ArrayList;

import modelLayer.Field;

public class DBTeam {
	private Connection con;

	/**
	 * Constructor for DBField() class. 
	 */
	public DBField() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * 
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return An ArrayList of Field objects.
	 */
	public ArrayList<Field> getAllFields(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	/**
	 * 
	 * @param name The name of the field that you wish to find.
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return A Field Object if found, null if not.
	 */
	public Field findField(String fieldNumber, boolean retriveAssociation) {
		String wClause = "  name = '" + fieldNumber + "'";
		return singleWhere(wClause, retriveAssociation);
	}

}
