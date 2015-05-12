package modelLayer;

import java.util.ArrayList;
import java.util.List;

public class Team {
	
	private List<Player> players;
	private int league;
	private String number;
	
	public Team(String number){
		this.number = number;
		this.players = new ArrayList<Player>();
	}
	
	public Team() {
		// TODO Auto-generated constructor stub
	}

	public void addPlayer(Player player){
		players.add(player);
	}
	
	public void removePlayer(Player player){
		players.remove(player);
	}
	
	public void getTeamSize(){
		players.size();
	}
	
	public int getLeague(){
		return league;
	}
	
	public void setLeague(int league){
		this.league = league;
	}
	
	public String getNumber()
	{
		return this.number;
	}
	
	public void setNumber(String number)
	{
		this.number = number;
	}
	
	public List<Player> getPlayers()
	{
		return this.players;
	}

}
