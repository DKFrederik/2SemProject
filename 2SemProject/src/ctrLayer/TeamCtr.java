package ctrLayer;

import java.sql.Connection;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

public class TeamCtr {
	
	private static TeamCtr instance = null;
	
	private TeamCtr()
	{
		
	}
	
	public static TeamCtr getInstance()
	{
		if(instance == null)
		{
			instance = new TeamCtr();
		}
		
		return instance;
	}
	
	public void insertTeam(Team t)
	{
		
	}
	
	public Team findTeam(String teamNumber)
	{
		return null;
	}
	
	public void deleteTeam(String teamNumber)
	{
		
	}
	
	public void updateTeam(Team t, String oldTeamNumber)
	{
		
	}
	
	public List<Team> getTeams(List<String> teamNumberList)
	{
		return null;
	}
	
}
