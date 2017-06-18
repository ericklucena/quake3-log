package me.ericklucena.quake3log.data.models;

import java.util.HashMap;
import java.util.Iterator;

import me.ericklucena.quake3log.data.interfaces.Jsonable;
import me.ericklucena.quake3log.data.interfaces.Reportable;

public class Match implements Jsonable, Reportable {

	int id;
	private HashMap<String, Player> players;
	private int worldKills;

	public Match(int id) {
		this.id = id;
		this.players = new HashMap<String, Player>();
		worldKills = 0;
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

	public int getTotalKills() {
		int kills = worldKills;

		for (Player player : players.values()) {
			kills += player.getKills();
		}

		return kills;
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
		worldKills++;
	}

	private void addIfNotInMatch(String playerName) {
		if (!players.containsKey(playerName)) {
			players.put(playerName, new Player(playerName));
		}
	}

	@Override
	public String toReport() {
		String report = String.format("Game %d\n", id);
		report += String.format("Total kills: %d\n", getTotalKills());
		if (players.size() > 0) {
			for (Player player : players.values()) {
				report += String.format("\t%s", player.toReport());
			}
		} else {
			report += "\tNo players in match\n";
		}
		return report;
	}

	@Override
	public String toJson() {
		String json = "{";
		json += String.format("\"id\" : \"%d\",", id);
		json += String.format("\"players\" : [");

		Iterator<Player> iterator = players.values().iterator();

		while (iterator.hasNext()) {
			json += String.format("%s", iterator.next().toJson());
			if (iterator.hasNext()) {
				json += ",";
			}
		}
		json += String.format("]");
		json += String.format("}");
		return json;
	}
}
