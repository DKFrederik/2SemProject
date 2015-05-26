package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBPostalcode implements IFPostalCode {
	private Connection con;

	/**
	 * Constructor for DBPostalcode() class. 
	 */
	public DBPostalcode() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/* (non-Javadoc)
	 * @see dbLayer.IFPostalCode#findCity(java.lang.String)
	 */
	@Override
	public String findCity(String zipcode) {
		String wClause = "  zipcode = '" + zipcode + "'";
		return singleWhere(wClause);
	}
	
	/**
	 * Finds a city in the database matching the wClause.
	 * @param wClause
	 * @return the city name
	 */
	private String singleWhere(String wClause) {
		ResultSet results;
		String city = "";

		String query = buildQuery(wClause);
		System.out.println(query);
		try { 
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				city = results.getString("city");
				stmt.close();				
			} else {
				city = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return city;
	}
	
	/**
	 * Builds the query
	 * @param wClause
	 * @return the query. 
	 */
	private String buildQuery(String wClause) {
		String query = "SELECT city FROM PostalCode";
		
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}


}
