package me.ericklucena.quake3log.data.models;

import me.ericklucena.quake3log.data.interfaces.Jsonable;
import me.ericklucena.quake3log.data.interfaces.Reportable;

public class Player implements Jsonable, Reportable {
	
	private String name;
	private int kills;
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
		if (kills > 0) {
			this.kills--;
		}
	}
	
	public void merge(Player player) {
		this.kills += player.kills;
		this.deaths += player.deaths;
	}
	
	public String toJson() {
		return null;
	}
	public String toReport() {
		return String.format("%s K/D: %d / %d \n", name, kills, deaths);
	}
}
