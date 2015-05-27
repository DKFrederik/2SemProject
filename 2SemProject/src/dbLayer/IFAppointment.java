package dbLayer;

import java.util.ArrayList;

import modelLayer.Appointment;

public interface IFAppointment {

	/**
	 * Retrieves all Persons from the Person table in the db.
	 * 
	 * @param retriveAssociation
	 *            Determines if associations should be retrieved or not.
	 * @return An ArrayList of Person objects.
	 */
	public abstract ArrayList<Appointment> getAllAppointments(int id,
			boolean retriveAssociation);

	/**
	 * Finds an appointment using the id. 
	 * @param The
	 *            date of the schedule to fetch
	 * @return The schedule of the day.
	 */
	public abstract Appointment findAppointment(int id, boolean retrieveAssociation);

	/**
	 * Inserts an appoinment into the db. 
	 * @param a the Appointment.
	 * @param scheduleId the id of the schedule.
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 */
	public abstract int insertAppointment(Appointment a, int scheduleId);

	/**
	 * Deletes an appointment from the DB.
	 * @param scheduleId the scheduleId for the appointments
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 */
	public abstract int deleteAppointments(int scheduleId);

}