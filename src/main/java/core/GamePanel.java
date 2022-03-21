package core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.InputStream;

import javax.swing.JPanel;

import entity.Enemy;
import entity.Player;
import entity.Score;
import tile.TileLoader;
import timer.TimerDisplay;

public class GamePanel extends JPanel implements Runnable{

    // Screen settings
    final int originalTileSize = 32; // 32x32 tiles
    final int scale = 2;
    
    public final int tileSize = originalTileSize * scale; // 64
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;     // 1024 px
    public final int screenHeight = tileSize * maxScreenRow;    // 768 px
    
    //World settings
    public final int maxWorldCol = 16;
    public final int maxWorldRow = 12;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //Map
    public final InputStream is = getClass().getResourceAsStream("/maps/RealMap.txt");

    // FPS
    int FPS = 60;

    //CollisionCheck 
    public CollisionCheck collisionChecker = new CollisionCheck(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    //public Background bg = new Background(this, keyH);
    public TileLoader loader = new TileLoader(this, is);
    
    //Timer
    TimerDisplay timerDisplay = new TimerDisplay(this);
    Score score = new Score(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        //this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {  
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        //start the timer
        timerDisplay.startTime();
        
        while(gameThread != null) {
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1) {
                // 1: oppdaterer informasjon, som spillerens posisjon
                update();
                // 2: tegner skjermen på nytt med oppdatert informasjon
                repaint();
                fall();
                repaint();
                jump();
                repaint();

                delta--;
                drawCount++;
            }
            
            //display FPS in console
            if(timer >= 1000000000) {
//                System.out.println("FPS:"+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    

	public void update() {
       // bg.update();
        score.showScore();
        player.update();

        timerDisplay.update();
        
    }
    
    public void jump() {
    	player.jump();
    }
    public void fall() {
    	player.fall();
    }
    
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;

        //bg.draw(g2);
        loader.draw(g2, player.worldX);
        player.draw(g2);
        timerDisplay.draw(g2);
        score.draw(g2);
        
        g2.dispose();
    }
    
}
