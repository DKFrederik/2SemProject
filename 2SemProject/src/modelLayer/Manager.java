package modelLayer;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class Manager extends Staff{
	
	private double salary;

	public Manager(String fname, String lname, String email, String phone, String zipcode, String username, String password, double salary) {
		super(fname, lname, email, phone, zipcode, username, password);
		this.salary = salary;
	}
	
	public Manager() {
		super();
	}
	
	public double getSalary(){
		return salary;
	}
	
	public void setSalary(double d){
		this.salary = d;
	}
	

	

}
