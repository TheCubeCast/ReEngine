package com.thecubecast.ReEngine.Main;

import com.thecubecast.ReEngine.Main.JukeBox;
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
        
        JukeBox.init();
	}
	
	
	void update() {
		//if (input.isKeyDown(KeyEvent.VK_D)) 
		//{ 
			if (x < 400 && y < 200) {
		       x = x + 1; 
			} else {
				if (y < 200) {
					 y = y + 1; 
				} else {
					if (x != 0) {
					 x = x - 1; 
					}
				}
			}
		//} 
		//if (input.isKeyDown(KeyEvent.VK_A)) 
		//{ 
		//        x -= 5; 
		//} 
		
	}
	
	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		
		bbg.setColor(Color.green);
		bbg.fillRect(0, 0, Width, Height);
		
		bbg.setColor(Color.red);
		bbg.drawOval(x, y, 20, 20);
		bbg.fillOval(x, y, 20, 20);
		
		g.drawImage(backBuffer, insets.left, insets.top, this); 
	}
}