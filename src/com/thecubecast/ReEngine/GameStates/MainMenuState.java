// GameState that shows logo.

package com.thecubecast.ReEngine.GameStates;

import java.awt.Color;
import java.awt.Graphics2D;

import com.thecubecast.ReEngine.Window.GamePanel;
import com.thecubecast.ReEngine.Window.Main;
import com.thecubecast.ReEngine.Data.Keys;
import com.thecubecast.ReEngine.Data.Common;
import com.thecubecast.ReEngine.Data.GameStateManager;
import com.thecubecast.ReEngine.Data.JukeBox;


public class MainMenuState extends GameState {
	
	private int TileSize = 48;
	
	private int FadeInTicks = 0;

	//The Menu states
	private int currentState;
	
	public static final int Main = 0;
	public static final int Single = 1;
	public static final int Options = 2;
	public static final int GAMEOVER = 3;
	public static final int OPTIONS = 4;
	public static final int TEST = 5;
	
	//The buttons state 0
	int[] button01 = null;
	int[] button02 = null;
	int[] button03 = null;
	int[] button04 = null;
	
	//The buttons state 1
	int[] button11 = null;
	int[] button12 = null;
	int[] button13 = null;
	int[] button14 = null;
	int[] checkbox15 = null;
	boolean checkbox15Value = false;
	
	//The buttons state 2
	int[] button21 = null;
	int[] Slider22 = null;
	float Slider22Value = 0.0f;
	int[] button24 = null;
	
