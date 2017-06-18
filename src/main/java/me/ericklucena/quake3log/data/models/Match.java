package me.ericklucena.quake3log.data.models;

import java.util.HashMap;

public class Match {
	
	int id;
	HashMap<String, Player> players;
	
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
	
}
