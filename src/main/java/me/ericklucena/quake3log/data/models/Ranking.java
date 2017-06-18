package me.ericklucena.quake3log.data.models;

import java.util.HashMap;

public class Ranking {
	
	HashMap<String, Player> players;
	
	public Ranking() {
		players = new HashMap<String, Player>();
	}

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public void setPlayers(HashMap<String, Player> players) {
		this.players = players;
	}
	
	public void addMatchStats(Match match) {
		for (Player player : match.getPlayers().values()) {
			addIfNotInRanking(player.getName());
			players.get(player.getName()).merge(player);
		}
	}
	
	private void addIfNotInRanking(String playerName) {
		if (!players.containsKey(playerName)) {
			players.put(playerName, new Player(playerName));
		}
	}
}
