// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.

package com.thecubecast.ReEngine.Window;

import java.awt.BorderLayout; 

import javax.swing.*;

import com.thecubecast.ReEngine.Data.Common;

public class Main {
	
	private static String Title = "ReEngine";
	
	public static void main(String[] args) {
		
		//Reads the settings from file to run with chosen settings
		
		JFrame window = new JFrame(Title);
		window.setLayout(new BorderLayout());
		window.add(new GamePanel(), BorderLayout.CENTER);
		
		window.pack();
		window.setVisible(true); 		
		
		window.setResizable(true); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setExtendedState( window.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		Common.print(window.getBounds().width + " X " + window.getBounds().height);
	}
	
}

