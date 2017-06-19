package com.thecubecast.ReEngine.Main;

import com.thecubecast.ReEngine.Data.JukeBox;
import com.thecubecast.ReEngine.Main.InputHandler; 

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Game extends JFrame {
	
	//Always set to 1 above the number of spites in file
	private BufferedImage[] Tiles = new BufferedImage[54];
	
	private int FPS = 120;
	private String Title = "ReEngine";
	private int Width = GetMonitorSizeW();
	private int Height = GetMonitorSizeH();

	InputHandler input;
	
	private BufferedImage backBuffer;
	private int TileSize = 40; //This is the size of each tile, aswell as how far the camera moves per "turn"
	private int cameraX;
	private int cameraY;

	public static void main(String[] args) {
		Game ReEngine = new Game();
		ReEngine.initialize();
		ReEngine.run();
		System.exit(0);
	}
	
	public void run() {
		boolean isRunning = true;
		
		JukeBox.load("/Music/bgmusic.wav", "introsound");
		JukeBox.loop("introsound");
		//JukeBox.setVolume("introsound", -30.0f);
		
		while(isRunning) {
			long time = System.currentTimeMillis();
			
			update();
			draw();
		
			//  delay looping so the FPS isn't crazy high
			//  delay for each frame  -   time it took for one frame 
	        time = (1000 / FPS) - (System.currentTimeMillis() - time); 

	        if (time > 0) 
            { 
                    try 
                    { 
                            Thread.sleep(time); 
                    } 
                    catch(Exception e){} 
            } 
		}
		setVisible(false); 
	}
	
	void initialize() {
		
		for(int i=0; i < Tiles.length; ++i){
			if (i >= 10) {
				try {
					print("Loaded images /Sprites/"+ Integer.toString(i) +".png");
					Tiles[i] = ImageIO.read(getClass().getResourceAsStream("/Sprites/"+ Integer.toString(i) +".png"));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					print("Loaded images /Sprites/0"+ Integer.toString(i) +".png");
					Tiles[i] = ImageIO.read(getClass().getResourceAsStream("/Sprites/0"+ Integer.toString(i) +".png"));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
       }
		
		
		setTitle(Title); 
        setSize(Width, Height); 
        setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true); 
        
        setSize(Width, Height); 
        
        backBuffer = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
        
        JukeBox.init();
        
        input = new InputHandler(this);
	}
	
	
	void update() {
		 
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) 
		{ 
			cameraX += TileSize; 
			sleep(10);
		} 
		if (input.isKeyDown(KeyEvent.VK_LEFT)) 
		{ 
			cameraX -= TileSize; 
			sleep(10);
		}
		if (input.isKeyDown(KeyEvent.VK_UP)) 
		{ 
			cameraY -= TileSize; 
			sleep(10);
		} 
		if (input.isKeyDown(KeyEvent.VK_DOWN)) 
		{ 
			cameraY += TileSize; 
			sleep(10);
		} 
		
		//IF STATEMENT THAT WILL BE PUT HERE WHEN INPUT HANDLING IS FIXED
		//IT WILL QUIT THE GAME
		
	}

	
	void draw() {
		//This sets up the FPS and important stuff
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		//Clears the screen each frame, Avoiding Artifacts
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, Width, Height);
		
		//The bottom layer
		//Draw the background here
		
		//The Tiles are being drawn on this "Layer"
		bbg.drawImage(Tiles[00], 40 - cameraX, 40 - cameraY, TileSize, TileSize, null);
		
		//The "Player" and other entities or overlays must be drawn last. Think top layer 
		bbg.drawImage(Tiles[53], Width / 2, Height / 2, TileSize, TileSize, null);
		
		//The GUI would go here
		
		//This just swaps the buffers
		g.drawImage(backBuffer, 0, 0, this); 
	}
	
	
	
	//Everything Below is just helpful functions
	
	int GetMonitorSizeW() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getWidth();
	}
	int GetMonitorSizeH() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getHeight();
	}
	
	void print(String Output) {
		System.out.println(Output);
	}
	
	void sleep(int Time) {
		 try {
			 // thread to sleep for 1000 milliseconds
	         Thread.sleep(Time);
	     } catch (Exception e) {
	    	 System.out.println(e);
	     }
	}
}