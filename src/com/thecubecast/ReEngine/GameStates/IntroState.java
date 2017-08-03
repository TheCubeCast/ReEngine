// GameState that shows logo.

package com.thecubecast.ReEngine.GameStates;

import java.awt.Color;
import java.awt.Graphics2D;

import com.thecubecast.ReEngine.Data.Common;

import com.thecubecast.ReEngine.Data.Keys;
import com.thecubecast.ReEngine.Data.GameStateManager;
import com.thecubecast.ReEngine.Data.JukeBox;


public class IntroState extends GameState {
	
	//int HEIGHT = gsm.Window.WindowH;
	//int WIDTH = gsm.Window.WindowH;
	
	private int alpha;
	private int ticks;
	
	private final int FADE_IN = 20;
	private final int LENGTH = 20;
	private final int FADE_OUT = 20;
	
	public IntroState(GameStateManager gsm) {
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
			gsm.setState(GameStateManager.MENU);
		}
	}
	
	public void draw(Graphics2D g, int width, int height) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.drawImage(gsm.Render.Images[00], width/3, height/2, 1481/2, 341/2, null);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, width, height);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {
			//JukeBox.stop("LogoSound");
			gsm.setState(GameStateManager.MENU);
		}
	}
}