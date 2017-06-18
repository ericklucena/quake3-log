package me.ericklucena.quake3log.data.models;

import me.ericklucena.quake3log.data.interfaces.Jsonable;
import me.ericklucena.quake3log.data.interfaces.Reportable;

public class Player implements Jsonable, Reportable {
	
	private String name;
	private int kills;
	private int deaths;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public String toJson() {
		return null;
	}
	public String toReport() {
		return null;
	}
}
