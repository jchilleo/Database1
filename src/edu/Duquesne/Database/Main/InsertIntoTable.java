package edu.Duquesne.Database.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class InsertIntoTable extends TableContainer {


	/**
	 * add a line to the table
	 * @param tableName name of the table (same as filename without extension)
	 * @param columns (All the column data separated by spaces (use underscore if space exist in name))
	 */
	public void addToTable(String tableName, String columns){
		ArrayList<String> insert = new ArrayList<String>();
		insert.add("false"); //by default tombstone is marked as false, so it won't be deleted during purge.
		insert.add(tableName);
		insert = parseInsertColumns(insert, columns);
		table.add(insert);
		addToDbFile(tableName, insert);
	}
	/**
	 * parse a string of column data and add to table line.
	 * @param insert 
	 * @param columns
	 */
	private ArrayList<String> parseInsertColumns(ArrayList<String> insert, String columns){
		String[] extractedStrings = columns.split("\\s+");
		int index = 2;
		for(String exString : extractedStrings){
			exString = cutString(index, exString);
			insert.add(exString);
			index++;
		}
		return (new ArrayList<>(insert));
	}
	/**
	 * Cut column data to the appropriate record length.
	 * @param index - location or row in the table of the string
	 * @param cString - the string needed to be cut
	 * @return a string with the correct length for a column
	 */
	private String cutString(int index, String cString){
		
		int maxLength = columnLength.get(index);
		if(cString.length() <= maxLength){
			return cString;
		}
		else{
			cString = cString.substring(0, maxLength);
			return cString;
		}
	}
	/**
	 * Add to database file
	 * @param fileName name of the file
	 * @param insert -Arraylist object that needs to be added to the file. 
	 */
	private void addToDbFile(String fileName, ArrayList<String> insert){
		
		String addToFile = null;
		addToFile = lineToString(insert);
		/*
		 * Code segement acquired from:
		 * http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
		 */
		try {
			Files.write(Paths.get("src/edu/Duquesne/Database/files/" + fileName + ".txt"), System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
		    Files.write(Paths.get("src/edu/Duquesne/Database/files/" + fileName + ".txt"), addToFile.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) { e.printStackTrace();}

	}
	/**
	 * converts an ArrayList<Strings> into a long concatenated string
	 * @param lineUpdate - ArrayList object to be stringed
	 * @return - a string of all data in the arrayList concatenated together.
	 */
	private String lineToString(ArrayList<String> lineUpdate){
		String stringLine = "";
		for(String tmp : lineUpdate){
			stringLine = stringLine.concat(tmp + " ");
		}
		return stringLine;
	}
	
}
