package edu.Duquesne.Database.Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TablePurge extends TableContainer {
	
	/**
	 * Removes all table entries with tombstones marked as true from the database file.
	 */
	public void purgeDataBase(){
		ArrayList<String> header = table.get(0);
		String fileName = "", extractedMeta = "";
		fileName = header.get(1);
		extractedMeta = extractMetaData(new ArrayList<>(header));
		try{
		File file = new File("src/edu/Duquesne/Database/files/" + fileName + ".txt");
		file.delete();
		}
		catch (Exception e){e.printStackTrace();}
		
		makeNewFile(fileName, extractedMeta);
	}
	
	/**
	 * Extracts string data from Arraylist and concatenates it.
	 * @param header - The first ArrayList (or row) in the table.
	 * @return returns the first row as a concatenated string. 
	 */
	private String extractMetaData(ArrayList<String> header){
		String meta = "";
		int index = 0;
		for(String tmp: header){
			if(index <2){}
			else{
				meta = meta.concat(tmp + " " + columnLength.get(index) + " ");
			}
			index++;
		}
		return meta;
	}
	
	/**
	 * Extracts string data from Arraylist and concatenates it.
	 * @param tableLine - A row from the database table.
	 * @return A row from the database in a concatenated string.
	 */
	private String extractData(ArrayList<String> tableLine){
		String data = "";
		int index = 0;
		boolean tombStone = false;
		tombStone = Boolean.parseBoolean(tableLine.get(0));
		if(tombStone) return null;
		else{
		for(String tmp: tableLine){
				data = data.concat(tmp + " " + columnLength.get(index) + " ");
		}
		return data;
		}
	}
	/**
	 * deletes previous database file and creates a new one, not adding any lines with a positive tombstone.
	 * @param fileName
	 * @param meta
	 */
	private void makeNewFile(String fileName, String meta){
		File file = null;
		@SuppressWarnings("unused")
		boolean firstLine = true;
		String tmp;
		try { 
			file = new File("src/edu/Duquesne/Database/files/" + fileName + ".txt");
			file.createNewFile();
		}
		catch (IOException e) {e.printStackTrace();}
		try{
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.flush();
			bw.write(fileName);
			bw.newLine();
			bw.write(meta);
			bw.newLine();
			bw.flush();
			for(ArrayList<String> tLine: table){
				if(tLine.get(0) == "Tombstone"){}
				else{
				if(firstLine = Boolean.parseBoolean(tLine.get(0))) firstLine = false;
				else{
					tmp = extractData(new ArrayList<>(tLine));
					if(tmp == null){}
					else{
						bw.write(tmp);
						bw.newLine();
						bw.flush();
					}
				}
			}}
			bw.write(System.lineSeparator());
			bw.close();
			}
			catch(IOException e){e.printStackTrace();}
	}

}
