package me.ericklucena.quake3log.business.parser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import me.ericklucena.quake3log.business.reader.QuakeLogReader;
import me.ericklucena.quake3log.data.models.Match;

public class QuakeLogParserTest {

	@Test
	public void QuakeLogParser_CommonGame_OneMatchFourPlayers() throws IOException {
		int expectedGames = 1;
		int expectedPlayers = 4;

		QuakeLogParser parser = new QuakeLogParser(getCommonGameReader());
		parser.parse();
		Match match = parser.getResult().getSummary().get(0);

		assertEquals(expectedGames, parser.getResult().getSummary().size());
		assertEquals(expectedPlayers, match.getPlayers().size());
	}
	
	@Test
	public void QuakeLogParser_CommonGame_RankingHasFourPlayers() throws IOException {
		int expectedPlayers = 4;

		QuakeLogParser parser = new QuakeLogParser(getCommonGameReader());
		parser.parse();

		assertEquals(expectedPlayers, parser.getResult().getRanking().getPlayers().size());
	}
	
	@Test
	public void QuakeLogParser_EmptyGame_OneMatchWithoutPlayers() throws IOException {
		int expectedGames = 1;
		int expectedPlayers = 0;

		QuakeLogParser parser = new QuakeLogParser(getEmptyGameReader());
		parser.parse();
		Match match = parser.getResult().getSummary().get(0);

		assertEquals(expectedGames, parser.getResult().getSummary().size());
		assertEquals(expectedPlayers, match.getPlayers().size());
	}
	
	@Test
	public void QuakeLogParser_GamePlayerConnectedNoAction_OneMatchOnePlayer() throws IOException {
		int expectedGames = 1;
		int expectedPlayers = 1;

		QuakeLogParser parser = new QuakeLogParser(getGamePlayerConnectedNoAction());
		parser.parse();
		Match match = parser.getResult().getSummary().get(0);

		assertEquals(expectedGames, parser.getResult().getSummary().size());
		assertEquals(expectedPlayers, match.getPlayers().size());
	}

	@Test
	public void QuakeLogParser_EmptyGameWithoutShutdown_OneMatchWithoutPlayers() throws IOException {
		int expectedGames = 1;
		int expectedPlayers = 0;

		QuakeLogParser parser = new QuakeLogParser(getGameWithoutShutdownReader());
		parser.parse();
		Match match = parser.getResult().getSummary().get(0);

		assertEquals(expectedGames, parser.getResult().getSummary().size());
		assertEquals(expectedPlayers, match.getPlayers().size());
	}

	@Test
	public void QuakeLogParser_TwoGamesWithoutShutdownOnFirst_TwoMatchs() throws IOException {
		int expectedGames = 2;

		QuakeLogParser parser = new QuakeLogParser(getTwoGamesWithoutShutdownOnFirst());
		parser.parse();

		assertEquals(expectedGames, parser.getResult().getSummary().size());
	}
	
	@Test
	public void QuakeLogParser_TwoGamesWithoutPlayers_EmptyRanking() throws IOException {
		int expectedPlayers = 0;

		QuakeLogParser parser = new QuakeLogParser(getTwoGamesWithoutShutdownOnFirst());
		parser.parse();

		assertEquals(expectedPlayers, parser.getResult().getRanking().getPlayers().size());
	}

	// Mocks
	private QuakeLogReader getEmptyGameReader() throws IOException {
		QuakeLogReader readerMock = Mockito.mock(QuakeLogReader.class);

		Mockito.when(readerMock.nextLine()).thenReturn(
				"0:00 ------------------------------------------------------------",
				"  0:00 InitGame: \\sv_floodProtect", " 15:00 Exit: Timelimit hit.", " 20:34 ClientConnect: 2",
				" 20:37 ShutdownGame:", " 20:37 ------------------------------------------------------------", null);

		return readerMock;
	}
	
	private QuakeLogReader getCommonGameReader() throws IOException {
		QuakeLogReader readerMock = Mockito.mock(QuakeLogReader.class);

		Mockito.when(readerMock.nextLine()).thenReturn(
				"0:00 InitGame: \\sv_floodProtect",
				"1:08 Kill: 3 2 6: Isgalamido killed Mocinha by MOD_ROCKET",
				"1:26 Kill: 1022 4 22: <world> killed Zeh by MOD_TRIGGER_HURT",
				"1:32 Kill: 1022 4 22: <world> killed Zeh by MOD_TRIGGER_HURT",
				"1:41 Kill: 1022 2 19: <world> killed Dono da Bola by MOD_FALLING",
				"1:47 ShutdownGame:",
				"1:47 ------------------------------------------------------------", null);

		return readerMock;
	}

	private QuakeLogReader getGameWithoutShutdownReader() throws IOException {
		QuakeLogReader readerMock = Mockito.mock(QuakeLogReader.class);

		Mockito.when(readerMock.nextLine()).thenReturn(
				"0:00 ------------------------------------------------------------",
				"  0:00 InitGame: \\sv_floodProtect", " 15:00 Exit: Timelimit hit.", " 20:34 ClientConnect: 2",
				" 20:37 ------------------------------------------------------------", null);

		return readerMock;
	}

	private QuakeLogReader getTwoGamesWithoutShutdownOnFirst() throws IOException {
		QuakeLogReader readerMock = Mockito.mock(QuakeLogReader.class);

		Mockito.when(readerMock.nextLine()).thenReturn(
				"0:00 ------------------------------------------------------------",
				"  0:00 InitGame: \\sv_floodProtect", " 15:00 Exit: Timelimit hit.", " 20:34 ClientConnect: 2",
				" 20:35 ShutdownGame:",
				" 20:37 ------------------------------------------------------------",
				"0:00 ------------------------------------------------------------",
				"  0:00 InitGame: \\sv_floodProtect", " 15:00 Exit: Timelimit hit.", " 20:34 ClientConnect: 2",
				" 20:37 ShutdownGame:", " 20:37 ------------------------------------------------------------", null);

		return readerMock;
	}
	
	private QuakeLogReader getGamePlayerConnectedNoAction() throws IOException {
		QuakeLogReader readerMock = Mockito.mock(QuakeLogReader.class);

		Mockito.when(readerMock.nextLine()).thenReturn(
				"  0:00 InitGame: \\sv_floodProtect", " 15:00 Exit: Timelimit hit.", " 20:34 ClientConnect: 2",
				" 20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\",
				" 20:37 ShutdownGame:", " 20:37 ------------------------------------------------------------", null);

		return readerMock;
	}

}
