package me.ericklucena.quake3log.data.models;

import java.util.ArrayList;
import java.util.Comparator;
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
public class Ranking implements Reportable, Jsonable {

	private HashMap<String, Player> players;

	public Ranking() {
		players = new HashMap<String, Player>();
	}

	public void addMatchStats(Match match) {
		for (Player player : match.getPlayers()) {
			addIfNotInRanking(player.getName());
			players.get(player.getName()).merge(player);
		}
	}

	private void addIfNotInRanking(String playerName) {
		if (!players.containsKey(playerName)) {
			players.put(playerName, new Player(playerName));
		}
	}

	@JsonProperty
	public List<Player> getPlayers() {
		List<Player> sortedPlayers = new ArrayList<Player>(players.values());
		sortedPlayers.sort(new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return o2.getKills() - o1.getKills();
			}
		});

		return sortedPlayers;
	}

	@Override
	public String toJson() throws JsonProcessingException {
		return JsonHelper.writeJSON(this);
	}

	@Override
	public String toReport() {
		String report = "Ranking\n";

		for (Player player : getPlayers()) {
			report += String.format("\t %s", player.toReport());
		}

		return report;
	}
}
