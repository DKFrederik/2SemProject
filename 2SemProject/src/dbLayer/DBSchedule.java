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
				+ "',"
				+ "(SELECT id FROM Person WHERE phoneno = '"
				+ s.getCreator().getPhone()
				+ "'))";

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
	public int deleteSchedule(Date date) {
		int rc = -1;

		String query = "DELETE FROM Schedule WHERE date = '" + date + "'";
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
		DBField fDb = new DBField();
		Schedule atObj = new Schedule(fDb.getAllFields(false),null);

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
		DBField fDb = new DBField();
		DBAppointment aDb = new DBAppointment();
		Schedule sObj = new Schedule(fDb.getAllFields(false),null);
		
		try 
		{
			sObj.setDate(results.getDate("date"));
			sObj.setAppointments(aDb.getAllAppointments(results.getInt("id"), true));
		} 
		catch (Exception e) 
		{
			System.out.println("error in building the Schedule object");
		}
		return sObj;
	}
	
	/**
	 * Finds the max id of the Schedule table.
	 * @return the max id used in the Schedule table. 
	 */
	public int findMaxId() {
		int id = 0;
		ResultSet results;
		String query = "SELECT MAX(id) as id FROM Schedule";
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			results.next();
			id = results.getInt("id");
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("search failed" + ex.getMessage());
		}
		return id;
	}
}

