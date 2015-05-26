package dbLayer;

import java.util.ArrayList;

import modelLayer.Person;

public interface IFPerson {

	/**
	 * Retrieves all Persons from the Person table in the db.
	 * 
	 * @param retriveAssociation
	 *            Determines if associations should be retrieved or not.
	 * @return An ArrayList of Person objects.
	 */
	public abstract ArrayList<Person> getAllPersons(boolean retriveAssociation);

	/**
	 * 
	 * @param phone
	 *            The phone number of the person that you wish to find.
	 * @param retriveAssoc
	 *            .iation Determines if associations should be retrieved or not.
	 * @return A Person Object if found, null if not.
	 */
	public abstract Person findPerson(String phone, boolean retriveAssociation);

	/**
	 * Inserts firstname, lastname and so on into the Person table in the
	 * database.
	 * 
	 * @param p
	 *            The person that is to be inserted
	 * @return the number of rows changed in the database.
	 * @throws Exception
	 */
	public abstract int insertPerson(Person p) throws Exception;

	/**
	 * Updates the basic information of a Person in the Person table in the
	 * database.
	 * 
	 * @param p
	 *            The Person object that is to be updated in the database.
	 * @return the number of rows change in the database
	 */
	public abstract int updatePerson(Person p, String oldPhone);

	/**
	 * Deletes a Person from the Person table in the database. The delete
	 * process also remove foreign keys using that Person tuple
	 * 
	 * @param phone
	 *            The phone number of the person that you wish to delete.
	 * @return the number of rows change in the database.
	 */
	public abstract int deletePerson(String phone);

}