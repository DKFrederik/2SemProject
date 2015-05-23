package dbLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * @Author Frederik, Nichlas, Claus og Peter
 * @date 13-05-2015 Schedule has the purpose of establishing a connection and
 *       execute several queries to the database, such as search, insert, delete
 *       and update.
 */

public class DBAppointment {
	private Connection con;

	public DBAppointment() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * Retrieves all Persons from the Person table in the db.
	 * 
	 * @param retriveAssociation
	 *            Determines if associations should be retrieved or not.
	 * @return An ArrayList of Person objects.
	 */
	public ArrayList<Appointment> getAllAppointments(int id, boolean retriveAssociation) {
		return miscWhere(" scheduleId = '" + id + "'", retriveAssociation);
	}

	/**
	 * 
	 * @param The
	 *            date of the schedule to fetch
	 * @return The schedule of the day.
	 */
	public Appointment findAppointment(int id) {
		String wClause = " scheduleId = '" + id + "'";
		return searchWhere(wClause);
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public int insertAppointment(Appointment a, int scheduleId) {

		int rc = -1;
		String query = "INSERT INTO Appointment(scheduleId, fieldNumber, timeSlotNumber, teamNumber)  VALUES('"
				+ scheduleId
				+ "','"
				+ a.getField().getFieldNumber()
				+ "','"
				+ a.getTimeSlot()
				+ "','"
				+ a.getTeam().getTeamNumber() + "')";

		System.out.println("insert : " + query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Appointment is not inserted correct" + ex.getMessage());
		}
		return (rc);
	}

	/**
	 * 
	 * @param
	 * @return
	 */
/*	public int updateAppointment(Appointment a) {
		Appointment aObj = a;
		int rc = -1;

		String query = "UPDATE appointment SET " + "fieldNumber ='"
				+ aObj.getField().getFieldNumber() + "', "
				+ "timeSlotNumber ='" + aObj.getTimeSlot() + "', "
				+ "teamNumber ='" + aObj.getTeam().getTeamNumber() + "', "
				+ " WHERE fieldNumber = '" + fObj.getFieldNumber() + "'";

		String query = "UPDATE Appointment SET ";
		System.out.println("Update query:" + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}

		catch (Exception ex) {
			System.out.println("Update exception in Appointment DB: " + ex);
		}
		return (rc);
	}*/

	/**
	 * 
	 * @param
	 * @return
	 */
	public int deleteAppointments(int scheduleId) {
		int rc = -1;

		String query = "DELETE FROM Appointment WHERE scheduleId = '" + scheduleId + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Delete exception in db: " + ex);
		}
		return (rc);
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	private Appointment searchWhere(String wClause) {
		ResultSet results;
		Appointment aObj = new Appointment(null);

		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				aObj = buildAppointment(results);
				stmt.close();
			} else {
				aObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return aObj;
	}

	/**
	 * 
	 * @param wClause
	 *            where clause for SQL query.
	 * @return A String formatted as a query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT fieldNumber, timeSlotNumber, teamNumber FROM Appointment";

		if (wClause.length() > 0)
			query += " WHERE " + wClause;

		return query;
	}

	/**
	 * 
	 * @param results
	 *            ResultSet used for building the Schedule
	 * @return
	 */
	private Appointment buildAppointment(ResultSet results) {
		Appointment aObj = new Appointment(null);
		try {

		} catch (Exception e) {
			System.out.println("error in building the Appointment object");
		}
		return aObj;
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
	private ArrayList<Appointment> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		DBField fDB = new DBField();
		DBTeam tDB = new DBTeam();
		ArrayList<Appointment> list = new ArrayList<Appointment>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Appointment aObj = new Appointment(null);
				aObj = buildAppointment(results);
				if (retrieveAssociation) {
						aObj.setField(fDB.findField(results.getString("fieldNumber")));
						aObj.setTeam(tDB.findTeam(results.getString("teamNumber"), true));
						//TimeSlotNumber
					}
				list.add(aObj);
				}// end while

			stmt.close();

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
}
}
