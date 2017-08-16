// The GameStateManager does exactly what its
// name says. It contains a list of GameStates.
// It decides which GameState to update() and
// draw() and handles switching between different
// GameStates.

package com.thecubecast.ReEngine.Data;

import java.awt.Graphics2D;
import java.awt.MouseInfo;

//import com.thecubecast.ReEngine.GameStates.GameOverState;
import com.thecubecast.ReEngine.GameStates.GameState;
import com.thecubecast.ReEngine.GameStates.IntroState;
import com.thecubecast.ReEngine.GameStates.MainMenuState;
import com.thecubecast.ReEngine.GameStates.TestState;
//import com.thecubecast.ReEngine.GameStates.OptionsState;
import com.thecubecast.ReEngine.GameStates.PlayState;
import com.thecubecast.ReEngine.Graphics.Draw;

import com.thecubecast.ReEngine.Data.Common;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	
	
	//Public render function object
	public Draw Render;
	
	//MousePos
	public int MouseX;
	public int MouseY;
	public boolean MouseDrag;
	
	//FrameTics
	public int Tics;
	
	public static final int NUM_STATES = 6;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	public static final int GAMEOVER = 3;
	public static final int OPTIONS = 4;
	public static final int TEST = 5;
	
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
			Common.print("Loaded state Intro");
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		else if(i == MENU) {
			Common.print("Loaded state MENU");
			gameStates[i] = new MainMenuState(this);
			gameStates[i].init();
		}
		else if(i == PLAY) {
			Common.print("Loaded state PLAY");
			gameStates[i] = new PlayState(this);
			gameStates[i].init();
		}
		/**
		else if(i == GAMEOVER) {
			Common.print("Loaded state GameOver");
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		}
		else if(i == OPTIONS) {
			Common.print("Loaded state Options");
			gameStates[i] = new OptionsState(this);
			gameStates[i].init();
		}
		**/
		else if(i == TEST) {
			Common.print("Loaded state Test");
			gameStates[i] = new TestState(this);
			gameStates[i].init();
		}
	}
	
	public void unloadState(int i) {
		Common.print("Unloaded state " + i);
		gameStates[i] = null;
	}
	
	public void update(int MousX, int MousY, boolean Draging, int TicZ) {
		MouseX = MousX;
		MouseY = MousY;
		MouseDrag = Draging;
		Tics = TicZ;
		if(gameStates[currentState] != null) {
			
			gameStates[currentState].update();
		}
	}
	
	public void draw(Graphics2D g, int W, int H) {
		if(gameStates[currentState] != null) {
			gameStates[currentState].draw(g, H, W);
		}
	}
	
}
