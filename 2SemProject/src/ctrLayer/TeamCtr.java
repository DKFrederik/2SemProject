package ctrLayer;

import java.sql.Connection;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

/**
 * Controller for Team. Controls creation, update, delete, find of Team and
 * adding players, manager and teamLeader to a team.
 * 
 * @author nichlas, frederik, peter, claus.
 * @version 20.05.2015
 * 
 */

public class TeamCtr {

	private static TeamCtr instance = null;
	private DBTeam dbT;

	private TeamCtr() {
		dbT = new DBTeam();
	}

	public static TeamCtr getInstance() {
		if (instance == null) {
			instance = new TeamCtr();
		}

		return instance;
	}

	/**
	 * Finds a Team in the DB.
	 * 
	 * @param teamNumber
	 *            the number of the team.
	 * @return the matching Team.
	 */
	public Team findTeam(String teamNumber) {
		return dbT.findTeam(teamNumber, true);
	}

	/**
	 * Attempts to delete a Team from the DB with the teamNumber.
	 * 
	 * @param teamNumber
	 *            of the team that is to be deleted.
	 * @return true or false to indicate success.
	 */
	public boolean deleteTeam(String teamNumber) {
		if (0 < dbT.deleteTeam(teamNumber)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Updates the teamNumber and league of a Team.
	 * 
	 * @param t
	 *            the Team to be update.
	 * @param oldTeamNumber
	 *            the teamNumber before the update.
	 * @return true or false to indicate success
	 */
	public boolean updateTeam(Team t, String oldTeamNumber) throws Exception {
		if (0 < dbT.updateTeam(t, oldTeamNumber)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets all the Teams in the DB.
	 * 
	 * @return A list of all Teams found.
	 */
	public List<Team> getTeams() {
		return dbT.getAllTeams(true);
	}

	/**
	 * Inserts a team into the DB
	 * 
	 * @param teamNumber
	 *            the teamNumber of the team to be.
	 * @param league
	 *            the league of the team to be.
	 * @return true or false depending on success.
	 */
	public boolean createTeam(String teamNumber, int league) throws Exception {
		Team t = new Team(teamNumber, league);
		boolean isSuccess = false;
		try {
			DBConnection.startTransaction();
			if (0 < dbT.insertTeam(t)) {
				isSuccess = true;
			}
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return isSuccess;
	}

	/**
	 * inserts a manager in ManagerAssociaiton
	 * 
	 * @param phoneno
	 *            of a manager
	 * @param teamNumber
	 *            of the Team
	 * @return true or false to indicate success
	 */
	public boolean insertManager(String phoneno, String teamNumber) {
		DBPerson dbp = new DBPerson();
		Person p = dbp.findPerson(phoneno, false);
		if (p instanceof Manager) {

			try {
				if (0 < dbT.insertManager((Manager) p, teamNumber))
					return true;
				else
					return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	/**
	 * Inserts a teamLeader and teamNumber into the TeamLeaderAssociation.
	 * 
	 * @param phoneno
	 *            of a TeamLeader
	 * @param teamNumber
	 *            of the Team
	 * @return true or false to indicate success
	 */
	public boolean insertTeamLeader(String phoneno, String teamNumber) {
		DBPerson dbp = new DBPerson();
		Person p = dbp.findPerson(phoneno, false);
		if (p instanceof TeamLeader) {

			try {
				if (0 < dbT.insertTeamLeader((TeamLeader) p, teamNumber))
					return true;
				else
					return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Deletes the TeamLeader from a Team.
	 * 
	 * @param phoneno
	 *            of the teamLeader.
	 * @param teamNumber
	 *            of the Team.
	 * @return true or false to indicate success.
	 */
	public boolean deleteTeamLeader(String phoneno, String teamNumber) {
		DBPerson dbp = new DBPerson();
		Person p = dbp.findPerson(phoneno, false);
		if (p instanceof TeamLeader) {

			try {
				if (0 < dbT.removeTeamLeader((TeamLeader) p, teamNumber))
					return true;
				else
					return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Deletes the TeamLeader from a Team.
	 * 
	 * @param phoneno
	 *            of the manager.
	 * @param teamNumber
	 *            of the Team.
	 * @return true or false to indicate success.
	 */
	public boolean deleteManager(String phoneno, String teamNumber) {
		DBPerson dbp = new DBPerson();
		Person p = dbp.findPerson(phoneno, false);
		if (p instanceof Manager) {

			try {
				if (0 < dbT.removeManager((Manager) p, teamNumber))
					return true;
				else
					return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
