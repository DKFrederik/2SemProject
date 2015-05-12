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
	
	public static void main(String[] args)
	{
		int noOfVer = 6;
		
		ArrayList<Team> testTeamList = new ArrayList<>();
		for(int i=0; i<noOfVer;i++)
		{
			testTeamList.add(new Team(""+i));
		}
		
		AppointedTime appointedTime = new AppointedTime(noOfVer);
		appointedTime.createGraph(testTeamList);
		
		
	}
	public void createGraph(List<Team> teams)
	{
		for(int i=0; i<teams.size(); i++)
		{
			teamGraph.addVertex(new Vertex("Hold" + teams.get(i).getNumber()));
			
			System.out.println(teamGraph.getNoOfVertices() + " " + teamGraph.getVertexName(i));
		}
		
		int numberOfTeams = teams.size();
		Player refPlayer;
		
		for(int i = 0; i < teams.size(); i++)
		{
			int numberOfPlayers = teams.get(i).getPlayers().size();
			for(int j = 0; j < numberOfPlayers; j++)
			{
				refPlayer = teams.get(i).getPlayers().get(j);
			}
		}
	}

	
}
