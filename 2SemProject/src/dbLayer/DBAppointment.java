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

public class DBAppointment implements IFAppointment {
	private Connection con;

	public DBAppointment() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/* (non-Javadoc)
	 * @see dbLayer.IFAppointment#getAllAppointments(int, boolean)
	 */
	@Override
	public ArrayList<Appointment> getAllAppointments(int id, boolean retriveAssociation) {
		return miscWhere(" scheduleId = '" + id + "'", retriveAssociation);
	}

	/* (non-Javadoc)
	 * @see dbLayer.IFAppointment#findAppointment(int)
	 */
	@Override
	public Appointment findAppointment(int id) {
		String wClause = " scheduleId = '" + id + "'";
		return searchWhere(wClause);
	}

	/* (non-Javadoc)
	 * @see dbLayer.IFAppointment#insertAppointment(modelLayer.Appointment, int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see dbLayer.IFAppointment#deleteAppointments(int)
	 */
	@Override
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
	 * Finds an appointment in the db. 
	 * @param wClause the where clause for the select query.
	 * @return An appointment object or null. 
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
	 * Builds a query. 
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
	 * Builds an Appointment object. 
	 * @param results
	 *            ResultSet used for building the Schedule.
	 * @return An Appointment object.
	 */
	private Appointment buildAppointment(ResultSet results) {
		DBField fDb = new DBField();
		DBTeam tDb = new DBTeam();
		Appointment aObj = new Appointment(null);
		try {		
			aObj.setField(fDb.findField(results.getString("fieldNumber")));
			aObj.setTeam((tDb.findTeam(results.getString("teamNumber"), true)));
			aObj.setTimeSlot(results.getInt("timeSlotNumber"));

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
						aObj.setTimeSlot(results.getInt("timeSlotNumber"));
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
