package dbLayer;

import modelLayer.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * DBProduct.java
 * 
 * @author Peter, Frederik, Claus og Nichlas.
 * @version 20.03.2015
 *
 */
public class DBPlayer {
	private Connection con;

	/**
	 * Constructor for DBProduct() class. 
	 */
	public DBPlayer() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * 
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return An ArrayList of Product objects.
	 */
	public ArrayList<Player> getAllProducts(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	/**
	 * 
	 * @param name The name of the product that you wish to find.
	 * @param retriveAssociation Determines if associations should be retrieved or not. 
	 * @return A Product Object if found, null if not.
	 */
	public Player findPlayer(String phone, boolean retriveAssociation) {
		String wClause = "  name = '" + phone + "'";
		return singleWhere(wClause, retriveAssociation);
	}
	
	/**
	 * 
	 * @param pro The product that is to be inserted
	 * @return 
	 * @throws Exception 
	 */
	public int insertProduct(Player player) throws Exception {

		int rc = -1;
		String query = "INSERT INTO Player(fname, lname, email, phone, age, position)  VALUES('"
				//+ pro.getSupplier()
				//+ "','"
				+ player.getFname()
				+ "','"
				+ player.getLname()
				+ "','"
				+ player.getEmail()
				+ "','"
				+ player.getPhone()
				+ "','"
				+ player.getAge()
				+ "','"
				+ player.getPosition() + "')";

		System.out.println("insert : " + query);
		try { // insert new Player
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Player not created");
			throw new Exception("Player is not inserted correct");
		}
		return (rc);
	}

	/**
	 * 
	 * @param cus The customer object that is to be updated in the database.
	 * @return
	 */
	public int updateProduct(Player player, String phone) {
		Player playerObj = player;
		int rc = -1;

		String query = "UPDATE Player SET " 
				+ "fname ='" + playerObj.getFname() + "', " 
				+ "lname ='" + playerObj.getLname() + "', " 
				+ "email ='" + playerObj.getEmail() + "', " 
				+ "phone ='" + playerObj.getPhone() + "', " 
				+ "age ='" + playerObj.getAge() + "', " 
				+ "position ='" + playerObj.getPosition() + "'";
		
		query += "WHERE phone = '" + phone + "'";
		System.out.println("Update query:" + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		
		catch (Exception ex) {
			System.out.println("Update exception in player db: " + ex);
		}
		return (rc);
	}

	public int deletePlayer(String phone) {
		int rc = -1;

		String query = "DELETE FROM Player WHERE phone = '" + phone + "'";
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Delete exception in product db: " + ex);
		}
		return (rc);
	}

	/**
	 * 
	 * @param wClause where clause for the query.
	 * @param retrieveAssociation whether or not to retrieve associations.
	 * @return An ArrayList of players.
	 */
	private ArrayList<Player> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Player> list = new ArrayList<Player>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Player playerObj = new Player();
				playerObj = buildPlayer(results);
				list.add(playerObj);
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

	private Player singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Player playerObj = new Player();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				playerObj = buildPlayer(results);
				stmt.close();
				
				// get supplier should be implemented around here.
				
			} else {
				playerObj = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return playerObj;
	}

	/**
	 * Builds a SQL query
	 * @param wClause where clause.
	 * @return the build SQL query.
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT fname, lname, email, phone, age, position FROM Player";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	/**
	 * Builds a player from a ResultSet.
	 * 
	 * @param results the results returned by the DMBS
	 * @return a player
	 */
	private Player buildPlayer(ResultSet results){
		Player playerObj = new Player();
		try 
		{
			playerObj.setFname(results.getString("fname"));
			playerObj.setLname(results.getString("lname"));
			playerObj.setEmail(results.getString("email"));
			playerObj.setPhone(results.getString("phone"));
			playerObj.setAge(Integer.parseInt(results.getString("age")));
			playerObj.setPosition(results.getString("position").charAt(0));
		} 
		catch (Exception e) {
			System.out.println("error in building the customer object");
		}
		return playerObj;
	}
	
}

