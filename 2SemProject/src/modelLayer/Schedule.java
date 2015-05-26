package modelLayer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private List<Appointment> appointments;
	private Date date; 
	private LinkedGraph teamGraph;
	private List<Field> fields;
	private Person creator;
	
	public Person getCreator() {
		return creator;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public Schedule(List<Field> fields, java.sql.Date date){
		appointments = new ArrayList<>();
		this.fields = fields;
		this.date = date;
	}
	
	public void createGraph()
	{
		int numberOfTeams = appointments.size();
		
		teamGraph = new LinkedGraph(numberOfTeams);
		
		for(int i=0; i<numberOfTeams; i++)
		{
			teamGraph.addVertex(new Vertex("Hold " + appointments.get(i).getTeam().getTeamNumber()));
			
			//System.out.println(teamGraph.getNoOfVertices() + " " + teamGraph.getVertex(i).getName());
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
	
	public void makeSchedule()
	{
		teamGraph.graphColoring();
		
		int numberOfColors = teamGraph.getNoOfColors();
		
		int[] colors = new int[numberOfColors];
		
		for(int i = 0;i < teamGraph.getNoOfVertices();i++)
		{
			
			if(teamGraph.getVertex(i).getColor() == 0)
			{
				appointments.get(i).setTimeSlot(0);
				appointments.get(i).setField(fields.get(colors[0]));
				colors[0]++;
				//System.out.println("Hold " + i + " skal spille 18-19");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 1)
			{
				appointments.get(i).setTimeSlot(1);
				appointments.get(i).setField(fields.get(colors[1]));
				colors[1]++;
				//System.out.println("Hold " + i + " skal spille 19-20");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 2)
			{
				appointments.get(i).setTimeSlot(2);
				appointments.get(i).setField(fields.get(colors[2]));
				colors[2]++;
				//System.out.println("Hold " + i + " skal spille 20-21");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 3)
			{
				appointments.get(i).setTimeSlot(3);
				appointments.get(i).setField(fields.get(colors[3]));
				colors[3]++;
			}
			
		}
	}
	
	public void print()
	{
		for(int i = 0; i < appointments.size(); i++)
		{
			System.out.println("Team: " + appointments.get(i).getTeam().getTeamNumber() + 
					" Field: " + appointments.get(i).getField().getFieldNumber() + 
					" Time: " + appointments.get(i).getTimeSlot());
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
	
	public List<Appointment> getAppointments()
	{
		return this.appointments;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
