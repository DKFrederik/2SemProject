package ctrLayer;

import java.sql.Connection;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

/**
 * Controller for Team. Controls creation, update, delete, find of Team and
 * adding players, manager and teamLeader to a team.
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
		Team t = new Team("STR", 2);
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
		if (0 < dbT.insertTeam(t)) {
			return true;
		} else {
			return false;
		}
	}

}
