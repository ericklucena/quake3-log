package me.ericklucena.quake3log.data.models;

import java.util.HashMap;

public class Match {
	
	int id;
	HashMap<String, Player> players;
	
	public Match(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HashMap<String, Player> getPlayers() {
		return players;
	}
	public void setPlayers(HashMap<String, Player> players) {
		this.players = players;
	}
	
	public void addKill(String playerName) {
		addIfNotInMatch(playerName);
		players.get(playerName).kill();
	}
	
	public void addDeath(String playerName) {
		addIfNotInMatch(playerName);
		players.get(playerName).death();		
	}

	public void addSuicide(String playerName) {
		addIfNotInMatch(playerName);
		players.get(playerName).death();		
	}
	
	private void addIfNotInMatch(String playerName) {
		if (!players.containsKey(playerName)) {
			players.put(playerName, new Player(playerName));
		}
	}
	
}
