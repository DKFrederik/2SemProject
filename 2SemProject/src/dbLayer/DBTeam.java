package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Team;

/**
 * 
 * @author nichlas, frederik, claus, peter
 * @version 12.05.2015
 * Database class for teams. Handles insert, delete, update, find and find all.
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
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return An ArrayList of Team objects.
	 */
	public ArrayList<Team> getAllTeams(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	/**
	 * 
	 * @param teamNumber The number of the team that you wish to find.
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return A Team Object if found, null if not.
	 */
	public Team findTeam(String teamNumber, boolean retriveAssociation) {
		String wClause = "  teamNumber = '" + teamNumber + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	/**
	 * 
	 * @param wClause where clause for the query.
	 * @param retrieveAssociation whether or not to retrieve associations.
	 * @return An ArrayList of Teams.
	 */
	private ArrayList<Team> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Team> list = new ArrayList<Team>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Team teamObj = new Team();
				teamObj = buildTeam(results);
				list.add(teamObj);
			}// end while
			stmt.close();
			
			//
			// ASSOCIATIONS!! 
			//


		}
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @param wClause where clause for the query.
	 * @param retrieveAssociation whether or not to retrieve associations.
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
				stmt.close();
				
				//
				// ASSOCIATIONS!! 
				//
			} else {
				teamObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return teamObj;
	}
	
	private String buildQuery(String wClause) {
		String query = "SELECT teamNumber, league FROM Team";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

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
	
	public int updateTeam(Team t, String oldTeamNo) throws Exception {

		int rc = -1;
		String query = "UPDATE Team SET "
						+ "teamNumber = '" + t.getNumber() + "'," 
						+ "league = '" + t.getLeague();
		
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

	
	public int deleteTeam(String teamNumber) {
		int rc = -1;

		String query = "DELETE FROM Team WHERE teamNumber = '" + teamNumber + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Delete exception in Team db: " + ex);
		}
		return (rc);

	}
	
	private Team buildTeam(ResultSet results) {
		Team t = new Team();
		
		try {
			t.setNumber(results.getString("teamNumber"));
			t.setLeague(Integer.parseInt(results.getString("league")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

}
