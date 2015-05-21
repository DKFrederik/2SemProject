package modelLayer;

import java.util.ArrayList;

public class ScheduleTest {

	public void SheduleTest() {
	}

	// Test
	public static void main(String[] args) {
		int noOfVer = 10;

		Player claus = new Player();
		Player peter = new Player();
		Player frederik = new Player();
		Player nichlas = new Player();
		Player finn = new Player();
		Player henrik = new Player();
		Player bendtner = new Player();
		Player john = new Player();
		Player dolan = new Player();
		Player donJohn = new Player();

		ArrayList<Team> testTeamList = new ArrayList<>();

		for (int i = 0; i < noOfVer; i++) {
			testTeamList.add(new Team("" + i, 1));
			for (int j = 0; j < 11; j++) {
				testTeamList.get(i).addPlayer(new Player());
			}

		}

		testTeamList.get(0).addPlayer(peter);
		testTeamList.get(4).addPlayer(peter);
		testTeamList.get(3).addPlayer(peter);
		testTeamList.get(1).addPlayer(nichlas);
		testTeamList.get(3).addPlayer(nichlas);
		testTeamList.get(0).addPlayer(frederik);
		testTeamList.get(4).addPlayer(frederik);
		testTeamList.get(2).addPlayer(henrik);
		testTeamList.get(1).addPlayer(henrik);
		testTeamList.get(3).addPlayer(finn);
		testTeamList.get(1).addPlayer(bendtner);
		testTeamList.get(4).addPlayer(john);
		testTeamList.get(1).addPlayer(dolan);
		testTeamList.get(2).addPlayer(donJohn);
		testTeamList.get(0).addPlayer(donJohn);
		testTeamList.get(3).addPlayer(donJohn);

		ArrayList<Field> testFields = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			testFields.add(new Field("" + i, "Training", 100, 70));
		}

		Schedule schedule = new Schedule(testFields);

		for (int i = 0; i < testTeamList.size(); i++) {
			schedule.addAppointment(testTeamList.get(i));
		}

		schedule.createGraph();
		schedule.makeSchedule();
		schedule.print();
	}
}
