package com.thecubecast.ReEngine.Graphics;

import com.thecubecast.ReEngine.Window.GamePanel;

import com.thecubecast.ReEngine.Data.Common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Draw {
	
	
	//Always set to 1 above the number of spites in file
	public BufferedImage[] Tiles = new BufferedImage[70];
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
	
	public void DrawAnimatedTile(Graphics buffer, int x, int y, int Tics) {
		
	}
	
	public void DrawChunkDebugLines(Graphics buffer, int x, int y , int TileSize, int cameraX, int cameraY) {
		buffer.drawLine(0-cameraX, 0-cameraY, (TileSize*16)-cameraX, 0-cameraY);
		buffer.drawLine(0-cameraX, 0- cameraY, 0-cameraX, (TileSize*16)-cameraY);
		buffer.drawLine((TileSize*16)-cameraX, 0-cameraY, (TileSize*16)-cameraX, ((TileSize*16))-cameraY);
		buffer.drawLine(0-cameraX, ((TileSize*16))-cameraY, (TileSize*16)-cameraX, ((TileSize*16))-cameraY);
	}
	
	//Renders the tiles across the world
	public void DrawTiles(Graphics buffer, int OffsetX, int OffsetY, int TileSize, int WorldSize) {
	//Function is for drawing the main tiles
		
		
		
		for(int i=0; i < 60; ++i){  //draws the top layer of grass
			buffer.drawImage(Tiles[01], i*40 - OffsetX, 20 - OffsetY, TileSize, TileSize, null);
		}
		for(int i=0; i < 260; ++i){  //draws the dirt
			if (i < 60) {
				buffer.drawImage(Tiles[00], i*40 - OffsetX, 60 - OffsetY, TileSize, TileSize, null);
			}
			else if (i >= 60 && i < 120){
				buffer.drawImage(Tiles[00], (i - 60)*40 - OffsetX, 100 - OffsetY, TileSize, TileSize, null);
			}
			else if (i >= 120 && i < 200){ 
				buffer.drawImage(Tiles[00], (i - 120)*40 - OffsetX, 140 - OffsetY, TileSize, TileSize, null);
			}
			else if (i >= 200 && i < 260){ 
				buffer.drawImage(Tiles[00], (i - 200)*40 - OffsetX, 180 - OffsetY, TileSize, TileSize, null);
			}
		}
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
	public void GUIDeco(Graphics buffer, int PosX, int PosY, int Sizex, int Sizey, String Text) {
		buffer.drawImage(Tiles[57], PosX, PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[58], PosX + Sizex, PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[58], PosX + (Sizex*2), PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[58], PosX + (Sizex*3), PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[58], PosX + (Sizex*4), PosY, Sizex, Sizey, null);
		buffer.drawImage(Tiles[59], PosX + (Sizex*5), PosY, Sizex, Sizey, null);
		buffer.drawString("testing GUI - " + Text, PosX + 40, PosY + (Sizey/2));
	}
	
	public int[] GUIButton(Graphics buffer, int PosX, int PosY, int length, int Sizex, int Sizey, boolean center, String text) {
		if (center) {
			PosX = PosX - ((Sizex*length)/2);
		}
		buffer.drawImage(Tiles[57], PosX, PosY, Sizex, Sizey, null);
		for (int i=1; i < (length + 1); i++) {
			buffer.drawImage(Tiles[58], PosX + (Sizex*i), PosY, Sizex, Sizey, null);
		}
		buffer.drawImage(Tiles[59], PosX + (Sizex*length), PosY, Sizex, Sizey, null);
		buffer.drawString(text , PosX+((Sizex*length)/2), PosY + (Sizey/2));
		
		int[] size = new int[] {PosX, PosY, PosX+(Sizex*length), PosY+(Sizey)}; //subtract 10 from the y cause the sprite for these are 10pixels, not the normal 16
		return size;
	}
	
	public int[] GUISlider(Graphics buffer, int PosX, int PosY, int length, int Sizex, int Sizey, boolean center, float SliderValue) {
		if (center) {
			PosX = PosX - ((Sizex*length)/2);
		}
		buffer.drawImage(Tiles[60], PosX, PosY, Sizex, Sizey, null);
		for (int i=1; i < (length + 1); i++) {
			buffer.drawImage(Tiles[61], PosX + (Sizex*i), PosY, Sizex, Sizey, null);
		}
		buffer.drawImage(Tiles[62], PosX + (Sizex*length), PosY, Sizex, Sizey, null);
		//Draws the Dot
		buffer.drawImage(Tiles[63], PosX + Math.round(((Sizex*(length))*SliderValue)), PosY, Sizex, Sizey, null);		
		
		int[] size = new int[] {PosX, PosY, PosX+(Sizex*length), PosY+(Sizey)}; 
		return size;
	}
	
	public int[] GUICheckBox(Graphics buffer, int PosX, int PosY, boolean Checked, int Sizex, int Sizey) {
		if (Checked) {
			buffer.drawImage(Tiles[68], PosX, PosY, Sizex, Sizey, null);
		}
		else {
			buffer.drawImage(Tiles[67], PosX, PosY, Sizex, Sizey, null);	
		}
		
		int[] size = new int[] {PosX, PosY, PosX+(Sizex), PosY+(Sizey)};
		return size;
	}
	
}