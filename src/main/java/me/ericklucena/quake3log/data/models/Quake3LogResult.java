package me.ericklucena.quake3log.data.models;

public class Quake3LogResult {

	private Summary summary;
	private Ranking ranking;

	public Quake3LogResult(Summary summary, Ranking ranking) {
		this.summary = summary;
		this.ranking = ranking;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public Summary getSummary() {
		return summary;
	}
}
