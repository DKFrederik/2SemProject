package modelLayer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private List<Appointment> appointments;
	private Date date;
	private LinkedGraph teamGraph;
	private List<Field> fields;
	
	public Schedule(List<Field> fields){
		appointments = new ArrayList<>();
		this.fields = fields;
	}
	
	//Test
	public static void main(String[] args)
	{
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
		
		for(int i=0; i<noOfVer;i++)
		{	
			testTeamList.add(new Team(""+i));
			for(int j = 0; j < 11; j++)
			{
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
		
		ArrayList<Field> testFields= new ArrayList<>();
		
		for(int i= 0; i < 3; i++)
		{
			testFields.add(new Field(""+i,"Training", 100, 70));
		}
		
		Schedule schedule = new Schedule(testFields);
		
		
		for(int i = 0; i < testTeamList.size(); i++)
		{
			schedule.addAppointment(testTeamList.get(i));
		}
		
		schedule.createGraph();
		schedule.makeSchedule();
		schedule.print();
	}
	
	
	public void createGraph()
	{
		int numberOfTeams = appointments.size();
		
		teamGraph = new LinkedGraph(numberOfTeams);
		
		for(int i=0; i<numberOfTeams; i++)
		{
			teamGraph.addVertex(new Vertex("Hold" + appointments.get(i).getTeam().getNumber()));
			
			System.out.println(teamGraph.getNoOfVertices() + " " + teamGraph.getVertex(i).getName());
		}
		
		
		System.out.println(numberOfTeams);
		
		for(int i = 0; i < numberOfTeams-1; i++)
		{
			for(int j = i+1; j < numberOfTeams; j++)
			{
				if(compareLists(appointments.get(i).getTeam().getPlayers(),appointments.get(j).getTeam().getPlayers()))
				{
					teamGraph.addEdge(teamGraph.getVertex(i), teamGraph.getVertex(j));
				}
			}
		}

	}
	
	private void makeSchedule()
	{
		teamGraph.graphColoring();
		
		int numberOfColors = teamGraph.getNoOfColors();
		
		int[] colors = new int[numberOfColors];
		
		for(int i = 0;i < teamGraph.getNoOfVertices();i++)
		{
			
			if(teamGraph.getVertex(i).getColor() == 0)
			{
				appointments.get(i).setTime(0);
				appointments.get(i).setField(fields.get(colors[0]));
				colors[0]++;
				//System.out.println("Hold " + i + " skal spille 18-19");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 1)
			{
				appointments.get(i).setTime(1);
				appointments.get(i).setField(fields.get(colors[1]));
				colors[1]++;
				//System.out.println("Hold " + i + " skal spille 19-20");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 2)
			{
				appointments.get(i).setTime(2);
				appointments.get(i).setField(fields.get(colors[2]));
				colors[2]++;
				//System.out.println("Hold " + i + " skal spille 20-21");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 3)
			{
				appointments.get(i).setTime(3);
				appointments.get(i).setField(fields.get(colors[3]));
				colors[3]++;
				//System.out.println("Hold " + i + " skal spille 20-21");
			}
			
		}
	}
	
	public void print()
	{
		for(int i = 0; i < appointments.size(); i++)
		{
			System.out.println("Team: " + appointments.get(i).getTeam().getNumber() + 
					" Field: " + appointments.get(i).getField().getFieldNumber() + 
					" Time: " + appointments.get(i).getTime());
		}
	}
	
	private boolean compareLists(List<Player> list1, List<Player> list2)
	{
		int sizeOne = list1.size();
		int sizeTwo = list2.size();
		boolean found = false;
		for(int i = 0; i < sizeOne && found == false; i++)
			for(int j = 0; j < sizeTwo && found == false; j++)
			{
				if(list1.get(i).equals(list2.get(j)))
				{
					found = true;
				}
			}
		return found;
	}
	
	public void addAppointment(Team t)
	{
		appointments.add(new Appointment(t));
	}
	
	public void removeAppointment(Team t)
	{
		boolean found = false;
		for(int i = 0; i < appointments.size() && found == false; i++)
		{
			if(appointments.get(i).getTeam().equals(t))
			{
				appointments.get(i);
				found = true;
			}
		}
	}
	
	
}
