package edu.Duquesne.Database.Main;

import java.util.ArrayList;

public class TableContainer {
	private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	private ArrayList<Integer> columnLength = new ArrayList<Integer>();
	private int recordLength = 0, columnTotal = 0;

	public ArrayList<ArrayList<String>> getTable(){
		return table;
	}
	public void setTable(ArrayList<ArrayList<String>> table){
		this.table = table;
	}

	public ArrayList<Integer> getColumnLengths(){
		return columnLength;
	}
	public void setColumnLengths(ArrayList<Integer> columnLength){
		this.columnLength = columnLength;
	}
	public int getRecordLength(){
		return recordLength;
	}
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
