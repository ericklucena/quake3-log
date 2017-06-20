package me.ericklucena.quake3log.data.models;

public class LogResult {

	private Summary summary;
	private Ranking ranking;

	public LogResult(Summary summary, Ranking ranking) {
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
