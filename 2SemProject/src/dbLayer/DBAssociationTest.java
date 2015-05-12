package dbLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelLayer.Person;
import modelLayer.Player;
import modelLayer.Team;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBAssociationTest {

	DBAssociation dba;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dba = new DBAssociation();
	}

	@After
	public void tearDown() throws Exception {
	}
	
}
