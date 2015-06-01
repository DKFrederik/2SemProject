package dbLayer;

import java.sql.Date;
import java.util.List;

import modelLayer.Schedule;

public interface IFSchedule {

	/**
	 * finds a schedule with the date.
	 * @param The date of the schedule to fetch
	 * @return The schedule of the day.
	 */
	public abstract Schedule findSchedule(Date date, boolean retrieveAssociation);

	/**
	 * Gets all schedules from the database.
	 * @param The date of the schedule to fetch
	 * @return The schedule of the day.
	 */
	public abstract List<Schedule> findAllSchedules(boolean retrieveAssociation);

	/**
	 * Inserts a Schedule into the database.
	 * @param s The schedule to be inserted.
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 */
	public abstract int insertSchedule(Schedule s);

	/**
	 * Deletes a schedule from the database that matches the date parameter.
	 * @param date the date of the schedule that is to be deleted.
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 */
	public abstract int deleteSchedule(Date date);

	/**
	 * Finds the max id of the Schedule table.
	 * @return the max id used in the Schedule table. 
	 */
	public abstract int findMaxId();

}