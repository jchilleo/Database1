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

	
	//TODO add a setTable() call
	
	/**
	 * Create or load an existing database table file.
	 * @param fileName Name of the database file with no extension.
	 * @param columnHeadersAndLengths String containing the column headers and their lengths separted by spaces. 
	 */
	public void getTableFile(String fileName, String columnHeadersAndLengths){
		ArrayList<ArrayList<String>> table = new ArrayList<>(getTable());
		try {
	      File file = new File("src/edu/Duquesne/Database/files/" + fileName + ".txt");
	      if (file.createNewFile()){
	    	  table.add(gatherColumnMeta(createTable(fileName, file, columnHeadersAndLengths), fileName, columnHeadersAndLengths, true));
	        out.println(fileName + ".txt is created!");
	      }else{
	    	  loadTable(file);
	        out.println(fileName + ".txt has been loaded.");
	      } } 
		catch (IOException e) {e.printStackTrace();}
		setTable(new ArrayList<>(table));
		
		
	}
	/**
	 * Write to the file the fileName and the meta data.
	 * @param fileName - Name of the file without extensions
	 * @param file - File data location.
	 * @param columnData - String containing all the column headers.
	 * @return ArrayList for first index partially filled. 
	 */
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
		return (new ArrayList<>(tableLine));
	}
	/**
	 * Parse column header data to separate and sort string names from int lengths.
	 * @param tableLine - partially field first index arrayList.
	 * @param fileName	- Name of the file with no extension.
	 * @param columnData - String to be parsed.
	 * @return - filled arraylist ready to be added to the main table.
	 */
	private ArrayList<String> gatherColumnMeta(ArrayList<String> tableLine, String fileName, String columnData, boolean newlyCreated){
		int ctotal= 0, rlength = 0;
		ArrayList<Integer> columnLengths = new ArrayList<Integer>();
		
		columnLengths.add("Tombstone".length());
		columnLengths.add(fileName.length());
		
		String[] cDataStrings = columnData.split("\\s+");
		for(int i = 0; i < (cDataStrings.length - 1); i = i+2){
			tableLine.add(cDataStrings[i]);
			columnLengths.add(Integer.parseInt(cDataStrings[i+1]));
			rlength= rlength + Integer.parseInt(cDataStrings[i+1]);
			ctotal++;
		}
		setColumnTotal(ctotal);
		setRecordLength(rlength);
		setColumnLengths(new ArrayList<>(columnLengths));
		if(newlyCreated)
			{ReadDbFile rdbf = new ReadDbFile();
			rdbf.addToDbFile(fileName, getColumnTotal(), getRecordLength(),columnData);}
		return (new ArrayList<>(tableLine));
	}
	
	/**
	 * Extracts header/meta data from a file and loads into database.
	 * @param file - path location of the file.
	 */
	private void loadTable(File file){
		ArrayList<ArrayList<String>> table = new ArrayList<>(getTable());
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
		table.add(gatherColumnMeta(tableLine, fileName, columnHeadersAndLengths, false));
		setTable(new ArrayList<>(table));
		extractTable(file);
		
	}
	
	/**
	 * Extracts the main parts of the database from the file.
	 * @param file - file path location for the database file.
	 */
	private void extractTable(File file){
		ArrayList<ArrayList<String>> table = new ArrayList<>(getTable());
		BufferedReader br = null;
		ArrayList<String> tableLine = new ArrayList<String>();
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
			}
			table.add(tableLine);
			if(br.ready()) extraction = br.readLine();
			}
		}
		catch (IOException e){e.printStackTrace();}
		finally{
			try{if(br != null)br.close();}
			catch(IOException ex){ex.printStackTrace();}
			}
		setTable(new ArrayList<>(table));
	
	}




}
