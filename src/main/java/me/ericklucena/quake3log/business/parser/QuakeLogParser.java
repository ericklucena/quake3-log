package me.ericklucena.quake3log.business.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.ericklucena.quake3log.business.reader.QuakeLogReader;
import me.ericklucena.quake3log.data.models.Match;
import me.ericklucena.quake3log.data.models.Ranking;

public class QuakeLogParser {

	private static final Pattern INIT_GAME_PATTERN = Pattern.compile("[0-9]+:[0-9]+\\s*\\bInitGame\\b:.*");
	private static final Pattern SHUTDOWN_GAME_PATTERN = Pattern.compile("[0-9]+:[0-9]+\\s*\\bShutdownGame\\b:.*");
	private static final Pattern CONNECT_PATTERN = Pattern.compile("[0-9]+:[0-9]+\\s*\\bClientUserinfoChanged\\b:\\s*[0-9\\s]\\s+n\\\\([a-zA-Z\\s]+)");
	private static final Pattern KILL_PATTERN = Pattern
			.compile(" (<?[a-zA-Z\\s]+>?) killed ([a-zA-Z\\s]+) by ([a-zA-Z_]+)");

	private static final int CONNECTION_GROUP = 1;
	private static final int KILLER_GROUP = 1;
	private static final int TARGET_GROUP = 2;

	private static final String WORLD_NAME = "<world>";

	private QuakeLogReader reader;
	private List<Match> matches;
	private Match currentMatch;
	private Ranking ranking;
	private int matchCounter;

	public QuakeLogParser(QuakeLogReader reader) {
		this.reader = reader;
		matchCounter = 0;
		matches = new ArrayList<Match>();
		ranking = new Ranking();
	}

	public void parse() throws IOException {

		String line;

		while ((line = reader.nextLine()) != null) {
			Matcher gameInit = INIT_GAME_PATTERN.matcher(line);
			Matcher gameShutdown = SHUTDOWN_GAME_PATTERN.matcher(line);
			Matcher connect = CONNECT_PATTERN.matcher(line);
			Matcher kill = KILL_PATTERN.matcher(line);

			if (gameInit.find()) {
				onGameStart();
			} else if (gameShutdown.find()) {
				onGameShutdown();
			}else if (connect.find()) {
				onConnection(connect.group(CONNECTION_GROUP));
			} else if (kill.find()) {
				onKill(kill.group(KILLER_GROUP), kill.group(TARGET_GROUP));
			}
		}

		// If log ends without shutting down the last game
		onGameShutdown();
	}

	private void onGameStart() {
		if (currentMatch != null) {
			onGameShutdown();
		}
		currentMatch = new Match(matchCounter++);
	}

	private void onGameShutdown() {
		if (currentMatch != null) {
			matches.add(currentMatch);
			ranking.addMatchStats(currentMatch);
			currentMatch = null;
		}
	}
	
	private void onConnection(String playerName) {
		currentMatch.addPlayer(playerName);
	}

	private void onKill(String killer, String target) {
		if (killer.equals(WORLD_NAME)) {
			currentMatch.addEnviromentKill(target);
		} else if (killer.equals(target)) {
			currentMatch.addSuicide(killer);
		} else {
			currentMatch.addKill(killer);
			currentMatch.addDeath(target);
		}
	}

	public List<Match> getMatches() {
		return matches;
	}

	public Ranking getRanking() {
		return ranking;
	}

}
