package dbLayer;

import java.util.ArrayList;

import modelLayer.Field;

public interface IFField {

	/**
	 * Retrieves all the fields from the database as an ArrayList
	 * @param retriveAssociation Determines if the method should retrieve association to the object.
	 * @return a list of fields.
	 */
	public abstract ArrayList<Field> getAllFields(boolean retriveAssociation);

	/**
	 * Finds a field in the database by calling the searchWhere method. 
	 * @param fieldNumber the Field objects phone no.
	 * @return the Field matching the fieldnumber.
	 */
	public abstract Field findField(String fieldNumber);

	/**
	 * Method for updating information about a field in the db.
	 * @param f The field object that is to be updated in the database.
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 */
	public abstract int updateField(Field f);

	/**
	 * Deletes a field from the database with a matching fieldNumber. 
	 * @param fieldNumber The number of the field that is to be removed.
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 */
	public abstract int deleteField(String fieldNumber);
	
	/**
	 * Inserts a field into the database.
	 * @param f a field.
	 * @return An int corresponding to the amount of changed rows or -1 if failed.
	 */
	public abstract int insertField(Field f);
}