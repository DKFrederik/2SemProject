package dbLayer;

import java.util.ArrayList;

import modelLayer.Manager;
import modelLayer.Player;
import modelLayer.Team;
import modelLayer.TeamLeader;

public interface IFTeam {

	/**
	 * Method for retrieving all teams from the database.
	 * @param retriveAssociation
	 *            Determines if associations should be retrieved or not.
	 * @return An ArrayList of Team objects.
	 */
	public abstract ArrayList<Team> getAllTeams(boolean retriveAssociation);

	/**
	 * Searches for a specific team in the DB using a teamNumber.
	 * @param teamNumber
	 *            The number of the team that you wish to find.
	 * @param retriveAssociation
	 *            Determines if associations should be retrieved or not.
	 * @return A Team Object if found, null if not.
	 */
	public abstract Team findTeam(String teamNumber, boolean retriveAssociation);

	/**
	 * Inserts teamNumber and league into the Team table in the DB.
	 * 
	 * @param t
	 *            the Team to be inserted
	 * @return the number of rows affected.
	 */
	public abstract int insertTeam(Team t) throws Exception;

	/**
	 * Updates the teamNumber and league for a Team in the Team table in the DB.
	 * 
	 * @param t
	 *            the Team which information is to be updated. The number of the
	 *            team that you wish to find.
	 * @param oldTeamNo
	 *            the old teamNumber, used for Where part of query
	 * @return the number of rows affected or -1 if error.
	 */
	public abstract int updateTeam(Team t, String oldTeamNo) throws Exception;

	/**
	 * Deletes a Team from the Team table. DBMS deletes handles deletion of
	 * foreign key using it.
	 * 
	 * @param teamNumber
	 *            The number of the team that you wish to delete.
	 * @return the number of rows affected or -1 if error.
	 */
	public abstract int deleteTeam(String teamNumber);

	/**
	 * Adds a Player to the Team by inserting a playerId and teamNumber in the
	 * Association table.
	 * 
	 * @param p
	 *            The player you wish to add to a team.
	 * @param teamNumber
	 *            The number of the team that you wish to add a Player to.
	 * @return the number of rows affected or -1 if error.
	 */
	public abstract int addPlayerTeam(Player p, String teamNumber)
			throws Exception;

	/**
	 * Removes a Player from the Team by deleting playerId and teamNumber in the
	 * Association table.
	 * 
	 * @param p
	 *            The player you wish to remove from the team.
	 * @param teamNumber
	 *            The number of the team that you wish to remove a Player from.
	 * @return the number of rows affected or -1 if error.
	 */
	public abstract int deletePlayerTeam(Player p, String teamNumber);

	/**
	 * Insert a TeamLeader and teamNumber into the TeamLeaderAssociation so that it can be stored for later retrieval.
	 * @param tl the Teamleader
	 * @param teamNumber teamNumber of the team
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 * @throws Exception 
	 */
	public abstract int insertTeamLeader(TeamLeader tl, String teamNumber)
			throws Exception;

	/**
	 * Insert a Manager and teamNumber into the ManagerAssociation so that it can be stored for later retrieval.
	 * @param m the Manager
	 * @param teamNumber the teamNumber of the Team
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 * @throws Exception
	 */
	public abstract int insertManager(Manager m, String teamNumber)
			throws Exception;

	/**
	 * Removes a Manager from the Team by deleting managerId and teamNumber in
	 * the ManagerAssociation table.
	 * 
	 * @param p
	 *            The manager you wish to remove from the team.
	 * @param teamNumber
	 *            The number of the team that you wish to remove a Manager from.
	 * @return the number of rows affected or -1 if error.
	 */
	public abstract int removeManager(Manager m, String teamNumber);

	/**
	 * Removes a TeamLeader from the Team by deleting leaderId and teamNumber in
	 * the Association table.
	 * 
	 * @param p
	 *            The teamleader you wish to remove from the team.
	 * @param teamNumber
	 *            The number of the team that you wish to remove a Player from.
	 * @return the number of rows affected or -1 if error.
	 */
	public abstract int removeTeamLeader(TeamLeader tl, String teamNumber);

}