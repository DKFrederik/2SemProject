package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Manager;
import modelLayer.Player;
import modelLayer.Referee;
import modelLayer.Staff;
import modelLayer.Team;
import modelLayer.TeamLeader;

/**
 * 
 * @author nichlas, frederik, claus, peter
 * @Description Database class for teams.
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
		String wClause = "  name = '" + teamNumber + "'";
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
			
			// Get suppliers around here

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
	
	public int insertTeam(Team t) throws Exception {

		int rc = -1;
		String query = "INSERT INTO Team(teamNumber, league) VALUES('"
						+ t.getNumber() + "','" + t.getLeague() + "')";
		
		System.out.println("insert : " + query);
		try { // insert new Person
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Team not created");
			System.out.println(ex.getErrorCode());
			System.out.println(ex.getMessage());
			throw new Exception("Team is not inserted correct");
		}
		return (rc);

	}
}
