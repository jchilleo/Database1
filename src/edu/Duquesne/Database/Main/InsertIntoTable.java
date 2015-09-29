package edu.Duquesne.Database.Main;

import java.util.ArrayList;

public class InsertIntoTable extends TableContainer {

	private ArrayList<ArrayList<String>> table = getTable();
	//TODO add a setTable() call
	
	public void addToTabel(String tableName, String columns){
		ArrayList<String> insert = new ArrayList<String>();
		insert.add("fasle");
		insert.add(tableName);
		parseInsertColumns(insert, columns);
		table.add(insert);
		setTable(table);
	}
	
	private void parseInsertColumns(ArrayList<String> insert, String columns){
		String[] extractedStrings = columns.split("\\s+");
		for(String exString : extractedStrings){
			insert.add(exString);
			
		}
	}
	
}
