package entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.GamePanel;
import core.KeyHandler;
import enemies.EntityEnemy;
import entity.Player;
import entity.Score;


public class PlayerItemsTest {

	GamePanel gp = new GamePanel("/maps/testingMap.txt");
	KeyHandler keyH = new KeyHandler(gp);
	Player p;
	EntityEnemy unicef[];
	int startPos;
	Score score;

	@BeforeEach
	void beforeEach() {
		this.p = new Player(gp, keyH);
		p.setDefaultValues();
	}
	
	@Test
	public void testSpeedBoost() {
		int normalSpeed = p.speed;
		//moves player to the right, and picks up speed boost
		for (int i = 0; i < 20; i++) {
			keyH.rightPressed = true;
			p.update();
		}
		
		int newSpeed = p.speed;
		assertTrue(newSpeed > normalSpeed);
		assertEquals(newSpeed, 7);
	}

	@Test
	public void testInvisible() {
		gp.collisionChecker.setCoins(100);
		int money = gp.collisionChecker.getCoins();
		
		for(int i = 0; i < 40; i++) {
			keyH.rightPressed = true;
			p.update();
		}
		int newMoney = gp.collisionChecker.getCoins(); // lagrer nye verdien til pengene
		
		assertEquals(newMoney, money + 10 - 15); // tar +10 -15 fordi player plukker opp 10kr, går så forbi en fiende, også plukker opp maske før neste fiende. Player skal altså bare miste 15kr fra første fiende
	}

	@Test
	public void testPlayerCanPickUp() {
		//TODO: lage når vi implementerer gjenstander
	}

	@Test
	public void testPlayerCanPickUpMoney() {
		score = new Score(gp);
		int money = gp.collisionChecker.getCoins();
		
		//moves player to the right, and picks up money
		for(int i = 0; i < 5; i++) {
			keyH.rightPressed = true;
			p.update();
		}
		
		
		int newScore = money + 10;

		assertTrue(gp.loader.numOfTiles[8][1] == 0);
		assertEquals(newScore, gp.collisionChecker.getCoins());
	}

	
	@Test
	public void playerLosesMoneyHomeless() {
		gp.collisionChecker.setCoins(100);
		int money = gp.collisionChecker.getCoins();
		
		for (int i = 0; i < 20; i++) {
			keyH.rightPressed = true;
			p.update();
		}
		
		int newScore = gp.collisionChecker.getCoins();

		assertTrue(newScore < money);
		assertEquals(newScore, money + 10 - 15);
	}
	
	@Test
	public void playerLosesMoneyUnicef() {
		gp.collisionChecker.setCoins(100);
		int money = gp.collisionChecker.getCoins();
		int newScore = 0;
		
		for (int i = 0; i < 20; i++) {
			if(gp.collisionChecker.takenMoney == true) {
				unicef[i].update();
				newScore = gp.collisionChecker.getCoins();
			}
		
		}

		assertTrue(newScore < money);
		
	}
}
