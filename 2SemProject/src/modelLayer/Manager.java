package modelLayer;

public class Manager extends Staff{
	
	private int salary;

	public Manager(String name, String email, String phone, String username, String password, int salary) {
		super(name, email, phone, username, password);
		this.salary = salary;
	}
	
	public int getSalary(){
		return salary;
	}
	
	public void setSalary(int salary){
		this.salary = salary;
	}
	

}
