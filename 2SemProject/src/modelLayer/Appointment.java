package modelLayer;

public class Appointment {

	private Team team;
	private int time;
	private Field field;
	
	public Appointment(Team t){
		this.team = t;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}
