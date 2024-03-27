package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = -8558398916119637570L;
	
	// SCREEN SETTINGS	
	final int originalTileSize = 16; //16x16 tiles
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile size
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// FPS
	int FPS = 60;
	
	// SYSTEM
	KeyHandler keyH = new KeyHandler();
	Music music = new Music();
	SoundEffect sound = new SoundEffect();
	public TileManager tileM = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	// ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));;
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
		playMusic(0);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	// Good Game Loop for Movement
//	public void run() {
//		while (gameThread != null) {
//			// System.out.println("The game loop is running.");
//			// long currentTime = System.nanoTime(); // gets current time in nanoseconds
//			
//			double drawInterval = 1000000000 / FPS; // divides 1 second by FPS (60) = .01666 secs
//			double nextDrawTime = System.nanoTime() + drawInterval; // sets the interval between frame change
//			
//			// UPDATE information such as character positions
//			update();
//			// DRAW the screen with the updated information
//			repaint();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long) remainingTime);
//				nextDrawTime += drawInterval;
//			} catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	// Delta Run Loop 
	public void run() {
		double drawInterval = 1000000000 / FPS; // .01666 secs
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		while(gameThread != null) { // 
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}	
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// TILE
		tileM.draw(g2);
		
		// OBJECT
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// PLAYER
		player.draw(g2);
		
		// UI
		ui.draw(g2);
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
}