	public MainMenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		JukeBox.load("/Music/The-8-Bit-Digger.wav", "MenuTheme");
		JukeBox.loop("MenuTheme");
		//JukeBox.setVolume("MenuTheme", 0.08f);
		
	}
	
	public void update() {
		handleInput();
		if (FadeInTicks < 100) {
			FadeInTicks++;
		}
		if ((100*Slider22Value) < 80) {
			JukeBox.setVolume("MenuTheme", -(100*Slider22Value));
		}
		
	}
	
	public void draw(Graphics2D bbg, int width, int height) {
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, width, height);
		
		//gsm.Render.DrawBackground(bbg, width, height);
		bbg.drawImage(gsm.Render.Images[03], 0, 0, width, height, null);
		
		//Each if statement is another part of the menu
		if (currentState == 0) { 
			button01 = gsm.Render.GUIButton(bbg, width/2, (height/20)*9, 5, TileSize, TileSize, true, "Singleplayer");
			button02 = gsm.Render.GUIButton(bbg, width/2, (height/20)*10, 5, TileSize, TileSize, true, "");
			button03 = gsm.Render.GUIButton(bbg, width/2, (height/20)*11, 5, TileSize, TileSize, true, "Options");
			button04 = gsm.Render.GUIButton(bbg, width/2, (height/20)*12, 5, TileSize, TileSize, true, "Quit");
		}
		if (currentState == 1) { 
			button11 = gsm.Render.GUIButton(bbg, width/2, (height/20)*9, 5, TileSize, TileSize, true, "Save 1");
			button12 = gsm.Render.GUIButton(bbg, width/2, (height/20)*10, 5, TileSize, TileSize, true, "Save 2");
			button13 = gsm.Render.GUIButton(bbg, width/2, (height/20)*11, 5, TileSize, TileSize, true, "Save 3");
			button14 = gsm.Render.GUIButton(bbg, width/2, (height/20)*12, 5, TileSize, TileSize, true, "Back");
		}
		
		if (currentState == 2) { 
			button21 = gsm.Render.GUIButton(bbg, width/2, (height/20)*9, 5, TileSize, TileSize, true, "Setting1");
			Color myColor = Color.decode("#fffff");
			bbg.setColor(myColor);
			//bbg.fillRect((width/2)-250, (height/20)*10, -5*(Volume), TileSize/4);
			bbg.drawString("" + (100*Slider22Value), (width/2), (height/20)*10);
			bbg.setColor(Color.WHITE);
			
			Slider22 = gsm.Render.GUISlider(bbg, (width/16)*7, (height/20)*11, 5, TileSize, TileSize, false, Slider22Value);
			//button22 = gsm.Render.GUIButton(bbg, (width/16)*7, (height/20)*11, 2, TileSize, TileSize, true, "Volume -");
			//button23 = gsm.Render.GUIButton(bbg, (width/16)*9, (height/20)*11, 2, TileSize, TileSize, true, "Volume +");
			button24 = gsm.Render.GUIButton(bbg, width/2, (height/20)*12, 5, TileSize, TileSize, true, "Back");
			checkbox15 = gsm.Render.GUICheckBox(bbg, width/2, (height/20)*5, checkbox15Value, TileSize, TileSize);
		}
	}
	
	public void handleInput() {
				
		if(gsm.MouseClick[0] == 1 && currentState == 0 && button01 != null) { //Runs all the button checks
			
			//Button 1 of Menu State 0
			if(gsm.MouseClick[1] >= button01[0] && gsm.MouseClick[1] <= button01[2]) {
				if(gsm.MouseClick[2] >= button01[1] && gsm.MouseClick[2] <= button01[3]) {
					currentState = 1;
				}
			}
			
			//Button 2 of Menu State 0
			if(gsm.MouseClick[1] >= button02[0] && gsm.MouseClick[1] <= button02[2]) {
				if(gsm.MouseClick[2] >= button02[1] && gsm.MouseClick[2] <= button02[3]) {
					Common.print("Button 2 Was clicked!");
				}
			}
			
			//Button 3 of Menu State 0
			if(gsm.MouseClick[1] >= button03[0] && gsm.MouseClick[1] <= button03[2]) {
				if(gsm.MouseClick[2] >= button03[1] && gsm.MouseClick[2] <= button03[3]) {
					currentState = 2;
				}
			}
			
			//Button 4 of Menu State 0
			if(gsm.MouseClick[1] >= button04[0] && gsm.MouseClick[1] <= button04[2]) {
				if(gsm.MouseClick[2] >= button04[1] && gsm.MouseClick[2] <= button04[3]) {
					Common.print("Game Quit Button Pressed in menu!");
					System.exit(0);
				}
			}
		}
		
		
		if(gsm.MouseClick[0] == 1 && currentState == 1 && button11 != null) { //Runs all the button checks
			
			//Button 1 of Menu State 1
			if(gsm.MouseClick[1] >= button11[0] && gsm.MouseClick[1] <= button11[2]) {
				if(gsm.MouseClick[2] >= button11[1] && gsm.MouseClick[2] <= button11[3]) {
					gsm.Rwr.CreateSave("Save1");
					gsm.ChosenSave = "Save1";
					JukeBox.stop("MenuTheme");
					gsm.setState(GameStateManager.PLAY);
				}
			}
			
			//Button 2 of Menu State 1
			if(gsm.MouseClick[1] >= button12[0] && gsm.MouseClick[1] <= button12[2]) {
				if(gsm.MouseClick[2] >= button12[1] && gsm.MouseClick[2] <= button12[3]) {
					gsm.Rwr.CreateSave("Save2");
					gsm.ChosenSave = "Save2";
					//JukeBox.stop("MenuTheme");
					gsm.setState(GameStateManager.PLAY);
				}
			}
			
			//Button 3 of Menu State 1
			if(gsm.MouseClick[1] >= button13[0] && gsm.MouseClick[1] <= button13[2]) {
				if(gsm.MouseClick[2] >= button13[1] && gsm.MouseClick[2] <= button13[3]) {
					gsm.Rwr.CreateSave("Save3");
					gsm.ChosenSave = "Save3";
					//JukeBox.stop("MenuTheme");
					gsm.setState(GameStateManager.PLAY);
				}
			}
			
			//Button 4 of Menu State 1
			if(gsm.MouseClick[1] >= button14[0] && gsm.MouseClick[1] <= button14[2]) {
				if(gsm.MouseClick[2] >= button14[1] && gsm.MouseClick[2] <= button14[3]) {
					currentState = 0;
				}
			}
			
			
		}
		
		if(gsm.MouseClick[0] == 1 && currentState == 2 && button21 != null) { //Runs all the button checks
					
			//Button 1 of Menu State 0
			if(gsm.MouseClick[1] >= button21[0] && gsm.MouseClick[1] <= button21[2]) {
				if(gsm.MouseClick[2] >= button21[1] && gsm.MouseClick[2] <= button21[3]) {
					
				}
			}
			
			if(gsm.MouseDrag[1] >= Slider22[0] && gsm.MouseDrag[1] <= Slider22[2]) { // THIS IS THE SLIDER
				if(gsm.MouseDrag[2] >= Slider22[1] && gsm.MouseDrag[2] <= Slider22[3]) {
					float Slider22Valuetemp = ((float)(gsm.MouseDrag[1] - Slider22[0])/(Slider22[2] - Slider22[0]));
					Slider22Value =  Slider22Valuetemp;
				}
			}
			
			//Button 4 of Menu State 0
			if(gsm.MouseClick[1] >= button24[0] && gsm.MouseClick[1] <= button24[2]) {
				if(gsm.MouseClick[2] >= button24[1] && gsm.MouseClick[2] <= button24[3]) {
					currentState = 0;
				}
			}
			
			if(gsm.MouseClick[1] >= checkbox15[0] && gsm.MouseClick[1] <= checkbox15[2]) { // THE CHECK BOX
				if(gsm.MouseClick[2] >= checkbox15[1] && gsm.MouseClick[2] <= checkbox15[3]) {
					checkbox15Value = !checkbox15Value;
				}
			}
			
			
		}
		
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