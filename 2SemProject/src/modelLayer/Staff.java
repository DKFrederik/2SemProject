package modelLayer;

public class Staff extends Person{

	private String username;
	private String password;
	
	public Staff(String fname, String lname, String email, String phone, String zipcode, String username, String password) {
		super(fname, lname, email, phone, zipcode);
		this.username = username;
		this.password = password;
	}
	
	public Staff() {
		
	}

	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
