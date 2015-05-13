package dbLayer;

import modelLayer.*;

import java.sql.*;

/**
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	13-05-2015
 * DBAppointedTime has the purpose of establishing a connection and execute several queries to the database, such
 * as search, insert, delete and update.
 */

public class DBAppointedTime {
	private Connection con;
	
	public DBAppointedTime() {
		con = DBConnection.getInstance().getDBcon();
	}

	/**
	 * 
	 * @param The date of the schedule to fetch
	 * @return The schedule of the day.
	 */
	public AppointedTime findAppointedTime(Date date) {
		String wClause = "   = '" + date + "'";
		return searchWhere(wClause);
	}
	
	/**
	 * 
	 * @param
	 * @return 
	 */
	public int insertAppointedTime(AppointedTime at) {

		int rc = -1;
		String query = "INSERT INTO AppointedTime()  VALUES('"
				+ "','"
				+ "')";

		System.out.println("insert : " + query);
		
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("AppointedTime is not inserted correct");
		}
		return (rc);
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public int updateAppointedTime(AppointedTime at) {
		AppointedTime atObj = at;
		int rc = -1;

		String query = "UPDATE AppointedTime SET ";
		System.out.println("Update query:" + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		
		catch (Exception ex) {
			System.out.println("Update exception in AppointedTime DB: " + ex);
		}
		return (rc);
	}

	
	/**
	 * 
	 * @param 
	 * @return
	 */
	public int delete(String Number) {
		int rc = -1;

		String query = "DELETE FROM  WHERE Number = '" + Number + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Delete exception in db: " + ex);
		}
		return (rc);
	}

	/**
	 * 
	 * @param 
	 * @return 
	 */
	private AppointedTime searchWhere(String wClause) {
		ResultSet results;
		AppointedTime atObj = new AppointedTime();

		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) 
			{
				atObj = buildAppointedTime(results);
				stmt.close();
			} 
			else 
			{
				atObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return atObj;
	}

	/**
	 * 
	 * @param wClause where clause for SQL query.
	 * @return A String formatted as a query.
	 */
	private String buildQuery(String wClause) {
		String query = "";

		if (wClause.length() > 0)
			query += " WHERE " + wClause;

		return query;
	}

	/**
	 * 
	 * @param results ResultSet used for building the AppointedTime
	 * @return
	 */
	private AppointedTime buildAppointedTime(ResultSet results) {
		AppointedTime atObj = new AppointedTime();
		try 
		{

		} catch (Exception e) {
			System.out.println("error in building the AppointedTimeObjet object");
		}
		return atObj;
	}
}

