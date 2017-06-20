package me.ericklucena.quake3log.data.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import me.ericklucena.quake3log.data.interfaces.Jsonable;
import me.ericklucena.quake3log.data.interfaces.Reportable;
import me.ericklucena.quake3log.util.JsonHelper;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Player implements Jsonable, Reportable {

	@JsonProperty
	private String name;
	@JsonProperty
	private int kills;
	@JsonProperty
	private int deaths;

	public Player(String name) {
		this.name = name;
		this.kills = 0;
		this.deaths = 0;
	}

	public String getName() {
		return name;
	}

	public int getKills() {
		return kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void kill() {
		this.kills++;
	}

	public void death() {
		this.deaths++;
	}

	public void suicide() {
		this.kills--;
	}

	public void merge(Player player) {
		this.kills += player.kills;
		this.deaths += player.deaths;
	}

	@Override
	public String toJson() throws JsonProcessingException {
		return JsonHelper.writeJSON(this);
	}

	@Override
	public String toReport() {
		return String.format("%s K/D: %d / %d \n", name, kills, deaths);
	}
}
