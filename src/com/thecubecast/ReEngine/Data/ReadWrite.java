package com.thecubecast.ReEngine.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ReadWrite {
	
	public static void init() { //Create the folders that hold everything neatly
		Path path = Paths.get("Saves");
		//Path p3 = Paths.get(URI.create("http:///dev.thecubecast.com/Login.php?User=BLANK"));
		
		if (Files.notExists(path)) {
			new File("Saves").mkdir();
			Common.print("Created 'Saves' folder!");
		}
	}
	
	public static Object[] LoadSettings() {
		//all the code that reads the file
		
		Path SettingsPath = Paths.get("Settings.properties");
		Object[] settup = null;
		
		if (Files.notExists(SettingsPath)) { // runs if the settings file does not exist
			//create new settings file with universal settings that work for everyone 
			Path path = Paths.get("", "Settings.config");
			ArrayList<String> lines = new ArrayList<>();
	        lines.add("#Settings");
	        lines.add("\n");
	        lines.add("Agreed:False");
			try {
				Files.write(path, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
			} catch (IOException e) {e.printStackTrace();}
		}
		else {
			//save each setting to a value in the array
			try {
				settup = Files.readAllLines(SettingsPath).toArray();
			} catch (IOException e) {e.printStackTrace();}
			
		}
		return settup; // returns the array containing each value in settings. Settings file format can be dynamically changed to add new values.
		
	}
	
	public boolean CreateSave(String Title) {
		//creates the world folder
		new File("Saves/"+Title).mkdir();
		new File("Saves/"+Title+"/Chunks").mkdir();
		Common.print("Created '"+ Title +"' save!");
		//creates the chunk folder and populates it with the starting chunks.
		
		//returns true or false depending on whether world files were successfully loaded
		return false;
		//the chunks are loaded independently from the world creation.
	}
	
	//Creates a new chunk
	public boolean CreateChunk(String Save, int[] id, int ChunkSize) {
		
		if(id[1] > 0) {
			ArrayList<String> lines = new ArrayList<>();
			for (int i=1; i < (ChunkSize + 1); i++) {
				lines.add("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
			}
			try {
				Files.write(Paths.get("Saves/"+Save+"/Chunks", "Chunk_"+id[0]+"_"+ id[1] +".dat"), lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
			} catch (IOException e) {e.printStackTrace();}
		}
		return true;
	}
	
	//Loads the chunk from file and adds its 2d array to memory
	public boolean LoadChunk(int[] id) {
		//int[] id is the chunk id, EX (1, -4) or 1 chunk to the right and down 4 from origin 
		
		return true;
	}
	
	//This is run when the player mines a block, changing the 2d array of the chunk
	//This takes the 2d array and rewrites the chunk on file
	public boolean UpdateChunk() {
		
		return true;
	}
	
	//This unloads the chunk from memory
	public boolean UnloadChunk() {
		
		return true;
	}
}