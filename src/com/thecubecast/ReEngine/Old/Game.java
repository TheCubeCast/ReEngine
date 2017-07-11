package com.thecubecast.ReEngine.Old;

import com.thecubecast.ReEngine.Data.Common;
import com.thecubecast.ReEngine.Data.JukeBox;
import com.thecubecast.ReEngine.Old.InputHandler; 
import com.thecubecast.ReEngine.Graphics.Draw;

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
	private int WorldSize = 200; //radius expanding from the origin point (0,0) of the world
	private int cameraX;
	private int cameraY;
	
	private int PlayerDirection = 1;
	private boolean MenuOpen = false;
	
	public static void main(String[] args) {
		Game ReEngine = new Game();
		ReEngine.initialize();
		ReEngine.run();
		System.exit(0);
	}
	
	public void run() {
		boolean isRunning = true;
		
		JukeBox.load("/Music/bgmusic.wav", "introsound");
		//JukeBox.loop("introsound");
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
		
		Render = new Draw();
		
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
		if (input.isKeyDown(KeyEvent.VK_ESCAPE)) { 
			MenuOpen = !MenuOpen;
		} 
		
		if (!MenuOpen) {
			if (input.isKeyDown(KeyEvent.VK_RIGHT)) 
			{ 
				if (input.isKeyDown(KeyEvent.VK_LEFT)) {}
				else {
					cameraX += TileSize; 
					PlayerDirection = 4;
					Common.sleep(10);	
				}
			} 
			else if (input.isKeyDown(KeyEvent.VK_LEFT)) 
			{ 
				if (input.isKeyDown(KeyEvent.VK_RIGHT)) {}
				else {
					cameraX -= TileSize; 
					PlayerDirection = 2;
					Common.sleep(10);	
				}
			}
			else if (input.isKeyDown(KeyEvent.VK_UP)) 
			{ 
				if (input.isKeyDown(KeyEvent.VK_DOWN)) {}
				else {
					cameraY -= TileSize;
					PlayerDirection = 1;
					Common.sleep(10);
				}
			} 
			else if (input.isKeyDown(KeyEvent.VK_DOWN)) 
			{ 
				if (input.isKeyDown(KeyEvent.VK_UP)) {}
				else {
					cameraY += TileSize; 
					PlayerDirection = 3;
					Common.sleep(10);
				}
			} 
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
		Render.DrawBackground(bbg, Width, Height);
		
		//The Tiles are being drawn on this "Layer"
		//A function that reads the map file, then places each tile on the screen
		
		Render.DrawTiles(bbg, cameraX, cameraY, TileSize, WorldSize);
		
		//The "Player" and other entities or overlays must be drawn last. Think top layer 
		Render.Player(bbg, (Width/2), ((Height/2) - (TileSize/2)), TileSize, TileSize, PlayerDirection);
		
		//The GUI would go here
		Render.GUI(bbg, 0, 0, TileSize, TileSize); //Any overlays such as Health, gold, fuel, etc.
		if(MenuOpen){Render.GUIMenu(bbg, Width/2, Height/2, TileSize, TileSize);} // The Game MEnu
		//This just swaps the buffers
		g.drawImage(backBuffer, 0, 0, this); 
	}
}