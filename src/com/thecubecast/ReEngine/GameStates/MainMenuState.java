// GameState that shows logo.

package com.thecubecast.ReEngine.GameStates;

import java.awt.Color;
import java.awt.Graphics2D;

import com.thecubecast.ReEngine.Window.GamePanel;
import com.thecubecast.ReEngine.Window.Main;
import com.thecubecast.ReEngine.Data.Keys;
import com.thecubecast.ReEngine.Data.GameStateManager;
import com.thecubecast.ReEngine.Data.JukeBox;


public class MainMenuState extends GameState {
	
	private int alpha;
	private int ticks;
	
	private final int FADE_IN = 20;
	private final int LENGTH = 20;
	private final int FADE_OUT = 20;
	
	public MainMenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		ticks = 0;
		//JukeBox.load("/Music/bgmusic.wav", "LogoSound");
		//JukeBox.play("LogoSound");
	}
	
	public void update() {
		handleInput();
		ticks++;
		if(ticks < FADE_IN) {
			alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
		if(ticks > FADE_IN + LENGTH) {
			alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
			if(alpha > 255) alpha = 255;
		}
		if(ticks > FADE_IN + LENGTH + FADE_OUT) {
			//JukeBox.stop("LogoSound");
			gsm.setState(GameStateManager.PLAY);
		}
	}
	
	public void draw(Graphics2D bbg, int width, int height) {
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, width, height);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {
			//JukeBox.stop("MenuNavigate");
			
			//Check what button the user is on, runs its function
		}
		
		if(Keys.isPressed(Keys.UP)) {
			//JukeBox.stop("MenuNavigate");
			
			//Moves the Chosen button UP
		}
		if(Keys.isPressed(Keys.DOWN)) {
			//JukeBox.stop("MenuNavigate");
			
			//Moves the Chosen button DOWN
		}
		if(Keys.isPressed(Keys.LEFT)) {
			//JukeBox.stop("MenuNavigate");

			//Moves the Chosen button LEFT
		}
		if(Keys.isPressed(Keys.RIGHT)) {
			//JukeBox.stop("MenuNavigate");
			
			//Moves the Chosen button RIGHT
		}
		
	}
}