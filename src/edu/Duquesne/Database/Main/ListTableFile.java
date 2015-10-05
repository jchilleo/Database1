package edu.Duquesne.Database.Main;

import java.util.ArrayList;

import static java.lang.System.out;

public class ListTableFile extends TableContainer {
	
	private String buffer = " ";
	
	/**
	 * Prints out the current table for the user to see.
	 */
	public void listFile(){
		int index;
		for(ArrayList<String> tableLines: table){
			index = 0;

			if(table.indexOf(tableLines) == 0){
				for(String item : tableLines){
					out.print(" " + bufferAdjust(columnLength.get(index), item) + " |");
					index++;
			}
				out.println();}
			else{
				for(String item : tableLines){
					out.print(" " + bufferAdjust(columnLength.get(index), item) + " |");
					index++;
			}
				out.println();
				}}

	
				out.println();}
		
	/**
	 * Adjust the amount of white space needed for each table entry, by taking the length of the entry subtracted by the max allowance and adding the difference.
	 * @param recordLength - max length allowed for this column.
	 * @param recordString - string to be printed
	 * @return - returns a new string with the added whitespace.
	 */
	private String bufferAdjust(int recordLength, String recordString){
		String tmp = "";
		int stringLength = recordString.length(), bufferAdjustment = recordLength - stringLength;
		if(bufferAdjustment == 0){
			return recordString;
		}
		else{
			for(int i=0; i < bufferAdjustment; i++){
				tmp = tmp.concat(buffer);
			}
			tmp = recordString.concat(tmp);
			return tmp;
		}
	}
}
