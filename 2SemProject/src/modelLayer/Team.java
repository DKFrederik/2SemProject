package modelLayer;

import java.util.List;

public class Team {
	
	private List<Player> players;
	private int league;
	
	public Team(int league){
		this.league = league;
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

}
