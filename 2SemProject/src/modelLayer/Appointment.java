package modelLayer;

public class Appointment {

	private Team team;
	private int timeSlot;
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

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public String getTime() {
		if(timeSlot == 0) {
			return "17:01 - 18:00";
		}
		else if(timeSlot == 1) {
			return "18:01 - 19:00";
		}
		else if(timeSlot == 2) {
			return "19:01 - 20:00";
		}
		else if(timeSlot == 3) {
			return "20:01 - 21:00";
		}
		return "Fail";
	}
}
