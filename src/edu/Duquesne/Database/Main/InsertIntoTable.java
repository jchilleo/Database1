package edu.Duquesne.Database.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class InsertIntoTable extends TableContainer {

	private ArrayList<ArrayList<String>> table = getTable();
	private ArrayList<Integer> lengths = getColumnLengths();
	
	/**
	 * 
	 * @param tableName name of the table (same as filename without extension)
	 * @param columns (All the column data separated by spaces (use underscore if space exist in name))
	 */
	public void addToTable(String tableName, String columns){
		ArrayList<String> insert = new ArrayList<String>();
		insert.add("false"); //by default tombstone is marked as false, so it won't be deleted during purge.
		insert.add(tableName);
		parseInsertColumns(insert, columns);
		table.add(insert);
		setTable(table);
		addToDbFile(tableName, insert);
	}
	
	/**
	 * 
	 * @param insert 
	 * @param columns
	 */
	private void parseInsertColumns(ArrayList<String> insert, String columns){
		String[] extractedStrings = columns.split("\\s+");
		int index = 2;
		for(String exString : extractedStrings){
			exString = cutString(index, exString);
			insert.add(exString);
			index++;
		}
	}
	
	private String cutString(int index, String cString){
		int maxLength = lengths.get(index);
		if(cString.length() <= maxLength){
			return cString;
		}
		else{
			cString = cString.substring(0, maxLength);
			return cString;
		}
	}
	
	private void addToDbFile(String fileName, ArrayList<String> insert){
		
		String addToFile = null;
		addToFile = lineToString(insert);
		/*
		 * Code segement acquired from:
		 * http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
		 */
		try {
		    Files.write(Paths.get("/edu/Duquesne/Database/files/" + fileName + ".txt"), addToFile.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}

	}
	
	private String lineToString(ArrayList<String> lineUpdate){
		String stringLine = null;
		for(String tmp : lineUpdate){
			stringLine = stringLine.concat(tmp);
		}
		return stringLine;
	}
	
}
