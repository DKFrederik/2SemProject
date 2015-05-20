package ctrLayer;

import java.sql.Connection;
import java.util.List;

import modelLayer.*;
import dbLayer.*;

public class TeamCtr {
	
	private static TeamCtr instance = null;
	private DBTeam dbT;
	
	private TeamCtr()
	{
		dbT = new DBTeam();
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
		return dbT.findTeam(teamNumber, true);
	}
	
	public boolean deleteTeam(String teamNumber)
	{
		if(0 < dbT.deleteTeam(teamNumber)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean updateTeam(Team t, String oldTeamNumber) throws Exception
	{
		if(0 < dbT.updateTeam(t, oldTeamNumber)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<Team> getTeams(List<String> teamNumberList)
	{
		return dbT.getAllTeams(true);
	}
	
}
