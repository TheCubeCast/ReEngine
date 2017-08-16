package com.thecubecast.ReEngine.Graphics;

import com.thecubecast.ReEngine.Window.GamePanel;

import com.thecubecast.ReEngine.Data.Common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Draw {
	
	
	//Always set to 1 above the number of spites in file
	public BufferedImage[] Tiles = new BufferedImage[60];
	public BufferedImage[] Images = new BufferedImage[4];
	
	public void Init() {
		for(int i=0; i < Tiles.length; ++i){
			if (i >= 10) {
				try {
					Common.print("Loaded images /Sprites/"+ Integer.toString(i) +".png");
					Tiles[i] = ImageIO.read(getClass().getResourceAsStream("/Sprites/megaminer_"+ Integer.toString(i) +".png"));
				}
				catch(Exception e) {
					//e.printStackTrace();
				}
			} else {
				try {
					Common.print("Loaded images /Sprites/0"+ Integer.toString(i) +".png");
					Tiles[i] = ImageIO.read(getClass().getResourceAsStream("/Sprites/megaminer_0"+ Integer.toString(i) +".png"));
				}
				catch(Exception e) {
					//e.printStackTrace();
				}
			}
        }
		for(int i=0; i < Images.length; ++i){
			if (i >= 10) {
				try {
					Common.print("Loaded images /Images/"+ Integer.toString(i) +".png");
					Images[i] = ImageIO.read(getClass().getResourceAsStream("/Images/image_"+ Integer.toString(i) +".png"));
				}
				catch(Exception e) {
					//e.printStackTrace();
				}
			} else {
				try {
					Common.print("Loaded images /Images/0"+ Integer.toString(i) +".png");
					Images[i] = ImageIO.read(getClass().getResourceAsStream("/Images/image_0"+ Integer.toString(i) +".png"));
				}
				catch(Exception e) {
					//e.printStackTrace();
				}
			}
        }

		try {
			GraphicsEnvironment ge = 
			         GraphicsEnvironment.getLocalGraphicsEnvironment();
			     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Munro.ttf")));
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	public void DrawAny(Graphics buffer, int ID,int x, int y) {
		buffer.drawImage(Tiles[ID], x, y, 40, 40, null);
	}
	
	public void DrawBackground(Graphics buffer, int x, int y) {
		//Function is responsible for drawing the backgrounds, behind the tiles
		buffer.drawImage(Tiles[07], 0, 0, x, y, null);
	}
	
	//Renders the tiles across the world
	public void DrawTiles(Graphics buffer, int OffsetX, int OffsetY, int TileSize, int WorldSize) {
	//Function is for drawing the main tiles
		
		
		
	//	for(int i=0; i < 60; ++i){  //draws the top layer of grass
	//		buffer.drawImage(Tiles[01], i*40 - OffsetX, 20 - OffsetY, TileSize, TileSize, null);
	//	}
	//	for(int i=0; i < 260; ++i){  //draws the dirt
	//		if (i < 60) {
	//			buffer.drawImage(Tiles[00], i*40 - OffsetX, 60 - OffsetY, TileSize, TileSize, null);
	//		}
	//		else if (i >= 60 && i < 120){
	//			buffer.drawImage(Tiles[00], (i - 60)*40 - OffsetX, 100 - OffsetY, TileSize, TileSize, null);
	//		}
	//		else if (i >= 120 && i < 200){ 
	//			buffer.drawImage(Tiles[00], (i - 120)*40 - OffsetX, 140 - OffsetY, TileSize, TileSize, null);
	//		}
	//		else if (i >= 200 && i < 260){ 
	//			buffer.drawImage(Tiles[00], (i - 200)*40 - OffsetX, 180 - OffsetY, TileSize, TileSize, null);
	//		}
	//	}
	}
	
public void DrawTilesForeground(Graphics buffer, int OffsetX, int OffsetY, int TileSize, int WorldSize) {
		//Function is for drawing the tiles that go in front of the player layer wise
	}
	
	//This will handle the animations as well
	public void Player(Graphics buffer, int PosX, int PosY, int Sizex, int Sizey, int direction) {
		if (direction == 1) {
			//Common.print("player moved up");
			buffer.drawImage(Tiles[53], PosX, PosY, Sizex, Sizey, null);
			//Common.print("player drawn at x:" + PosX + " and y:" + PosY + " at sizes " + Sizex + " " + Sizey + " .");
		}
		if (direction == 2) {
			//Common.print("player moved left");
			buffer.drawImage(Tiles[55], PosX, PosY, Sizex, Sizey, null);
		}
		if (direction == 3) {
			//Common.print("player moved down");
			buffer.drawImage(Tiles[54], PosX, PosY, Sizex, Sizey, null);
		}
		if (direction == 4) {
			//Common.print("player moved right");
			buffer.drawImage(Tiles[56], PosX, PosY, Sizex, Sizey, null);
		}
	}
	
	//The GUI or Menu would go here.
	public void GUI(Graphics buffer, int PosX, int PosY, int Sizex, int Sizey) {
		buffer.drawImage(Tiles[57], PosX, PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[58], PosX + Sizex, PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[58], PosX + (Sizex*2), PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[59], PosX + (Sizex*3), PosY, Sizex, Sizey, null);
		buffer.drawString("testing GUI" , PosX + 40, PosY + 15);
	}
	
	public void GUIMenu(Graphics buffer, int PosX, int PosY, int Sizex, int Sizey) {
		buffer.drawImage(Tiles[57], PosX, PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[58], PosX - Sizex, PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[58], PosX, PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[59], PosX + Sizex, PosY, Sizex, Sizey, null);
		buffer.drawString("The Menu" , PosX, PosY + 15);
	}
	
}