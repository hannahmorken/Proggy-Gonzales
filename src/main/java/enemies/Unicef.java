package enemies;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import core.Panel;

import java.awt.Rectangle;

public class Unicef extends EntityEnemy{

    Panel gp;
	
    public Unicef(Panel gp) {
        super(gp);	
        this.gp = gp;
        direction = "down";
        speed = 1;
        enemySolid = new Rectangle(14 ,2,gp.tileSize-14, gp.tileSize-2);
        
        getHoboImage();
    }
    
	public void getHoboImage() {
	        
	        try {
	        	unicefRight = ImageIO.read(getClass().getResourceAsStream("/enemies/Unicef_left1.png"));
	        	unicefLeft = ImageIO.read(getClass().getResourceAsStream("/enemies/Unicef_left2.png"));

	            	  
	            
	            
	        }catch(IOException e) {
	            e.printStackTrace();
	        }
	}

	public void setAction() {
			
			
			
			movementCount ++;
			
			//the higher the movementcount, the longer between change of direction
			if(movementCount == 120) {
				
				Random random = new Random();
				int i = random.nextInt(100) + 1; //pick up a number from 1-100
				
				if(i <= 50) {
					direction = "enemyLeft";
				}
				if(i > 50) {
					direction = "enemyRight";
				}
				if(onGround = false) {
					direction = "down";
				}
			
				movementCount = 0;
			}
			
			
		}
	
	

}


