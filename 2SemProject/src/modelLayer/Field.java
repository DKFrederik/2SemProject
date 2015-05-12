package modelLayer;

public class Field {
	
	private String type;
	//A field can contain n numbers of smaller fields. Example: field "1a", field "1b", field "1c"
	private String number; 
	private String length;
	private String width;
	
	public Field(String type, String number, String length, String width){
		this.type = type;
		this.number = number;
		this.length = length;
		this.width = width;
	}
	
	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	public Field() {
		
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setNumber(String number){
		this.number = number;
	}

}
