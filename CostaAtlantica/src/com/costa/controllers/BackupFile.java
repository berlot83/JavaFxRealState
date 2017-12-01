package com.costa.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;

public class BackupFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1198563003690066926L;

	public void saveFile(String text) {
		try {
			File file = new File("backup.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(text+"\n\n");
			fileWriter.flush();
			fileWriter.close();
			
		}catch(Exception error) {
			
		}
	}
	
}
