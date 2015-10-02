package edu.Duquesne.Database.Main;

import java.util.ArrayList;

import static java.lang.System.out;

public class ListTableFile extends TableContainer {

	
	
	private String buffer = " ";
	
	public void listFile(){
		//ArrayList<Integer> columnLength = new ArrayList<> (getColumnLengths());
		//ArrayList<ArrayList<String>> table = new ArrayList<>(getTable());
		int index;
		for(ArrayList<String> tableLines: table){
			index = 0;
			/*if(table.indexOf(tableLines) == 0){
				for(String item : tableLines){
					out.print(" " + bufferAdjust(columnLength.get(index), item) + " |");
					index++;
			}
				out.println();}
				*/
		//	else{
				for(String item : tableLines){
					out.print(" " + bufferAdjust(columnLength.get(index), item) + " |");
					index++;
			}
				out.println();
				//}
		}
	}
	
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
