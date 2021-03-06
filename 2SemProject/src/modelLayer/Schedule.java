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
	private boolean isComplete;
	

	/**
	 * Contructor for the Schedule class.
	 * @param fields a list of fields.
	 * @param date the schedules date.
	 */
	public Schedule(List<Field> fields, java.sql.Date date){
		appointments = new ArrayList<>();
		this.fields = fields;
		this.date = date;
		this.isComplete = false;
	}
	/**
	 * Getter for the creator
	 * @return a Person.
	 */
	public Person getCreator() {
		return creator;
	}
	/**
	 * Setter for the creator.
	 * @param creator a Person.
	 */
	public void setCreator(Person creator) {
		this.creator = creator;
	}
	
	/**
	 * Create the graph for the schedule using. 
	 */
	public void createGraph()
	{
		int numberOfTeams = appointments.size();
		
		teamGraph = new LinkedGraph(numberOfTeams);
		
		for(int i=0; i<numberOfTeams; i++)
		{
			teamGraph.addVertex(new Vertex(appointments.get(i).getTeam().getTeamNumber()));
		}
		
		for(int i = 0; i < numberOfTeams-1; i++)
		{
			for(int j = i+1; j < numberOfTeams; j++)
			{
				if(appointments.get(i).getTeam().getManager().getPhone().equals(appointments.get(j).getTeam().getManager().getPhone()) ||
						compareLists(appointments.get(i).getTeam().getPlayers(),appointments.get(j).getTeam().getPlayers()))
				{
					teamGraph.addEdge(teamGraph.getVertex(i), teamGraph.getVertex(j));
				}
			}
		}

	}
	/**
	 * Creates the finished schedule with fields and timeslots assigned.
	 */
	public void makeSchedule()
	{
		teamGraph.graphColoring();
		
		int numberOfColors = teamGraph.getNoOfColors();
		
		int[] fieldList = new int[numberOfColors];
		
		for(int i = 0;i < teamGraph.getNoOfVertices();i++)
		{
			
			if(teamGraph.getVertex(i).getColor() == 0)
			{
				appointments.get(i).setTimeSlot(0);
				appointments.get(i).setField(fields.get(fieldList[0]));
				fieldList[0]++;
			}
			
			else if(teamGraph.getVertex(i).getColor() == 1)
			{
				appointments.get(i).setTimeSlot(1);
				appointments.get(i).setField(fields.get(fieldList[1]));
				fieldList[1]++;
				//System.out.println("Hold " + i + " skal spille 19-20");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 2)
			{
				appointments.get(i).setTimeSlot(2);
				appointments.get(i).setField(fields.get(fieldList[2]));
				fieldList[2]++;
				//System.out.println("Hold " + i + " skal spille 20-21");
			}
			
			else if(teamGraph.getVertex(i).getColor() == 3)
			{
				appointments.get(i).setTimeSlot(3);
				appointments.get(i).setField(fields.get(fieldList[3]));
				fieldList[3]++;
			}
			
			isComplete = true;
		}
	}

	/**
	 * Compares two lists of Players to see if duplicate exist.
	 * @param list1 first list to compare.
	 * @param list2 second list to compare.
	 * @return True if duplicates found, else false.
	 */
	private boolean compareLists(List<Player> list1, List<Player> list2)
	{
		int sizeOne = list1.size();
		int sizeTwo = list2.size();
		boolean found = false;
		for(int i = 0; i < sizeOne && found == false; i++)
			for(int j = 0; j < sizeTwo && found == false; j++)
			{
				if(list1.get(i).getPhone().equals((list2.get(j).getPhone())))
				{
					found = true;
				}
			}
		return found;
	}
	/**
	 * Adds an appointement to the Schedule.
	 * @param t a Team for the Appointment.
	 */
	public void addAppointment(Team t)
	{ 
		appointments.add(new Appointment(t));
	}
	/**
	 * Returns the list of appointments.
	 * @return A list of Appointments.
	 */
	public List<Appointment> getAppointments()
	{
		return this.appointments;
	}
	/**
	 * Sets the appointment field using a list of appointments.
	 * @param appList list of Appointments.
	 */
	public void setAppointments(List<Appointment> appList)
	{
		this.appointments = appList;
	}
	/**
	 * Removes an appointment for a Team.
	 * @param t the Team to remove the appointment for.
	 */
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
	/**
	 * Getter for date.
	 * @return the date.
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Setter for date
	 * @param date a date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Getter for isComplete. isComplete is true if makeSchedule() has been run.
	 * @return true if makeSchedule() has run, false if not.
	 */
	public boolean isComplete() {
		return isComplete;
	}
}
