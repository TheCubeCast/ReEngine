package com.thecubecast.ReEngine.Main;

import com.thecubecast.ReEngine.Data.JukeBox;
import com.thecubecast.ReEngine.Main.InputHandler; 

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Transparency;
import javax.swing.JFrame;

public class Game extends JFrame {

	private BufferedImage Player;
	private BufferedImage Dirt;
	
	private int FPS = 120;
	private String Title = "ReEngine";
	private int Width = GetMonitorSizeW();
	private int Height = GetMonitorSizeH();
	
	Insets insets;
	InputHandler input;
	
	private BufferedImage backBuffer;
	private int x = 0;
	private int y = 10;

	public static void main(String[] args) {
		Game ReEngine = new Game();
		ReEngine.initialize();
		ReEngine.run();
		System.exit(0);
	}
	
	public void run() {
		boolean isRunning = true;
		
		JukeBox.load("/Music/bgmusic.wav", "introsound");
		JukeBox.loop("introsound");
		//JukeBox.setVolume("introsound", -30.0f);
		
		while(isRunning) {
			long time = System.currentTimeMillis();
			
			update();
			draw();
		
			//  delay looping so the FPS isn't crazy high
			//  delay for each frame  -   time it took for one frame 
	        time = (1000 / FPS) - (System.currentTimeMillis() - time); 

	        if (time > 0) 
            { 
                    try 
                    { 
                            Thread.sleep(time); 
                    } 
                    catch(Exception e){} 
            } 
		}
		setVisible(false); 
	}
	
	void initialize() {
		
		try {
			Player = ImageIO.read(getClass().getResourceAsStream("/Sprites/53.png"));
			Dirt = ImageIO.read(getClass().getResourceAsStream("/Sprites/00.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		setTitle(Title); 
        setSize(Width, Height); 
        setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true); 
        
        insets = getInsets(); 
        setSize(insets.left + Width + insets.right, insets.top + Height + insets.bottom); 
        
        backBuffer = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
        
        JukeBox.init();
        
        input = new InputHandler(this);
	}
	
	
	void update() {
	 
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) 
		{ 
		        x += 5; 
		} 
		if (input.isKeyDown(KeyEvent.VK_LEFT)) 
		{ 
		        x -= 5; 
		}
		if (input.isKeyDown(KeyEvent.VK_UP)) 
		{ 
		        y -= 5; 
		} 
		if (input.isKeyDown(KeyEvent.VK_DOWN)) 
		{ 
		        y += 5; 
		} 
		
		//IF STATEMENT THAT WILL BE PUT HERE WHEN INPUT HANDLING IS FIXED
		//IT WILL QUIT THE GAME
		
	}

	
	void draw() {
		//This sets up the FPS and important stuff
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		//Clears the screen each frame, Avoiding Artifacts
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, Width, Height);
		
		//The bottom layer
		//Draw the background here
		
		//The Tiles are being drawn on this "Layer"
		bbg.drawImage(Dirt, 20, 20, 40, 40, null);
		
		//The "Player" and other entities or overlays must be drawn last. Think top layer 
		bbg.drawImage(Player, x, y, 20, 20, null);
		
		g.drawImage(backBuffer, insets.left, insets.top, this); 
	}
	
	
	int GetMonitorSizeW() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getWidth();
	}
	int GetMonitorSizeH() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getHeight();
	}
}