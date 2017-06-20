package me.ericklucena.quake3log.data.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private Player player;
	
	@Before
	public void createPlayer() {
		player = new Player("Tester");
	}
	
	@Test
	public void Player_Kill_KIllsIncrements() {
		
		int expected = 1;

		player.kill();
		
		assertEquals(expected, player.getKills());
	}
	
	@Test
	public void Player_Suicide_KillsDecrements() {
		
		int expected = 1;
		
		player.kill();
		player.kill();
		player.suicide();
		
		assertEquals(expected, player.getKills());
	}
	
	@Test
	public void Player_SuicideWithoutAnyKills_NegativeKills() {
		
		int expected = -1;
		
		player.kill();
		player.suicide();
		player.suicide();
		
		assertEquals(expected, player.getKills());
	}

	@Test
	public void Player_Death_Success() {
		
		int expected = 1;
		
		player.death();
		
		assertEquals(expected, player.getDeaths());
	}

}
