package dbLayer;
import modelLayer.Field;

import modelLayer.*;

import java.sql.*;

/**
 * 
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	20-03-2015
 * DBField has the purpose of establishing a connection and execute several queries to the database, such
 * as search, insert, delete and update.
 */

public class DBField {
	private Connection con;
	
	public DBField() {
		con = DBConnection.getInstance().getDBcon();
	}

	/**
	 * 
	 * @param phoneno the Field objects phone no.
	 * @return the Field matching the phone no.
	 */
	public Field findField(String fieldNumber) {
		String wClause = "  fieldNumber = '" + fieldNumber + "'";
		return searchWhere(wClause);
	}
	
	/**
	 * 
	 * @param f a Field object to be inserted.
	 * @return 
	 */
	public int insertField(Field f) {

		int rc = -1;
		String query = "INSERT INTO Field(fieldNumber, type, length, width)  VALUES('"
				+ f.getFieldNumber()
				+ "','"
				+ f.getType()
				+ "','"
				+ f.getLength()
				+ "','"
				+ f.getWidth()
				+ "')";

		System.out.println("insert : " + query);
		
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Field is not inserted correct");
		}
		return (rc);
	}

	/**
	 * 
	 * @param f The field object that is to be updated in the database.
	 * @return
	 */
	public int updateField(Field f) {
		Field fObj = f;
		int rc = -1;

		String query = "UPDATE field SET " + "fieldNumber ='" + fObj.getFieldNumber()
				+ "', " + "type ='" + fObj.getType() + "', " 
				+ "length ='" + fObj.getLength() + "', " 
				+ "width ='" + fObj.getWidth() + "', " 
				+ " WHERE fieldNumber = '" + fObj.getFieldNumber() + "'";
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

	
	/**
	 * 
	 * @param fieldNumber The number of the field that is to be removed.
	 * @return
	 */
	public int deleteField(String fieldNumber) {
		int rc = -1;

		String query = "DELETE FROM field WHERE fieldNumber = '" + fieldNumber + "'";
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
	 * @param wClause where clause for sql query
	 * @return a Field object
	 */
	private Field searchWhere(String wClause) {
		ResultSet results;
		Field fObj = new Field();

		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) 
			{
				fObj = buildField(results);
				stmt.close();
			} 
			else 
			{
				fObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return fObj;
	}

	/**
	 * 
	 * @param wClause where clause for SQL query.
	 * @return A String formatted as a query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT fieldNumber, type, length, width FROM Field";

		if (wClause.length() > 0)
			query += " WHERE " + wClause;

		return query;
	}

	/**
	 * 
	 * @param results ResultSet used for building the field
	 * @return The build field object
	 */
	private Field buildField(ResultSet results) {
		Field fObj = new Field();
		try 
		{
			fObj.setFieldNumber(results.getString("fieldNumber"));
			fObj.setType(results.getString("type"));
			fObj.setLength(Integer.parseInt(results.getString("length")));
			fObj.setWidth(Integer.parseInt(results.getString("width")));
		} catch (Exception e) {
			System.out.println("error in building the field object");
		}
		return fObj;
	}
}
