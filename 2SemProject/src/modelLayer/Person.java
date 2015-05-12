package modelLayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String zipcode;
	private String city;
	
	public Person(String fname, String lname, String email, String phone, String zipcode){
		if(email == null){
			email = "";
		}
		this.zipcode = zipcode;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}
	
	public Person(){
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
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void checkEmail() {
	       String email = "clausjoergensen91@gmail.com";
	       String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
	        
	        Pattern pattern = Pattern.compile(emailPattern);
	        Matcher m = pattern.matcher(email);

	        if(m.find())
	            System.out.println(email + " is ok");
	        else
	            System.out.println(email + " is not ok");
	}


}
