package entity.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import core.CollisionCheck;
import core.GamePanel;
import core.KeyHandler;

/**
 * Class for player 1
 * Inherits from PLayerEntity
 */
public class Player1 extends PlayerEntity {

    final KeyHandler keyH;
    String playerName;
    public final int playerX;
    public final int playerY;
    
    
    
    public Player1(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        this.collisionChecker = new CollisionCheck(this);
        playerX = 480;
        playerY = 515;
        worldX = 64;
        playerState = PlayerState.NORMAL;
        setDefaultValues();
        getImage();
        this.playerNum = 1;
    }

    @Override
    public void getImage() {
        if (playerState == PlayerState.NORMAL) {
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_default1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_default2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_left2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_right2.png"));
    
            }catch(IOException e) {
                e.printStackTrace();
            }

        } else if (playerState == PlayerState.INVISIBLE)  {
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_default1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_default2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_left2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_right2.png"));

            }catch(IOException e) {
                e.printStackTrace();
            }

        } else if (playerState == PlayerState.FASTER)  {
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_default1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_default2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_left2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_right2.png"));

            }catch(IOException e) {
                e.printStackTrace();
            }

        } else if (playerState == PlayerState.VOI)  {
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_default1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_default2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_default2.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_left2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_default1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_right2.png"));

            }catch(IOException e) {
                e.printStackTrace();
            }

        }
        
    }

    @Override
    public void update() {
        if(keyH.upPressed1 || keyH.downPressed || keyH.leftPressed1 || keyH.rightPressed1) {
				if(keyH.upPressed1) {
					previousDirection = direction;
					direction = "up";

	            }
	            else if(keyH.downPressed) {
	            	previousDirection = direction;
	                direction = "down";

	            }
	            else if(keyH.leftPressed1) {
	            	previousDirection = direction;
	                direction = "left";

	            }
	            else if(keyH.rightPressed1) {
	            	previousDirection = direction;
	                direction = "right";
	            }
                
                // Is the tile Proggy is located in solid? default setting is false
	            colliding = false;
                
                // updates to be true if Proggy collides with a solid tile
                collisionChecker.checkCollisionOnTile();

                // Checks playerState and sets the correct abilities to match the power-up
                PowerUp();

                // If collision is false, player moves. else: direction stops. 
                if (!colliding) {
                    switch(direction) {
                    case "up":
                    jumpP1();
                    moveWhileJumping();
                        break;
                    case "down":
                    fall();
                        break;
                    case "right":
                    worldX += speed;
                    jumpP1();		//jump instead of fall seems to give better results currently
                    
                        break;
                    case "left":
                    worldX -= speed; 
                    jumpP1();
                    
                        break;
                    }
                }
                updateSprite();
        }
    }

    /**
     * Jump method for player 1
     */
    public void jumpP1() {
        if(keyH.upPressed1 || (jumpStrength <= 0 && !onGround)) {
            super.jump();
        }
    }

    /**
     * Method to make it possible for player 1 to move left or right while in the air
     */
    private void moveWhileJumping () {
    	String originalDir = previousDirection;
    	int moveInAir = 0;
    	if(keyH.leftPressed1 || keyH.rightPressed1) {
    		
			if(keyH.leftPressed1) {
				originalDir = direction;
				direction = "left";
				moveInAir = -speed;
			}
			if(keyH.rightPressed1) {
				originalDir = direction;
				direction = "right";
				moveInAir = speed;
			}
			collisionChecker.checkCollisionOnTile();
			if(!colliding) {
				worldX += moveInAir;
			}
		}
    	direction = originalDir;
    }
    
    @Override
    public void draw(Graphics2D g2) {        
        BufferedImage image = choseSprite();
        g2.drawImage(image, playerX, playerY, gp.tileSize, gp.tileSize, null);
    }

    @Override
    public void setGravity(int gravity) {
    	this.gravity = gravity;
    }
        
    
}

