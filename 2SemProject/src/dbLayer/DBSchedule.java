package dbLayer;

import modelLayer.*;

import java.sql.*;

/**
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	13-05-2015
 * Schedule has the purpose of establishing a connection and execute several queries to the database, such
 * as search, insert, delete and update.
 */

public class DBSchedule {
	private Connection con;
	
	public DBSchedule() {
		con = DBConnection.getInstance().getDBcon();
	}

	/**
	 * 
	 * @param The date of the schedule to fetch
	 * @return The schedule of the day.
	 */
	public Schedule findSchedule(Date date) {
		String wClause = "   = '" + date + "'";
		return searchWhere(wClause);
	}
	
	/**
	 * 
	 * @param
	 * @return 
	 */
	public int insertSchedule(Schedule s) {
		
		DBPerson pDB = new DBPerson();

		int rc = -1;
		String query = "INSERT INTO Schedule(date, creator)  VALUES('"
				+ s.getDate()
				+ "','"
				+ pDB.findPerson(s.getCreator().getPhone(), false)
				+ "')";

		System.out.println("insert : " + query);
		
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Schedule is not inserted correct");
		}
		return (rc);
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public int updateSchedule(Schedule s) {
		Schedule sObj = s;
		int rc = -1;

		String query = "UPDATE Schedule SET "
				+ 
				;
		System.out.println("Update query:" + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		
		catch (Exception ex) {
			System.out.println("Update exception in Schedule DB: " + ex);
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
	private Schedule searchWhere(String wClause) {
		ResultSet results;
		Schedule atObj = new Schedule();

		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) 
			{
				atObj = buildSchedule(results);
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
	 * @param results ResultSet used for building the Schedule
	 * @return
	 */
	private Schedule buildSchedule(ResultSet results) {
		Schedule atObj = new Schedule();
		try 
		{

		} catch (Exception e) {
			System.out.println("error in building the Schedule object");
		}
		return atObj;
	}
	
	/**
	 * Finds the max id of the Schedule table.
	 * @return the max id used in the Schedule table. 
	 */
	public int findMaxId() {
		int id = 0;
		ResultSet rs;
		String query = "SELECT MAX(id) FROM Schedule";
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rs = stmt.executeQuery(query);
			stmt.close();
			
			id = rs.getInt("id");
		}
		catch (Exception ex) {
			System.out.println("search failed");
		}
		return id;
	}
}

