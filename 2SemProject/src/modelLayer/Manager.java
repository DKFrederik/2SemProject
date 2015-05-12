package modelLayer;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class Manager extends Staff{
	
	private int salary;

	public Manager(String fname, String lname, String email, String phone, String zipcode, String username, String password, int salary) {
		super(fname, lname, email, phone, zipcode, username, password);
		this.salary = salary;
	}
	
	public Manager() {
		super();
	}
	
	public int getSalary(){
		return salary;
	}
	
	public void setSalary(int salary){
		this.salary = salary;
	}
	

	

}
