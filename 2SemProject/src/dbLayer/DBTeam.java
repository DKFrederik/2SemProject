package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Manager;
import modelLayer.Person;
import modelLayer.Player;
import modelLayer.Team;
import modelLayer.TeamLeader;

/**
 * 
 * @author nichlas, frederik, claus, peter
 * @version 12.05.2015 Database class for teams. Handles insert, delete, update,
 *          find and find all.
 */

public class DBTeam {
	private Connection con;

	/**
	 * Constructor for DBTeam() class.
	 */
	public DBTeam() {
		con = DBConnection.getInstance().getDBcon();
	}

	/**
	 * 
	 * @param retriveAssociation
	 *            Determines if associations should be retrieved or not.
	 * @return An ArrayList of Team objects.
	 */
	public ArrayList<Team> getAllTeams(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	/**
	 * 
	 * @param teamNumber
	 *            The number of the team that you wish to find.
	 * @param retriveAssociation
	 *            Determines if associations should be retrieved or not.
	 * @return A Team Object if found, null if not.
	 */
	public Team findTeam(String teamNumber, boolean retriveAssociation) {
		String wClause = "  teamNumber = '" + teamNumber + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	/**
	 * 
	 * @param wClause
	 *            where clause for the query.
	 * @param retrieveAssociation
	 *            whether or not to retrieve associations.
	 * @return An ArrayList of Teams.
	 */
	private ArrayList<Team> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Team> teams = new ArrayList<Team>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Team teamObj = new Team();
				teamObj = buildTeam(results);
				if (retrieveAssociation) {
					teamObj.setPlayers(getPlayers(results
							.getString("teamNumber")));
				}
				teams.add(teamObj);
			}// end while
			stmt.close();

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return teams;
	}

	/**
	 * 
	 * @param wClause
	 *            where clause for the query.
	 * @param retrieveAssociation
	 *            whether or not to retrieve associations.
	 * @return A Team.
	 */

