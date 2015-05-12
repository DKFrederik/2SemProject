package modelLayer;

public class Field {
	
	private String type;
	//A field can contain n numbers of smaller fields. Example: field "1a", field "1b", field "1c"
	private String number; 
	
	public Field(String type, String number){
		this.type = type;
		this.number = number;
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
