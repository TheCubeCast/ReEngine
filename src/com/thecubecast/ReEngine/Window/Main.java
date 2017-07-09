// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.

package com.thecubecast.ReEngine.Window;

import javax.swing.JFrame;

import com.thecubecast.ReEngine.Data.Common;

public class Main {
		
	private static String Title = "ReEngine";
	public static final int WIDTH = Common.GetMonitorSizeW();
	public static final int HEIGHT = Common.GetMonitorSizeH();
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame(Title);
		
		window.add(new GamePanel());
		
		window.pack();
		
		window.setTitle(Title); 
		window.setSize(WIDTH, HEIGHT); 
		window.setResizable(false); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		window.setVisible(true); 
		
	}
	
}
