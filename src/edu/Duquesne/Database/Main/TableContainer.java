package edu.Duquesne.Database.Main;

import java.util.ArrayList;

public class TableContainer {
	protected static ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	protected static ArrayList<Integer> columnLength = new ArrayList<Integer>();
	protected static int recordLength = 0, columnTotal = 0;
	
	/**
	 * getter for the main table object
	 * @return the main table
	 */
	public ArrayList<ArrayList<String>> getTable(){
		return (new ArrayList<>(table));
	}

	/**
	 * Getter for columnLengths
	 * @return - ArrayList<Integers> for all column lengths
	 */
	public ArrayList<Integer> getColumnLengths(){
		return (new ArrayList<>(columnLength));
	}
	
	
	public int getRecordLength(){
		return recordLength;
	}
	
	public int getColumnTotal(){
		return columnTotal;
	}

}
