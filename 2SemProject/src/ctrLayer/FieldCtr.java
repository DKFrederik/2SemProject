package ctrLayer;

import java.sql.Connection;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

/**
 * Controller for Field. Controls creation, update, delete, find of Field.
 * @author nichlas, frederik, peter, claus.
 * @version 20.05.2015
 * 
 */
public class FieldCtr {

	private static FieldCtr instance = null;
	private DBField fDB;

	public FieldCtr() {
		fDB = new DBField();
	}

	public static FieldCtr getInstance() {
		if (instance == null) {
			instance = new FieldCtr();
		}

		return instance;
	}

	public void insertField(Field f) {

	}

	/**
	 * Finds a Field in the DB.
	 * 
	 * @param fieldNumber
	 *            the number of the field.
	 * @return the matching Field.
	 */
	public Field findField(String fieldNumber) {
		return fDB.findField(fieldNumber);
	}

	/**
	 * Deletes a Field from the DB with the fieldNumber.
	 * 
	 * @param fieldNumber
	 *            of the field that is to be deleted.
	 * @return true or false to indicate success.
	 */
	public boolean deleteField(String fieldNumber) {
		if (0 < fDB.deleteField(fieldNumber)) {
			return true;
		} else {
			return false;
		}
	}

//  skal dette overhovedet bruges?
//	/**
//	 * Updates the field.
//	 * 
//	 * @param f
//	 *            the Field to be update.
//	 * @param oldFieldNumber
//	 *            the fieldNumber before the update.
//	 * @return true or false to indicate success
//	 */
//	public boolean updateField(Field f, String oldFieldNumber) throws Exception {
//		if (0 < fDB.updateField(f)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	/**
	 * Inserts a field into the DB
	 * 
	 * @param fieldNumber
	 *            the fieldNumber of the field.
	 * @param type
	 *            the type of the field.
	 * @param length
	 *            the length of the field.
	 * @param width
	 *            the width of the field.
	 * @return true or false depending on success.
	 */
	public boolean createField(String fieldNumber, String type, int length, int width) throws Exception {
		Field f = new Field(fieldNumber, type, length, width);
		if (0 < fDB.insertField(f)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets all the Fields in the DB.
	 * 
	 * @return A list of all Fields.
	 */
	public List<Field> getFields() {
		return fDB.getAllFields(true);
	}
}