package modelLayer;

public class Manager extends Staff{
	
	private int salary;

	public Manager(String fname, String lname, String email, String phone, String username, String password, int salary) {
		super(fname, lname, email, phone, username, password);
		this.salary = salary;
	}
	
	public int getSalary(){
		return salary;
	}
	
	public void setSalary(int salary){
		this.salary = salary;
	}
	

}
