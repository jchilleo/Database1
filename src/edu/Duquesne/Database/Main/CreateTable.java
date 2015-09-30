package edu.Duquesne.Database.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

import static java.lang.System.out;

public class CreateTable extends TableContainer{

	private ArrayList<ArrayList<String>> table = getTable();
	//TODO add a setTable() call
	
	public void getTableFile(String fileName, String columnHeadersAndLengths){
		try {
	      File file = new File("/edu/Duquesne/Database/files" + fileName + ".txt");
	      if (file.createNewFile()){
	    	  table.add(gatherColumnMeta(createTable(fileName, file, columnHeadersAndLengths), fileName, columnHeadersAndLengths));
	        out.println(fileName + ".txt is created!");
	      }else{
	    	  loadTable(file);
	        out.println(fileName + ".txt has been loaded.");
	      } } 
		catch (IOException e) {e.printStackTrace();}
		
		
	}

	private ArrayList<String> createTable(String fileName, File file, String columnData){
	
		ArrayList<String> tableLine = new ArrayList<String>();
		
		try{
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.flush();
		bw.write(fileName);
		bw.newLine();
		bw.write(columnData);
		bw.close();
		}
		catch(IOException e){e.printStackTrace();}
		/*meta data located in first index of table array list, containing what each index of tableLine
		* has, and their lengths.
		* First two indexes will always represent the tombstone, and table name, all other indexes will
		* reference the column names and lengths. A simple deliminater will extract the column name from
		* the int length. */
		tableLine.add("Tombstone");
		tableLine.add(fileName);
		return tableLine;
	}
	
	private ArrayList<String> gatherColumnMeta(ArrayList<String> tableLine, String fileName, String columnData){
		
		ArrayList<Integer> columnLengths = getColumnLengths();
		
		columnLengths.add("Tombstone".length());
		columnLengths.add(fileName.length());
		
		String[] cDataStrings = columnData.split("\\s+");
		for(int i = 0; i < (cDataStrings.length - 1); i = i+2){
			tableLine.add(cDataStrings[i]);
			columnLengths.add(Integer.parseInt(cDataStrings[i+1]));
		}
		setColumnLengths(columnLengths);
		return tableLine;
	}
	
	private void loadTable(File file){
		BufferedReader br = null;
		String fileName = null, columnHeadersAndLengths = null;
		try{
			br = new BufferedReader(new FileReader(file));
			if(br.ready())fileName = br.readLine();
			if(br.ready())columnHeadersAndLengths = br.readLine();
		}
		catch (IOException e){e.printStackTrace();}
		finally{
			try{if(br != null)br.close();}
			catch(IOException ex){ex.printStackTrace();}
			}
		ArrayList<String> tableLine = new ArrayList<String>();
		tableLine.add("Tombstone");
		tableLine.add(fileName);
		table.add(gatherColumnMeta(tableLine, fileName, columnHeadersAndLengths));
		extractTable(br, file);
	}
	
	private void extractTable(BufferedReader br, File file){
		ArrayList<Integer> columnLengths = getColumnLengths();
		ArrayList<String> tableLine = new ArrayList<String>();
		boolean skip = false;
		String extraction = null;
		
		try{
			br = new BufferedReader(new FileReader(file));
		if(br.ready()) extraction = br.readLine();
		if(br.ready()) extraction = br.readLine();// skips header which is first two lines.
		if(br.ready()) extraction = br.readLine();// now we are at the start of the data.
		
		while(extraction.length() != 0 || extraction != null){
		
			String[] cDataStrings = extraction.split("\\s+");
			for(int i = 0; i < (cDataStrings.length - 1); i++){
				tableLine.add(cDataStrings[i]);
			}}
		}
		catch (IOException e){e.printStackTrace();}
		finally{
			try{if(br != null)br.close();}
			catch(IOException ex){ex.printStackTrace();}
			}
		
	
	}




}
