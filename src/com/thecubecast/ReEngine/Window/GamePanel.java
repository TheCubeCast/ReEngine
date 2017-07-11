// The GamePanel is the drawing canvas.
// It contains the game loop which
// keeps the game moving forward.
// This class is also the one that grabs key events.

package com.thecubecast.ReEngine.Window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
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
	public static final int WIDTH = Common.GetMonitorSizeW();
	public static final int HEIGHT = Common.GetMonitorSizeH();
	
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
		BackBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) BackBuffer.getGraphics();
		gsm = new GameStateManager();
		Common.print("Screen Height is " + HEIGHT + " and Width is " + WIDTH + ".");
	}
	
	// updates game
	private void update() {
		gsm.update();
		
	}
	
	// draws game
	private void draw() {
		gsm.draw(g);
	}
	
	// copy buffer to screen
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(BackBuffer, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
	
	//Mouse Event
	 public void mousePressed(MouseEvent e) {
		 Common.print("Mouse pressed; # of clicks: " + e.getClickCount());
	 }
	 
	 public void mouseReleased(MouseEvent e) {
	      Common.print("Mouse released; # of clicks: " + e.getClickCount());
	 }
	public int getmousePosX() {
		int X = 0;
		return X;
	}
	public int getmousePosY() {
		int Y = 0;
		return Y;
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
