/** This State is specifically for testing of game engine function
 * Input handling, sprite rendering, or music testing will be done here**/

package com.thecubecast.ReEngine.GameStates;

import com.thecubecast.ReEngine.Data.GameStateManager;
import com.thecubecast.ReEngine.Data.JukeBox;
import com.thecubecast.ReEngine.Data.Keys;
import com.thecubecast.ReEngine.Window.GamePanel;

import com.thecubecast.ReEngine.Data.Common;

import com.thecubecast.ReEngine.Graphics.Draw;

import java.awt.*;

public class TestState extends GameState {	
	
	private int TileSize = 40; //This is the size of each tile, aswell as how far the camera moves per "turn"
	
	private boolean MenuOpen = false;
	
	public TestState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		//JukeBox.load("/Music/bgmusic.wav", "introsound");
		//JukeBox.loop("introsound");
		//JukeBox.setVolume("introsound", -30.0f);
	}
	
	
	public void update() {
		
		if(Keys.isPressed(Keys.ESCAPE)) { 
			MenuOpen = !MenuOpen;
			Common.sleep(50);
		} 
		
		
		//IF STATEMENT THAT WILL BE PUT HERE WHEN INPUT HANDLING IS FIXED
		//IT WILL QUIT THE GAME
		
	}

	
	public void draw(Graphics2D bbg, int width, int height) {
		/**
		 * To use the camera
		 * Subtract the location of the sprite by the cameras position.
		 */
		
		//This sets up the FPS and important stuff
		//Clears the screen each frame, Avoiding Artifacts
		bbg.setColor(Color.green);
		bbg.fillRect(0, 0, width, height);
		
		//To Setup "Layers" just run code in order of back layer to front layer
		gsm.Render.DrawBackground(bbg, width, height);
		
		//The GUI would go here
		gsm.Render.GUIDeco(bbg, 0, 0, TileSize, TileSize); //Any overlays such as Health, gold, fuel, etc.
	}
}