package dbLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ctrLayer.FieldCtr;

/**
 * @Author 	Frederik, Nichlas, Claus og Peter
 * @date	13-05-2015
 * Schedule has the purpose of establishing a connection and execute several queries to the database, such
 * as search, insert, delete and update.
 */

public class DBSchedule implements IFSchedule {
	private Connection con;
	
	public DBSchedule() {
		con = DBConnection.getInstance().getDBcon();
	}

	/* (non-Javadoc)
	 * @see dbLayer.IFSchedule#findSchedule(java.sql.Date, boolean)
	 */
	@Override
	public Schedule findSchedule(Date date, boolean retrieveAssociation) {
		String wClause = " Schedule.date = '" + date + "'";
		return singleWhere(wClause,retrieveAssociation);
	}
	
	/* (non-Javadoc)
	 * @see dbLayer.IFSchedule#findAllSchedules(boolean)
	 */
	@Override
	public List<Schedule> findAllSchedules(boolean retrieveAssociation) {
		return miscWhere("",retrieveAssociation);
	}
	
	/* (non-Javadoc)
	 * @see dbLayer.IFSchedule#insertSchedule(modelLayer.Schedule)
	 */
	@Override
	public int insertSchedule(Schedule s) {
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

	/* (non-Javadoc)
	 * @see dbLayer.IFSchedule#deleteSchedule(java.sql.Date)
	 */
	@Override
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
	 * Method for finding a single Schedule.
	 * @param wClause where clause. 
	 * @param retrieveAssociation whether associations should be build as well.
	 * @return A schedule object or null.
	 */
	private Schedule singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Schedule schObj = new Schedule(new DBField().getAllFields(false),null);
		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) 
			{
				schObj = buildSchedule(results);
				if (retrieveAssociation) {
					schObj.setCreator(new DBPerson().findPerson(results.getString("phoneno"),true));
					schObj.setAppointments(new DBAppointment().getAllAppointments(results.getInt("id"), false));
				}
				stmt.close();
			} 
			else 
			{
				schObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return schObj;
	}
	/**
	 * Gets a list of all Schedules in the database.
	 * @param wClause Where clause.
	 * @param retrieveAssociation Whether associations should be build as well.
	 * @return A list of Schedules.
	 */
	private List<Schedule> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		List<Schedule> schedules = new ArrayList<Schedule>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Schedule schObj = new Schedule(new DBField().getAllFields(false),null);
			schObj = buildSchedule(results);
				if (retrieveAssociation) {
					schObj.setCreator(new DBPerson().findPerson(results.getString("phoneno"),true));
					schObj.setAppointments(new DBAppointment().getAllAppointments(results.getInt("id"), true));
				}
				schedules.add(schObj);
			}// end while
			stmt.close();

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return schedules;
	}

	/**
	 * Builds a SELECT query.
	 * @param wClause where clause for SQL query.
	 * @return A String formatted as a query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT Schedule.date, Schedule.creator, Schedule.id, Person.phoneno "
					 + "FROM Schedule INNER JOIN Person ON Schedule.creator = Person.id";

		if (wClause.length() > 0)
			query += " WHERE " + wClause;

		return query;
	}

	/**
	 * Method for building Schedule objects.
	 * @param results ResultSet used for building the Schedule
	 * @return a Schedule object
	 */
	private Schedule buildSchedule(ResultSet results) {

		Schedule sObj = new Schedule(new DBField().getAllFields(false),null);
		try 
		{
			sObj.setDate(results.getDate("date"));		
		} 
		catch (Exception e) 
		{
			System.out.println("error in building the Schedule object");
		}
		return sObj;
	}
	
	/* (non-Javadoc)
	 * @see dbLayer.IFSchedule#findMaxId()
	 */
	@Override
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

