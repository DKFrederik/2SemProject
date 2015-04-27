package modelLayer;

public class Player extends Person{
	
	private int age;
	private char position; //Goalkeeper "G", Defense "D", Midfield "M", Forward "F"
	
	public Player(String fname, String lname, String email, String phone, int age, char position) {
		super(fname, lname, email, phone);
		this.age = age;
		this.position = position;
	}
	
	public Player(){
		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getPosition() {
		return position;
	}

	public void setPosition(char position) {
		this.position = position;
	}

}
