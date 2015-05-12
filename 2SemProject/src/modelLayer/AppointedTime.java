package modelLayer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AppointedTime {
	
	private Field field;
	private Match match;
	private Date date;
	private LinkedGraph teamGraph;
	
	public AppointedTime(int noVer){
		teamGraph = new LinkedGraph(noVer);
	}
	
	//Test
	public static void main(String[] args)
	{
		int noOfVer = 5;
		
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
		
		AppointedTime appointedTime = new AppointedTime(noOfVer);
		appointedTime.createGraph(testTeamList);
		appointedTime.makeSchedule();
		
	}
	
	
	public void createGraph(List<Team> teams)
	{
		int numberOfTeams = teams.size();
		
		for(int i=0; i<numberOfTeams; i++)
		{
			teamGraph.addVertex(new Vertex("Hold" + teams.get(i).getNumber()));
			
			System.out.println(teamGraph.getNoOfVertices() + " " + teamGraph.getVertex(i).getName());
		}
		
		
		System.out.println(numberOfTeams);
		
		for(int i = 0; i < numberOfTeams-1; i++)
		{
			for(int j = i+1; j < numberOfTeams; j++)
			{
				if(compareLists(teams.get(i).getPlayers(),teams.get(j).getPlayers()))
				{
					teamGraph.addEdge(teamGraph.getVertex(i), teamGraph.getVertex(j));
				}
			}
		}

	}
	
	private void makeSchedule()
	{
		teamGraph.graphColoring();	
		for(int i = 0;i < teamGraph.getNoOfVertices();i++)
		{
			if(teamGraph.getVertex(i).getColor() == 0)
			{
				System.out.println("Hold " + i + " skal spille 18-19");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 1)
			{
				System.out.println("Hold " + i + " skal spille 19-20");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 2)
			{
				System.out.println("Hold " + i + " skal spille 20-21");
			}
			
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
}
