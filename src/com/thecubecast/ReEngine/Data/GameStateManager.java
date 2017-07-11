// The GameStateManager does exactly what its
// name says. It contains a list of GameStates.
// It decides which GameState to update() and
// draw() and handles switching between different
// GameStates.

package com.thecubecast.ReEngine.Data;

import java.awt.Graphics2D;

//import com.thecubecast.ReEngine.GameStates.GameOverState;
import com.thecubecast.ReEngine.GameStates.GameState;
import com.thecubecast.ReEngine.GameStates.IntroState;
//import com.thecubecast.ReEngine.GameStates.OptionsState;
//import com.thecubecast.ReEngine.GameStates.MenuState;
import com.thecubecast.ReEngine.GameStates.PlayState;
import com.thecubecast.ReEngine.Graphics.Draw;


public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	public Draw Render;
	
	public static final int NUM_STATES = 5;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	public static final int GAMEOVER = 3;
	public static final int OPTIONS = 4;
	
	public GameStateManager() {
		
		JukeBox.init();
		
		Render = new Draw();
		Render.Init();
		
		gameStates = new GameState[NUM_STATES];
		setState(INTRO); //THIS IS THE STATE WERE WE START WHEN THE GAME IS RUN
		
	}
	
	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if(i == INTRO) {
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		/**else if(i == MENU) {
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		}
		**/
		else if(i == PLAY) {
			gameStates[i] = new PlayState(this);
			gameStates[i].init();
		}
		/**
		else if(i == GAMEOVER) {
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		}
		else if(i == OPTIONS) {
			gameStates[i] = new OptionsState(this);
			gameStates[i].init();
		}
		**/
	}
	
	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	public void update() {
		if(gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}
	
	public void draw(Graphics2D g) {
		if(gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}
	
}
