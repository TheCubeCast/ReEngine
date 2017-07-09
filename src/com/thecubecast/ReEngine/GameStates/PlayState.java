package com.thecubecast.ReEngine.GameStates;

import com.thecubecast.ReEngine.Data.Common;
import com.thecubecast.ReEngine.Data.GameStateManager;
import com.thecubecast.ReEngine.Data.JukeBox;
import com.thecubecast.ReEngine.Data.Keys;
import com.thecubecast.ReEngine.Window.GamePanel;
import com.thecubecast.ReEngine.Graphics.Draw;

import java.awt.*;

public class PlayState extends GameState {	
	int HEIGHT = GamePanel.HEIGHT;
	int WIDTH = GamePanel.WIDTH;
	
	private int TileSize = 40; //This is the size of each tile, aswell as how far the camera moves per "turn"
	private int WorldSize = 200; //radius expanding from the origin point (0,0) of the world
	private int cameraX = 240;
	private int cameraY = -600;
	
	private int PlayerDirection = 4;
	private boolean MenuOpen = false;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		JukeBox.load("/Music/bgmusic.wav", "introsound");
		//JukeBox.loop("introsound");
		//JukeBox.setVolume("introsound", -30.0f);
	}
	
	
	public void update() {
		if(Keys.isPressed(Keys.ESCAPE)) { 
			MenuOpen = !MenuOpen;
			Common.sleep(50);
		} 
		
		if (!MenuOpen) {
			if(Keys.isDown(Keys.RIGHT)) { 
				if(Keys.isDown(Keys.LEFT)) {}
				else {
					if (PlayerDirection != 4) {
						PlayerDirection = 4;
						Common.sleep(5);	
					}
					else {
						cameraX += TileSize; 
						Common.sleep(10);		
					}
				}
			} 
			else if (Keys.isDown(Keys.LEFT)) 
			{ 
				if (Keys.isDown(Keys.RIGHT)) {}
				else {
					if (PlayerDirection != 2) {
						PlayerDirection = 2;
						Common.sleep(5);	
					}
					else {
						cameraX -= TileSize; 
						Common.sleep(10);		
					}
				}
			}
			else if (Keys.isDown(Keys.UP)) 
			{ 
				if (Keys.isDown(Keys.DOWN)) {}
				else {
					if (Keys.isDown(Keys.RIGHT)) {}
					else {
						if (PlayerDirection != 1) {
							PlayerDirection = 1;
							Common.sleep(5);	
						}
						else {
							cameraY -= TileSize;
							Common.sleep(10);		
						}
					}
				}
			} 
			else if (Keys.isDown(Keys.DOWN)) 
			{ 
				if (Keys.isDown(Keys.UP)) {}
				else {
					if (PlayerDirection != 3) {
						PlayerDirection = 3;
						Common.sleep(5);	
					}
					else {
						cameraY += TileSize; 
						Common.sleep(10);		
					}
				}
			} 
		}
		
		//IF STATEMENT THAT WILL BE PUT HERE WHEN INPUT HANDLING IS FIXED
		//IT WILL QUIT THE GAME
		
	}

	
	public void draw(Graphics2D bbg) {
		
		/**
		 * To use the camera
		 * Subtract the location of the sprite by the cameras position.
		 */
		
		//This sets up the FPS and important stuff
		//Clears the screen each frame, Avoiding Artifacts
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, WIDTH, HEIGHT);
		
		//The bottom layer
		//Draw the background here
		gsm.Render.Background(bbg, WIDTH, HEIGHT);
		
		//The Tiles are being drawn on this "Layer"
		//A function that reads the map file, then places each tile on the screen
		
		gsm.Render.DrawTiles(bbg, cameraX, cameraY, TileSize, WorldSize);
		
		//The "Player" and other entities or overlays must be drawn last. Think top layer 
		gsm.Render.Player(bbg, (WIDTH/2), ((HEIGHT/2) - (TileSize/2)), TileSize, TileSize, PlayerDirection);
		
		//The GUI would go here
		gsm.Render.GUI(bbg, 0, 0, TileSize, TileSize); //Any overlays such as Health, gold, fuel, etc.
		if(MenuOpen){gsm.Render.GUIMenu(bbg, WIDTH/2, HEIGHT/2, TileSize, TileSize);} // The Game MEnu
	}

}