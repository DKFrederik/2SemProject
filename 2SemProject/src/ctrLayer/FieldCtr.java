package ctrLayer;

import java.sql.Connection;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

/**
 * Controller for Field. Controls creation, update, delete, find of Field.
 * 
 * @author nichlas, frederik, peter, claus.
 * @version 20.05.2015
 * 
 */
public class FieldCtr {

	private static FieldCtr instance = null;
	private DBField dbF;

	private FieldCtr() {
		dbF = new DBField();
	}
	
	/**
	 * Creates an instance of the FieldCtr
	 */
	public static FieldCtr getInstance() {
		if (instance == null) {
			instance = new FieldCtr();
		}

		return instance;
	}

	/**
	 * Inserts a Field in the DB.
	 * 
	 * @param Field f
	 *            the Field object inserted.
	 * @return true or false to indicate success.
	 */
	public boolean insertField(Field f) throws Exception {
		boolean isSuccess = false;
		try {
			DBConnection.startTransaction();
			if (0 < dbF.insertField(f)) {
				isSuccess = true;
			}
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return isSuccess;
	}

	/**
	 * Finds a Field in the DB.
	 * 
	 * @param fieldNumber
	 *            the number of the field.
	 * @return the matching Field.
	 */
	public Field findField(String fieldNumber) {
		return dbF.findField(fieldNumber);
	}

	/**
	 * Deletes a Field from the DB with the fieldNumber.
	 * 
	 * @param fieldNumber
	 *            of the field that is to be deleted.
	 * @return true or false to indicate success.
	 */
	public boolean deleteField(String fieldNumber) {
		boolean isSuccess = false;
		try {
			DBConnection.startTransaction();
			if (0 < dbF.deleteField(fieldNumber)) {
				isSuccess = true;
			}
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return isSuccess;
	}

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
	public boolean createField(String fieldNumber, String type, int length,
			int width) throws Exception {
		Field f = new Field(fieldNumber, type, length, width);
		boolean isSuccess = false;
		try {
			DBConnection.startTransaction();
			if (0 < dbF.insertField(f)) {
				isSuccess = true;
			}
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return isSuccess;
	}

	/**
	 * Gets all the Fields in the DB.
	 * 
	 * @return A list of all Fields.
	 */
	public List<Field> getFields() {
		return dbF.getAllFields(true);
	}
}