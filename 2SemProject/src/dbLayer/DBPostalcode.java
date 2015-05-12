package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBPostalcode {
	private Connection con;

	/**
	 * Constructor for DBPerson() class. 
	 */
	public DBPostalcode() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	public String findCity(String zipcode) {
		String wClause = "  zipcode = '" + zipcode + "'";
		return singleWhere(wClause);
	}
	
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
	
	private String buildQuery(String wClause) {
		String query = "SELECT city FROM PostalCode";
		
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}


}
