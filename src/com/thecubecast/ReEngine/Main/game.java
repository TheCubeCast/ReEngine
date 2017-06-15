package com.thecubecast.ReEngine.Main;

import com.thecubecast.ReEngine.Main.InputHandler; 

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Game extends JFrame {

	private int FPS = 120;
	private String Title = "ReEngine";
	private int Width = 600;
	private int Height = 400;
	
	Insets insets;
	InputHandler input;
	
	private BufferedImage backBuffer;
	private int x = 0;

	public static void main(String[] args) {
		Game ReEngine = new Game();
		ReEngine.initialize();
		ReEngine.run();
		System.exit(0);
	}
	
	public void run() {
		boolean isRunning = true;
		
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
		setTitle(Title); 
        setSize(Width, Height); 
        setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setVisible(true); 
        
        insets = getInsets(); 
        setSize(insets.left + Width + insets.right, insets.top + Height + insets.bottom); 
        
        backBuffer = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
        input = new InputHandler(this); 
	}
	
	void update() {
		if (input.isKeyDown(KeyEvent.VK_D)) 
		{ 
		        x += 5; 
		} 
		if (input.isKeyDown(KeyEvent.VK_A)) 
		{ 
		        x -= 5; 
		} 
		
	}
	
	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		
		bbg.setColor(Color.white);
		bbg.fillRect(0, 0, Width, Height);
		
		bbg.setColor(Color.black);
		bbg.drawOval(x, 10, 20, 20);
		
		g.drawImage(backBuffer, insets.left, insets.top, this); 
	}
}