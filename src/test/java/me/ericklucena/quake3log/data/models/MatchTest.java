package me.ericklucena.quake3log.data.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MatchTest {

	private Match match;
	private Player player1;
	private Player player2;
	
	@Before
	public void createMatchAndPlayers() {
		match = new Match(0);
		player1 = new Player("Tester 1");
		player2 = new Player("Tester 2");
	}
	
	@Test
	public void Match_NewPlayerAction_PlayerAdded() {
		int expectedSize = 1;
		int expectedKills = 1;
		
		match.addKill(player1.getName());
		
		assertEquals(expectedSize, match.getPlayers().size());
		assertEquals(expectedKills, match.getPlayer(player1.getName()).getKills());
	}
	
	@Test
	public void Match_NewPlayersAction_MultiplePlayersAdded() {
		int expectedSize = 2;
		
		match.addKill(player1.getName());
		match.addDeath(player2.getName());
		
		assertEquals(expectedSize, match.getPlayers().size());
	}
	
	@Test
	public void Match_ExistingPlayerAction_PlayerModified() {
		int expectedSize = 1;
		int expectedDeaths = 2;

		match.addDeath(player1.getName());
		match.addDeath(player1.getName());
		
		assertEquals(expectedSize, match.getPlayers().size());
		assertEquals(expectedDeaths, match.getPlayers().get(0).getDeaths());
	}

}
