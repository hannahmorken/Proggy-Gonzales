package gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import core.GamePanel;


public class WinScreen2 extends GameScreen{
	
	GamePanel gp;
	Image image;
	
	public WinScreen2(GamePanel gp) {
		this.gp = gp;
		image = new ImageIcon(getClass().getResource("/graphics/ProggyEnding.gif")).getImage();
	}

	@Override
	public void draw(Graphics g2) {
		
		this.setGp(gp);
		this.setGraphics(g2);
		
		String s1;
		
		if (gp.getLang() == Languages.NORWEGIAN) {
			s1 = "ESC - Tilbake til hovedmeny";
		}
		else {
			s1 = "ESC - Back to main menu";
		}
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.drawImage(image, 250, 50, 600, 600, null);
		
		this.draw(30);
		g2.setColor(Color.WHITE);
		g2.drawString(s1, centerText(s1)-100, gp.tileSize*10);
	}
	
	
}
