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

public class DBTeam implements IFTeam {
	private Connection con;

	/**
	 * Constructor for DBTeam() class.
	 */
	public DBTeam() {
		con = DBConnection.getInstance().getDBcon();
	}

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#getAllTeams(boolean)
	 */
	@Override
	public ArrayList<Team> getAllTeams(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#findTeam(java.lang.String, boolean)
	 */
	@Override
	public Team findTeam(String teamNumber, boolean retriveAssociation) {
		String wClause = "  teamNumber = '" + teamNumber + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	/**
	 * Used by getAllTeams() to find all teams.
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
	 * Searches for a single Team object in the DB and builds it. 
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

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#insertTeam(modelLayer.Team)
	 */
	@Override
	public int insertTeam(Team t) throws Exception {

		int rc = -1;
		String query = "INSERT INTO Team(teamNumber, league) VALUES('"
				+ t.getTeamNumber() + "','" + t.getLeague() + "')";

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

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#updateTeam(modelLayer.Team, java.lang.String)
	 */
	@Override
	public int updateTeam(Team t, String oldTeamNo) throws Exception {

		int rc = -1;
		String query = "UPDATE Team SET " + "teamNumber = '"
				+ t.getTeamNumber() + "'," + "league = '" + t.getLeague();

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

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#deleteTeam(java.lang.String)
	 */
	@Override
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
			t.setTeamNumber(results.getString("teamNumber"));
			t.setLeague(Integer.parseInt(results.getString("league")));
			t.setManager(getManager(results.getString("teamNumber")));
			t.setTeamLeader(getTeamLeader(results.getString("teamNumber")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * Get all the players belonging to a Team
	 * 
	 * @param teamNumber
	 *            The number of the team that you wish to find Players for.
	 * @return An ArrayList of Players.
	 */
	private ArrayList<Player> getPlayers(String teamNumber) {

		ResultSet results;
		ArrayList<Player> list = new ArrayList<Player>();
		String query = "SELECT personId FROM PlayerAssociation WHERE teamNumber = '"
				+ teamNumber + "'";

		System.out.println(query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			IFPerson dbp = new DBPerson();
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

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#addPlayerTeam(modelLayer.Player, java.lang.String)
	 */
	@Override
	public int addPlayerTeam(Player p, String teamNumber) throws Exception {
		int rc = -1;
		String query = "INSERT INTO PlayerAssociation(personId, teamNumber) VALUES("
				+ "(SELECT id FROM Person WHERE phoneno = '" + p.getPhone()
				+ "'),'" + teamNumber + "')";

		System.out.println("insert : " + query);
		try { // insert new Association
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("PlayerAssociation not created");
			throw new Exception("PlayerAssociation is not inserted correct");
		}
		return (rc);
	}

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#deletePlayerTeam(modelLayer.Player, java.lang.String)
	 */
	@Override
	public int deletePlayerTeam(Player p, String teamNumber) {
		int rc = -1;

		String query = "DELETE FROM PlayerAssociation WHERE teamNumber = '"
				+ teamNumber
				+ "' and personId = (SELECT id FROM Person WHERE phoneno = '"
				+ p.getPhone() + "')";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Delete exception in PlayerAssociation db: " + ex);
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
			IFPerson dbp = new DBPerson();
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
	 *            The number of the team that you wish to find the TeamLeader
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
			IFPerson dbp = new DBPerson();
			if (results.next())
				tl = (TeamLeader) dbp.findPerson(results.getString("phoneno"),
						false);
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}

		return tl;
	}

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#insertTeamLeader(modelLayer.TeamLeader, java.lang.String)
	 */
	@Override
	public int insertTeamLeader(TeamLeader tl, String teamNumber)
			throws Exception {
		int rc = -1;
		String query = "INSERT INTO TeamLeaderAssociation(leaderId, teamNumber) VALUES("
				+ "(SELECT id FROM Person WHERE phoneno = '"
				+ tl.getPhone()
				+ "'),'" + teamNumber + "')";

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
	
	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#insertManager(modelLayer.Manager, java.lang.String)
	 */
	@Override
	public int insertManager(Manager m, String teamNumber) throws Exception {
		int rc = -1;
		String query = "INSERT INTO ManagerAssociation(leaderId, teamNumber) VALUES("
				+ "(SELECT id FROM Person WHERE phoneno = '"
				+ m.getPhone()
				+ "'),'" + teamNumber + "')";

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

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#removeManager(modelLayer.Manager, java.lang.String)
	 */
	@Override
	public int removeManager(Manager m, String teamNumber) {
		int rc = -1;

		String query = "DELETE FROM ManagerAssociation WHERE teamNumber = '"
				+ teamNumber
				+ "' and managerId = (SELECT id FROM Person WHERE phoneno = '"
				+ m.getPhone() + "')";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Delete exception in ManagerAssociation db: "
					+ ex);
		}
		return (rc);
	}

	/* (non-Javadoc)
	 * @see dbLayer.IFTeam#removeTeamLeader(modelLayer.TeamLeader, java.lang.String)
	 */
	@Override
	public int removeTeamLeader(TeamLeader tl, String teamNumber) {
		int rc = -1;

		String query = "DELETE FROM TeamLeaderAssociation WHERE teamNumber = '"
				+ teamNumber
				+ "' and leaderId = (SELECT id FROM Person WHERE phoneno = '"
				+ tl.getPhone() + "')";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Delete exception in TeamLeaderAssociation db: "
					+ ex);
		}
		return (rc);
	}
}
