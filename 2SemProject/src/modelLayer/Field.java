package modelLayer;

import java.util.ArrayList;
import java.util.List;

public class Field {
	
	private String type;
	//A field can contain n numbers of smaller fields. Example: field "1a", field "1b", field "1c"
	private String number; 
	private int length;
	private int width;
	private List<Field> fields;
	
	public Field(String number, String type, int length, int width){
		this.number = number;
		this.type = type;
		this.length = length;
		this.width = width;
		this.fields = new ArrayList<Field>();
	}
	
	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	public Field() {
		
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the field number 
	 */
	public String getFieldNumber() {
		return number;
	}
	
	public void setFieldNumber(String number) {
		this.number = number;
	}
	
	/**
	 * @return size of field
	 */
	public void getFieldSize() {
		fields.size();
	}

	public void deleteField() {
		fields.remove(getFieldNumber());
	}
}
