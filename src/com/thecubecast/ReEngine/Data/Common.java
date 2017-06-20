package com.thecubecast.ReEngine.Data;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Common {
	
	public static int GetMonitorSizeW() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getWidth();
	}
	public static int GetMonitorSizeH() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getHeight();
	}
	
	public static void print(String Output) {
		System.out.println(Output);
	}
	
	public static void sleep(int Time) {
		 try {
			 // thread to sleep for 1000 milliseconds
	         Thread.sleep(Time);
	     } catch (Exception e) {
	    	 System.out.println(e);
	     }
	}
}