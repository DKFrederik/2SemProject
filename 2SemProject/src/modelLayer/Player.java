package modelLayer;

public class Player extends Person{
	
	private int age;
	private String position;
	
	public Player(String name, String email, String phone, int age, String position) {
		super(name, email, phone);
		this.age = age;
		this.position = position;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
