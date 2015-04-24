package modelLayer;

public class Staff extends Person{
	
	public Staff(String name, String email, String phone, String username, String password) {
		super(name, email, phone);
		this.username = username;
		this.password = password;
	}

	private String username;
	private String password;
	
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
