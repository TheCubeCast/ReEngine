package com.thecubecast.ReEngine.Graphics;

import com.thecubecast.ReEngine.Data.Common;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Draw {
	
	
	//Always set to 1 above the number of spites in file
	private BufferedImage[] Tiles = new BufferedImage[54];
	
	
	public void Init() {
		for(int i=0; i < Tiles.length; ++i){
			if (i >= 10) {
				try {
					Common.print("Loaded images /Sprites/"+ Integer.toString(i) +".png");
					Tiles[i] = ImageIO.read(getClass().getResourceAsStream("/Sprites/"+ Integer.toString(i) +".png"));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					Common.print("Loaded images /Sprites/0"+ Integer.toString(i) +".png");
					Tiles[i] = ImageIO.read(getClass().getResourceAsStream("/Sprites/0"+ Integer.toString(i) +".png"));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
        }
	}
	
	
	public void Background(Graphics buffer, int x, int y) {
		buffer.drawImage(Tiles[07], 0, 0, x, y, null);
	}
	
	
	//Renders the tiles across the world
	public void DrawTiles(Graphics buffer, int OffsetX, int OffsetY, int TileSize) {
		buffer.drawImage(Tiles[00], 0 - OffsetX, 20 - OffsetY, TileSize, TileSize, null);
		buffer.drawImage(Tiles[00], 40 - OffsetX, 20 - OffsetY, TileSize, TileSize, null);
		buffer.drawImage(Tiles[00], 80 - OffsetX, 20 - OffsetY, TileSize, TileSize, null);
		buffer.drawImage(Tiles[00], 120 - OffsetX, 20 - OffsetY, TileSize, TileSize, null);
		buffer.drawImage(Tiles[00], 0 - OffsetX, 60 - OffsetY, TileSize, TileSize, null);
		buffer.drawImage(Tiles[00], 40 - OffsetX, 60 - OffsetY, TileSize, TileSize, null);
		buffer.drawImage(Tiles[00], 80 - OffsetX, 60 - OffsetY, TileSize, TileSize, null);
		buffer.drawImage(Tiles[00], 120 - OffsetX, 60 - OffsetY, TileSize, TileSize, null);
	}
	
	//This will handle the animations as well
	public void Player(Graphics buffer, int PosX, int PosY, int Sizex, int Sizey) {
		buffer.drawImage(Tiles[53], PosX, PosY, Sizex, Sizey, null);
	}
	
}