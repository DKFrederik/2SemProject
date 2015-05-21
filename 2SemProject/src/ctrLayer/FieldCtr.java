package ctrLayer;

import java.sql.Connection;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

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

	public boolean deleteField(String fieldNumber) {
		if (0 < fDB.deleteField(fieldNumber)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateField(Field f, String oldFieldNumber) throws Exception {
		if (0 < fDB.updateField(f)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean createField(String fieldNumber, String type, int length, int width) throws Exception {
		Field f = new Field(fieldNumber, type, length, width);
		if (0 < fDB.insertField(f)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Field> getFields() {
		return fDB.getAllFields(true);
	}
}