package core;

import gameState.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public float jumpSpeed;
    private GamePanel gp;
    
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (gp.gameState == GameState.START_MENU) {
            if (code == KeyEvent.VK_DOWN) {
                gp.playSoundEffect(0);
                gp.menu.commandNum++;
                if (gp.menu.commandNum > 3)
                    gp.menu.commandNum = 0;
            }
            if (code == KeyEvent.VK_UP) {
            	gp.playSoundEffect(0);
                gp.menu.commandNum--;
                if (gp.menu.commandNum < 0)
                    gp.menu.commandNum = 3;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.menu.commandNum == 0) {
                	gp.stopMusic();
                	gp.playSoundEffect(0);
                    gp.gameState = GameState.RUNNING_GAME;
                    gp.playMusic(7);
                    gp.startTimer();
                }
                if (gp.menu.commandNum == 1) {
                	gp.playSoundEffect(0);
                    gp.gameState = GameState.INFO_SCREEN;
                }
                
                if (gp.menu.commandNum == 2) {
                	gp.playSoundEffect(0);
                	gp.gameState = GameState.GAME_CONTROLS;
                	
                }
                if (gp.menu.commandNum == 3) {
                	gp.playSoundEffect(0);
                    System.exit(0);
                }
            }
        }

        if (gp.gameState == GameState.INFO_SCREEN) {
            if (code == KeyEvent.VK_RIGHT) {
            	gp.playSoundEffect(0);
                gp.info.c++;
                if (gp.info.c > 2)
                    gp.info.c = 0;
            }

            if (code == KeyEvent.VK_LEFT) {
            	gp.playSoundEffect(0);
                gp.info.c--;
                if (gp.info.c < 0)
                    gp.info.c = 2;
            }
            if (code == KeyEvent.VK_ENTER) {
            	gp.playSoundEffect(0);
                if (gp.info.c == 1) {
                    gp.gameState = GameState.START_MENU;
                    gp.info.c = 0;
                }

                if (gp.info.c == 2) {

                }
            }
        }
        
        if(gp.gameState == GameState.GAME_CONTROLS) {
        	gp.playSoundEffect(0);
        	if(code == KeyEvent.VK_ESCAPE) {
        		gp.gameState = GameState.START_MENU;
        	}
        }
        
        if(gp.getGameOver() == true || gp.getOutOfBounds() == true) {
        	gp.gameState = GameState.GAME_OVER;
        	if(code == KeyEvent.VK_LEFT) {
        		gp.playSoundEffect(0);
        		leftPressed = true;
        		gp.getGameOverObj().cmd--;
        		if(gp.getGameOverObj().cmd<1) {
        			gp.getGameOverObj().cmd = 1;
        		}
        	}
        	if(code==KeyEvent.VK_RIGHT) {
        		gp.playSoundEffect(0);
        		rightPressed = true;
        		gp.getGameOverObj().cmd++;
        		if(gp.getGameOverObj().cmd>2) {
        			gp.getGameOverObj().cmd = 2;
        		}
        	}
            if (gp.getGameOverObj().cmd == 1 && code == KeyEvent.VK_ENTER) {
            	gp.gameState = GameState.RUNNING_GAME;
            	gp.getGameOverObj().restart();
            	gp.startTimer();
            }
            if (gp.getGameOverObj().cmd == 2 && code == KeyEvent.VK_ENTER) {
                gp.setGame();
                gp.gameState = GameState.START_MENU;
            }
        }

        if (gp.gameState == GameState.RUNNING_GAME) {
            // Up-arrow or space-button is pressed
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE || e.getKeyChar() == 'w') {
                upPressed = true;
            }
            // left-arrow is pressed
            if (code == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {
                leftPressed = true;

            }
            // right-arrow is pressed
            if (code == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {
                rightPressed = true;
            }
            if((gp.getGameOver() == false || gp.getOutOfBounds() == false) && code == KeyEvent.VK_ESCAPE) {
            	gp.gameState = GameState.PAUSED_GAME;
            }
        }
        
        if (gp.gameState == GameState.PAUSED_GAME) {
        	gp.stopTimer();
        	if (code == KeyEvent.VK_DOWN) {
        		gp.playSoundEffect(0);
        		gp.pause.cmd++;
        		if (gp.pause.cmd > 3){
        			gp.pause.cmd = 3;
        		}
        	}
        	if (code == KeyEvent.VK_UP) {
        		gp.playSoundEffect(0);
        		gp.pause.cmd--;
        		if (gp.pause.cmd < 1) {
        			gp.pause.cmd =  1;
        		}
        	}
        	if(gp.pause.cmd == 1 && code == KeyEvent.VK_ENTER) {
        		gp.gameState = GameState.RUNNING_GAME;
        		gp.startTimer();
        	}
        	if(gp.pause.cmd == 2 && code == KeyEvent.VK_ENTER) {
        		gp.stopMusic();
        		gp.gameState = GameState.RUNNING_GAME;
        		gp.setGame();
        		gp.startTimer();
        	}
        	if(gp.pause.cmd == 3 && code == KeyEvent.VK_ENTER) {
        		gp.stopMusic();
        		gp.gameState = GameState.START_MENU;
        		gp.playMusic(8);
        		gp.setGame();
        	}
        }
    }

    // releasing the buttons
    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        // space-button or up-arrow is released 
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE || e.getKeyChar() == 'w') {
            upPressed = false;
        }
        
        // left arrow is released
        if (code == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {
            leftPressed = false;
            
        }
        //right-arrow is released
        if (code == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {
            rightPressed = false;
        }
         
        
    }

}
