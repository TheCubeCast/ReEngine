package com.thecubecast.ReEngine.Data;


public class Map {
    private int x;
    private int y;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
	private int GrassDepth = -1; //Keep in mind that above the grass is positive y coordinates. Depth would be negative
	private int StoneDepth = 10;
	public float StoneDepthVariation = 0.2f; //This is how much the stone randomizes away from the static stone depth
	
	
	
}