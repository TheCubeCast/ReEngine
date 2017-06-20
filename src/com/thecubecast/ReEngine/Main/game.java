package com.thecubecast.ReEngine.Main;

import com.thecubecast.ReEngine.Data.Common;
import com.thecubecast.ReEngine.Data.JukeBox;
import com.thecubecast.ReEngine.Main.InputHandler; 
import com.thecubecast.ReEngine.Graphics.Draw;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Game extends JFrame {
	Draw Render;
	private int FPS = 120;
	private String Title = "ReEngine";
	private int Width = Common.GetMonitorSizeW();
	private int Height = Common.GetMonitorSizeH();

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
		
		
		setTitle(Title); 
        setSize(Width, Height); 
        setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true); 
        
        setSize(Width, Height); 
        
        backBuffer = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
        
        Render.Init();
        JukeBox.init();
        
        input = new InputHandler(this);
	}
	
	
	void update() {
		 
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) 
		{ 
			cameraX += TileSize; 
			Common.sleep(10);
		} 
		if (input.isKeyDown(KeyEvent.VK_LEFT)) 
		{ 
			cameraX -= TileSize; 
			Common.sleep(10);
		}
		if (input.isKeyDown(KeyEvent.VK_UP)) 
		{ 
			cameraY -= TileSize; 
			Common.sleep(10);
		} 
		if (input.isKeyDown(KeyEvent.VK_DOWN)) 
		{ 
			cameraY += TileSize; 
			Common.sleep(10);
		} 
		
		//IF STATEMENT THAT WILL BE PUT HERE WHEN INPUT HANDLING IS FIXED
		//IT WILL QUIT THE GAME
		
	}

	
	void draw() {
		
		/**
		 * To use the camera
		 * Subtract the location of the sprite by the cameras position.
		 */
		
		//This sets up the FPS and important stuff
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		//Clears the screen each frame, Avoiding Artifacts
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, Width, Height);
		
		//The bottom layer
		//Draw the background here
		Render.Background(bbg, Width, Height);
		
		//The Tiles are being drawn on this "Layer"
		//A function that reads the map file, then places each tile on the screen
		
		Render.DrawTiles(bbg, cameraX, cameraY, TileSize);
		
		//The "Player" and other entities or overlays must be drawn last. Think top layer 
		//Render.Player(bbg, )
		
		//The GUI would go here
		
		//This just swaps the buffers
		g.drawImage(backBuffer, 0, 0, this); 
	}
}