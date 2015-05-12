package dbLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * DBField.java
 * 
 * @author Peter, Frederik, Claus og Nichlas.
 * @version 20.03.2015
 *
 */
public class DBField {
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
	public Field findField(String number, boolean retriveAssociation) {
		String wClause = "  name = '" + number + "'";
		return singleWhere(wClause, retriveAssociation);
	}
	
	/**
	 * 
	 * @param pro The field that is to be inserted
	 * @return 
	 * @throws Exception 
	 * number
	 * type
	 */
	public int insertField(Field f) throws Exception {

		int rc = -1;
		String query = "INSERT INTO Field(number, type)  VALUES('"
				+ f.getNumber()
				+ "','"
				+ f.getType()
				+ "','";
//		if(f instanceof Field) {
//			Player pl = (Field) f;
//			query += pl.getBDay()
//					+ "','"
//					+ pl.getPosition()
//					+ "','"
//					+ "null"
//					+ "','"
//					+ "null"
//					+ "','"
//					+ "-1"
//					+ ""
//					+ "','"
//					+ "P";
//		}
		
		
		query += "')";
		System.out.println("insert : " + query);
		try { // insert new Field
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Field not created");
			System.out.println(ex.getErrorCode());
			System.out.println(ex.getMessage());
			throw new Exception("Field is not inserted correct");
		}
		return (rc);
	}
	
	/**
	 * 
	 * @param The field object that is to be updated in the database.
	 * @return
	 */
	public int updateField(Field field, String number) {
		Field fieldObj = field;
		int rc = -1;

		String query = "UPDATE Field SET " 
				+ "fname ='" + fieldObj.getNumber() + "', " 
				+ "lname ='" + fieldObj.getType() + "', ";
		query += "WHERE number = '" + number + "'";
		System.out.println("Update query:" + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		
		catch (Exception ex) {
			System.out.println("Update exception in field db: " + ex);
		}
		return (rc);
	}

	public int deleteField(String number) {
		int rc = -1;

		String query = "DELETE FROM Field WHERE number = '" + number + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Delete exception in field db: " + ex);
		}
		return (rc);
	}

	/**
	 * 
	 * @param wClause where clause for the query.
	 * @param retrieveAssociation whether or not to retrieve associations.
	 * @return An ArrayList of fieds.
	 */
	private ArrayList<Field> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Field> list = new ArrayList<Field>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Field fieldObj = new Field();
				fieldObj = buildField(results);
				list.add(fieldObj);
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

	private Field singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Field fieldObj = new Field();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				fieldObj = buildField(results);
				stmt.close();
				
			} else {
				fieldObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return fieldObj;
	}

	/**
	 * Builds a SQL query
	 * @param wClause where clause.
	 * @return the build SQL query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT number, type, position FROM Field";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	/**
	 * Builds a field from a ResultSet.
	 * 
	 * @param results the results returned by the DMBS
	 * @return a field
	 */
	private Field buildField(ResultSet results){
		Field fieldObj = new Field();
		try 
		{
			fieldObj.setNumber(results.getString("number"));
			fieldObj.setType(results.getString("type"));
		} 
		catch (Exception e) {
			System.out.println("error in building the field object");
		}
		return fieldObj;
	}
	
}