	private Team singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Team teamObj = new Team();

		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				teamObj = buildTeam(results);
				if (retrieveAssociation) {
					teamObj.setPlayers(getPlayers(results
							.getString("teamNumber")));
				}
				stmt.close();
			} else {
				teamObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return teamObj;
	}

	/**
	 * Builds a query with the where clause.
	 * 
	 * @param wClause
	 *            Where Clause
	 * @return a query
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT teamNumber, league FROM Team";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	/**
	 * Inserts teamNumber and league into the Team table in the DB.
	 * 
	 * @param t
	 *            the Team to be inserted
	 * @return the number of rows affected.
	 */
	public int insertTeam(Team t) throws Exception {

		int rc = -1;
		String query = "INSERT INTO Team(teamNumber, league) VALUES('"
				+ t.getNumber() + "','" + t.getLeague() + "')";

		System.out.println("insert : " + query);
		try { // insert new Team
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Team not created");
			throw new Exception("Team is not inserted correct");
		}
		return (rc);

	}

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
	public int updateTeam(Team t, String oldTeamNo) throws Exception {

		int rc = -1;
		String query = "UPDATE Team SET " + "teamNumber = '" + t.getNumber()
				+ "'," + "league = '" + t.getLeague();

		query += "'" + " WHERE teamNumber = '" + oldTeamNo + "'";

		System.out.println("update : " + query);
		try { // Update Team
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Team not updated");
			throw new Exception("Team not update correctly");
		}
		return (rc);

	}

	/**
	 * Deletes a Team from the Team table. DBMS deletes handles deletion of
	 * foreign key using it.
	 * 
	 * @param teamNumber
	 *            The number of the team that you wish to delete.
	 * @return the number of rows affected or -1 if error.
	 */
	public int deleteTeam(String teamNumber) {
		int rc = -1;

		String query = "DELETE FROM Team WHERE teamNumber = '" + teamNumber
				+ "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Delete exception in Team db: " + ex);
		}
		return (rc);

	}

	/**
	 * Builds a Team using a ResultSet.
	 * 
	 * @param results
	 *            the ResultsSet from which the information used to build is
	 *            retrieved.
	 * @return a Team.
	 */
	private Team buildTeam(ResultSet results) {
		Team t = new Team();

		try {
			t.setNumber(results.getString("teamNumber"));
			t.setLeague(Integer.parseInt(results.getString("league")));
			t.setManager(getManager(results.getString("teamNumber")));
			t.setTeamLeader(getTeamLeader(results.getString("teamNumber")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * Get allt the players belonging to a Team
	 * 
	 * @param teamNumber
	 *            The number of the team that you wish to find Players for.
	 * @return An ArrayList of Players.
	 */
	private ArrayList<Player> getPlayers(String teamNumber) {

		ResultSet results;
		ArrayList<Player> list = new ArrayList<Player>();
		String query = "SELECT personId FROM Association WHERE teamNumber = '"
				+ teamNumber + "'";

		System.out.println(query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			DBPerson dbp = new DBPerson();
			Person p = new Person();
			ResultSet resPer;
			while (results.next()) {// Get phoneno of players
				query = "SELECT phoneno FROM Person WHERE id = "
						+ results.getString("personId");
				Statement stmt2 = con.createStatement();
				resPer = stmt2.executeQuery(query);
				while (resPer.next()) {// Use phoneno's as parameter for
										// findPerson to get players.
					p = dbp.findPerson(resPer.getString("phoneno"), false);
					if (p instanceof Player)
						list.add((Player) p);
				}
				stmt2.close();
			}// end while
			stmt.close();

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

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
	public int addPlayerTeam(Player p, String teamNumber) throws Exception {
		int rc = -1;
		String query = "INSERT INTO Association(personId, teamNumber) VALUES("
				+ "(SELECT id FROM Person WHERE phoneno = '" + p.getPhone() + "'),'"
				+ teamNumber + "')";

		System.out.println("insert : " + query);
		try { // insert new Association
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Association not created");
			throw new Exception("Association is not inserted correct");
		}
		return (rc);
	}

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
	public int deletePlayerTeam(Player p, String teamNumber) {
		int rc = -1;

		String query = "DELETE FROM Association WHERE teamNumber = '"
				+ teamNumber + "' and personId = (SELECT id FROM Person WHERE phoneno = '" + p.getPhone() + "')";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Delete exception in Association db: " + ex);
		}
		return (rc);
	}

	/**
	 * Finds the manager for the Team.
	 * 
	 * @param teamNumber
	 *            The number of the team that you wish to find the manager for.
	 * @return A Manager object or null.
	 */
	private Manager getManager(String teamNumber) {
		Manager m = null;
		ResultSet results;
		String query = "SELECT phoneno FROM Person WHERE id = (SELECT managerId FROM ManagerAssociation WHERE teamNumber = '"
				+ teamNumber + "')";

		System.out.println(query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			DBPerson dbp = new DBPerson();
			while (results.next())
				m = (Manager) dbp.findPerson(results.getString("phoneno"),
						false);
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}

		return m;
	}

	/**
	 * Finds the TeamLeader for the Team.
	 * 
	 * @param teamNumber
	 *            The number of the team that you wish to find  the TeamLeader
	 *            for.
	 * @return A TeamLeader object or null.
	 */
	private TeamLeader getTeamLeader(String teamNumber) {
		TeamLeader tl = null;
		ResultSet results;
		String query = "SELECT phoneno FROM Person WHERE id = (SELECT leaderId FROM TeamLeaderAssociation WHERE teamNumber = '"
				+ teamNumber + "')";

		System.out.println(query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			DBPerson dbp = new DBPerson();
			if (results.next())
				tl = (TeamLeader) dbp.findPerson(results.getString("phoneno"),
						false);
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}

		return tl;
	}
	
	public int insertTeamLeader(TeamLeader tl, String teamNumber) throws Exception {
		int rc = -1;
		String query = "INSERT INTO TeamLeaderAssociation(leaderId, teamNumber) VALUES("
				+ "(SELECT id FROM Person WHERE phoneno = '" + tl.getPhone() + "'),'"
				+ teamNumber + "')";

		System.out.println("insert : " + query);
		try { // insert new Association
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("TeamLeader not created");
			throw new Exception("TeamLeader is not inserted correct");
		}
		return (rc);
	}
}
	
	public int insertManager(Manager m, String teamNumber) throws Exception {
		int rc = -1;
		String query = "INSERT INTO ManagerAssociation(leaderId, teamNumber) VALUES("
				+ "(SELECT id FROM Person WHERE phoneno = '" + m.getPhone() + "'),'"
				+ teamNumber + "')";
	
		System.out.println("insert : " + query);
		try { // insert new Association
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Manager not created");
			throw new Exception("Manager is not inserted correct");
		}
		return (rc);
	}
}

