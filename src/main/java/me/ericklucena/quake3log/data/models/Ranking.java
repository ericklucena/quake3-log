package me.ericklucena.quake3log.data.models;

import java.util.HashMap;

public class Ranking {
	
	HashMap<String, Player> players;

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public void setPlayers(HashMap<String, Player> players) {
		this.players = players;
	}
}
