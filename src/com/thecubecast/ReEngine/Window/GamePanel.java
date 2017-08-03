// The GamePanel is the drawing canvas.
// It contains the game loop which
// keeps the game moving forward.
// This class is also the one that grabs key events.

package com.thecubecast.ReEngine.Window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.thecubecast.ReEngine.Data.Keys;

import com.thecubecast.ReEngine.Data.Common;

import com.thecubecast.ReEngine.Data.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	// dimensions
	// HEIGHT is the playing area size
	// HEIGHT2 includes the bottom window
	public int WIDTH = getSize().width;
	public int HEIGHT = getSize().height;
	
	// game loop stuff
	private Thread thread;
	private boolean running;
	private final int FPS = 120;
	private final int TARGET_TIME = 1000 / FPS;
	
	// drawing stuff
	private BufferedImage BackBuffer;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;
	
	// constructor
	public GamePanel() {
		addComponentListener(new WindowListener());
		setFocusable(true);
		requestFocus();
	}
	
	// ready to display
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			addKeyListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}
	
	// run new thread
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = TARGET_TIME - elapsed / 1000000;
			if(wait < 0) wait = TARGET_TIME;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	// initializes fields
	private void init() {
		running = true;
		HEIGHT = getHeight();
		WIDTH = getWidth();
		Common.print("Panel Size initialized to " + WIDTH + " X " + HEIGHT + ".");
		while (HEIGHT == 0 | WIDTH == 0) {
			Common.sleep(20);
			HEIGHT = getHeight();
			WIDTH = getWidth();
		}
		BackBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB); 
		g = (Graphics2D) BackBuffer.getGraphics();
		gsm = new GameStateManager();
		Common.print("Panel Size is " + WIDTH + " X " + HEIGHT + ".");
	}
	
	// updates game
		private void update() {
			gsm.update();
			//Common.print("W: " + WIDTH + " and H: " + HEIGHT );
			
		}
	
	// draws game
	private void draw() {
		gsm.draw(g, HEIGHT, WIDTH);
	}
	
	// copy buffer to screen
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(BackBuffer, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
	
	public class WindowListener implements ComponentListener {
		
		@Override
		public void componentHidden(ComponentEvent e) {
			//if the window was minimized
		}
	
		@Override
		public void componentMoved(ComponentEvent e) {
			//Who cares	
		}
	
		public void componentResized(ComponentEvent e) {
			HEIGHT = getHeight();
			WIDTH = getWidth();
			Common.print("JPanel resized to W: " + e.getComponent().getWidth() + " by H: " + e.getComponent().getHeight());
		}
	
		@Override
		public void componentShown(ComponentEvent e) {
			//If the window was restored
		}
			
	}

	// key event
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), true);
	}
	public void keyReleased(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), false);
	}
}
