package edu.Duquesne.Database.Main;

import java.util.ArrayList;

public class removeTableLine extends TableContainer{
	
	private ArrayList<ArrayList<String>> table = getTable();
	
	/**
	 * Changes tombstone to true at specific index in the table, so that purge will fully remove the entry.
	 * @param indexToRemove - The index in the ArrayList that is intended to be removed.
	 */
	public void removeEntry(int indexToRemove){
		ArrayList<String> temp = table.get(indexToRemove);
		temp.remove(0); //position 0 will always be the tombstone
		temp.add(0, "true");
		table.remove(indexToRemove);
		table.add(indexToRemove, temp);
		setTable(table);
	}
}
