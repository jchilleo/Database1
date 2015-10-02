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
	 * set the main table
	 * @param table - newer version of the main table.
	 */
	public void setTable(ArrayList<ArrayList<String>> table){
		this.table.addAll(table);
	}
	/**
	 * Getter for columnLengths
	 * @return - ArrayList<Integers> for all column lengths
	 */
	public ArrayList<Integer> getColumnLengths(){
		return (new ArrayList<>(columnLength));
	}
	/**
	 * setter for column lengths
	 * @param columnLength - arraylist of integers of column lengths
	 */
	public void setColumnLengths(ArrayList<Integer> columnLength){
		this.columnLength.addAll(columnLength);
	}
	/**
	 * Getter for total record length of all columns (expect the first two).
	 * @return - total combined record length.
	 */
	public int getRecordLength(){
		return recordLength;
	}
	/**
	 * Setter for total record length
	 * @param recordLength - total record length of all columns combined.
	 */
	public void setRecordLength(int recordLength){
		this.recordLength = recordLength;
	}
	public int getColumnTotal(){
		return columnTotal;
	}
	public void setColumnTotal(int columnTotal){
		this.columnTotal = columnTotal;
	}
}
