package com.thecubecast.ReEngine.Main;

import java.awt.Component; 
import java.awt.event.*; 

public class InputHandler implements KeyListener 
{        
	
		private boolean keys[];
	
        public InputHandler(Component c) 
        { 
                c.addKeyListener(this); 
        } 
        
        /** 
         * Checks whether a specific key is down 
         * @param keyCode The key to check 
         * @return Whether the key is pressed or not 
         */ 
        public boolean isKeyDown(int keyCode) 
        { 
                if (keyCode > 0 && keyCode < 256) 
                { 
                        return keys[keyCode]; 
                } 
                
                return false; 
        } 
        
        /** 
         * Called when a key is pressed while the component is focused 
         * @param e KeyEvent sent by the component 
         */ 
        public void keyPressed(KeyEvent e) 
        { 
                
        } 

        /** 
         * Called when a key is released while the component is focused 
         * @param e KeyEvent sent by the component 
         */ 
        public void keyReleased(KeyEvent e) 
        { 
                
        } 

        /** 
         * Not used 
         */ 
        public void keyTyped(KeyEvent e){} 
} 