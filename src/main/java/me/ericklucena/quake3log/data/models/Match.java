package me.ericklucena.quake3log.data.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import me.ericklucena.quake3log.data.interfaces.Jsonable;
import me.ericklucena.quake3log.data.interfaces.Reportable;
import me.ericklucena.quake3log.util.JsonHelper;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Match implements Jsonable, Reportable {

	@JsonProperty
	private int id;
	private HashMap<String, Player> players;
	@JsonProperty
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

	@JsonProperty
	public List<Player> getPlayers() {
		return new ArrayList<Player>(players.values());
	}

	public Player getPlayer(String playerName) {
		return players.get(playerName);
	}

	public int getTotalKills() {
		int kills = worldKills;

		for (Player player : players.values()) {
			kills += player.getKills();
		}

		return kills;
	}

	public void addPlayer(String playerName) {
		addIfNotInMatch(playerName);
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
		players.get(playerName).suicide();
	}

	public void addEnviromentKill(String playerName) {
		addSuicide(playerName);
		worldKills++;
	}

	private void addIfNotInMatch(String playerName) {
		if (!players.containsKey(playerName)) {
			players.put(playerName, new Player(playerName));
		}
	}

	@Override
	public String toJson() throws JsonProcessingException {
		return JsonHelper.writeJSON(this);
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
}
