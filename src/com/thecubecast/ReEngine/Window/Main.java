// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.

package com.thecubecast.ReEngine.Window;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

import com.thecubecast.ReEngine.Data.Common;

public class Main {
		
	static Main outer = new Main();
	GamePanel gamePanel = new GamePanel();
	
	private static String Title = "ReEngine";
	
	public int WindowW;
	public int WindowH;
	
	public static void main(String[] args) {
		
		Main main = new Main();
		
		//Reads the settings from file to run with chosen settings
		
		JFrame window = new JFrame(Title);
		window.add(new GamePanel());
		
		window.pack();
		window.setVisible(true); 
		
		window.setResizable(true); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setExtendedState( window.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		window.addComponentListener(outer.new WindowListener());
		
		main.WindowH = window.getBounds().height;
		main.WindowW = window.getBounds().width;
		
		Common.print(main.WindowH + " X " + main.WindowW);
	}
	
	public class WindowListener implements ComponentListener {

		GamePanel gamePanel = new GamePanel();
		
		@Override
		public void componentHidden(ComponentEvent e) {
			//if the window was minimized
			
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			//Who cares
			
		}

		public void componentResized(ComponentEvent e) {
			//Common.print(WindowH + " X " + WindowW); //This is the size of the window before being changed
			Common.print("Window resized to H: " + e.getComponent().getHeight() + " by W: " + e.getComponent().getWidth());
			WindowW = e.getComponent().getWidth();
			WindowH = e.getComponent().getHeight();
			gamePanel.SizeChange(WindowH, WindowW, false);
		}

		@Override
		public void componentShown(ComponentEvent e) {
			//If the window was restored
			
		}
		
	}
	
}
