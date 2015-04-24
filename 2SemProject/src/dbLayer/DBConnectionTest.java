package dbLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBConnectionTest {

	@Test
	public void test() {
		DBConnection dbCon = DBConnection.getInstance();
		if(dbCon != null)
		{
			System.out.println("Connection to DB is ok");
		}
		else{
		    fail("Can not connect to the DB");
		}
	}

}
