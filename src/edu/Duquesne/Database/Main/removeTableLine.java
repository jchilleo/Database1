package edu.Duquesne.Database.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class removeTableLine extends TableContainer{
	
	/**
	 * Changes tombstone to true at specific index in the table, so that purge will fully remove the entry.
	 * @param indexToRemove - The index in the ArrayList that is intended to be removed.
	 * @throws IOException 
	 */
	public void removeEntry(int indexToRemove) throws IOException{
		//ArrayList<ArrayList<String>> table = new ArrayList<>(getTable());
		String oldLine = null, newLine = null;
		ArrayList<String> temp = table.get(indexToRemove);
		oldLine = lineToString(temp);
		temp.remove(0); //position 0 will always be the tombstone
		temp.add(0, "true");
		table.remove(indexToRemove);
		table.add(indexToRemove, temp);
		//setTable(table);
		newLine = lineToString(temp);
		String fileName = temp.get(1);
		File file = new File("src/edu/Duquesne/Database/files/" + fileName + ".txt");
		updateLine(oldLine, newLine, file);
	}
	/**
	 * returns an index from table as a long string for print writing.
	 * @param lineUpdate -table entry to be converted to a string
	 * @return concatenated string.
	 */
	private String lineToString(ArrayList<String> lineUpdate){
		String stringLine = "";
		for(String tmp : lineUpdate){
			stringLine = stringLine.concat(tmp + " ");
		}
		return stringLine;
	}
	/*this method was modified slightly but gathered from
	 * http://stackoverflow.com/questions/25220340/java-replace-line-in-a-text-file
	 * reference site in this original post of above link
	 * http://stackoverflow.com/questions/20039980/java-replace-line-in-text-file
	 */
	/**
	 * Updates the file to reflect to tombstone change to from false to true.
	 * @param toUpdate -line that needs updated
	 * @param updated - post update of line
	 * @param file - file location 
	 * @throws IOException
	 */
	private void updateLine(String toUpdate, String updated, File file) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String line;
	    String input = "";

	    while ((line = br.readLine()) != null)
	        input += line + System.lineSeparator();

	    input = input.replace(toUpdate, updated);

	    FileOutputStream os = new FileOutputStream(file);
	    os.write(input.getBytes());

	    br.close();
	    os.close();
	}
}
