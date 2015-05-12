package ctrLayer;

import java.sql.Connection;

import modelLayer.*;
import dbLayer.*;

public class TeamCtr {
	
	private static TeamCtr instance = null;
	
	public TeamCtr()
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
		return
	}
	
	public void deleteTeam(String teamNumber)
	{
		
	}
	
	public void updateTeam(Team t, String oldTeamNumber)
	{
		
	}
	
}
