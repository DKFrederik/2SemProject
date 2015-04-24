package modelLayer;

public class Person {
	
	private String fname;
	private String lname;
	private String email;
	private String phone;
	
	public Person(String fname, String lname, String email, String phone){
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
