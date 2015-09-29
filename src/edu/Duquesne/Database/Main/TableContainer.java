package edu.Duquesne.Database.Main;

import java.util.ArrayList;

public class TableContainer {
	private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	private ArrayList<Integer> columnLength = new ArrayList<Integer>();

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

}
