package edu.Duquesne.Database.Main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

	
public class MainMenu extends TableContainer{
	
	protected static String headers = "";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		MainMenu db = new MainMenu();
		db.dbfCheck();
		db.mainMenu(in);
		in.close();
	}
	
	/**
	 * checks to make sure the main database file is created, it not then it will be created.
	 */
	private void dbfCheck(){	
		try {
			 ReadDbFile rdf = new ReadDbFile();
		      File file = new File("dbFiles.txt");
		      
		      if (file.createNewFile()){
		        out.println("dfFile is created!");
		      }else{
		        out.println("dfFile loaded.");
		      }
		      rdf.getTables();
		      
	    	} catch (IOException e) {e.printStackTrace();}
	}
	/**
	 * first menu to be seen by the user.
	 * @param in - global scanner for reading input.
	 */
	private void mainMenu(Scanner in){
		boolean valid = false;
		int resp = 0;
		do{
		out.println("What would you like to do?");
		out.println("[1] Create or Load a Table");
		out.println("[2] Exit");
		String response = getResponse(in);
		try{
				resp = Integer.parseInt(response); 
				valid = true;}
		catch(Exception e){out.println("Response not valid.");}
		}
		while (valid == false);
		
		if(resp == 1){createOrLoadMenu(in);}
		else if (resp == 2){
			TablePurge tp = new TablePurge();
			tp.purgeDataBase();
		}
		else{
			out.println("System will now purge and exit.");
			TablePurge tp = new TablePurge();
			tp.purgeDataBase();
		}
		
	}
	/**
	 * Second menu where the user can decide to either create or load a database file.
	 * @param in - global scanner for reading input.
	 */
	private void createOrLoadMenu(Scanner in){
		boolean valid = false;
		int resp = 0;
		String response = null, fileName, columnData = "";
		out.println("Would you like to load or create a file?");
		do{
			out.println("What you like to do?");
			out.println("[1] Load an existing file.");
			out.println("[2] Create a new file.");
			out.println("[3] Exit");
			response = getResponse(in);
			try{
					resp = Integer.parseInt(response); 
					valid = true;}
			catch(Exception e){out.println("Response not valid.");}
			}
			while (valid == false);
		
		if(resp == 1){
			out.println("Please enter the name of the file you wish to load from, exluding any extensions.");
			fileName = getResponse(in);
			out.println("The file name you entered is: " + fileName + ".txt"); 
			CreateTable ct = new CreateTable();
			ct.getTableFile(fileName, " ");
			mainMenu2(fileName, in);
		}
		else if(resp == 2){
			boolean notFinished = true;
			out.println("Please enter a name for the new file without extensions.");
			fileName = getResponse(in);
			out.println("The file name you entered is: " + fileName + ".txt"); 
			while(notFinished){
				columnData = getColumnData(columnData, in);
				out.println("Would you like to add more columns?");
				out.println("[1] yes.");
				out.println("[2] no.");
				response = getResponse(in);
				if(Integer.parseInt(response) == 1){}
				else{
					notFinished = false;
				}
			}
			CreateTable ct = new CreateTable();
			ct.getTableFile(fileName, columnData);
		mainMenu2(fileName, in);
		}
		else{
			out.println("System will now purge and exit.");
			TablePurge tp = new TablePurge();
			tp.purgeDataBase();
		}
	}
	/**
	 * Grabs the user input, and places into a string.
	 * @param in - global scanner for reading input.
	 * @return - string of the user input.
	 */
	private String getResponse(Scanner in){
		String response = null;
		response = in.nextLine();
		return response;
	}
	/**
	 * For newly created databases, this will fetch the column headers and record lengths.
	 * @param columnData - string of column headers already captured (blank for first run).
	 * @param in - global scanner for reading user input.
	 * @return - concatenated string of all previous header data with newly added data.
	 */
	private String getColumnData(String columnData, Scanner in){
		out.println("Please enter a column title.");
		String tmp = getResponse(in);
		headers = headers.concat(tmp + " ");
		columnData = columnData.concat(tmp.replaceAll("\\s+", "_")+ " ");
		out.println("Please enter a column length.");
		columnData = columnData.concat(getResponse(in) + " ");
		return columnData;
	}
	/**
	 * Third menu where users can select to add, or mark rows for deletion, in addition they can view the table and lastly exit. 
	 * @param fileName - name of the table 
	 * @param in - global scanner for reading input.
	 */
	private void mainMenu2(String fileName, Scanner in){
		
		String response;
		int resp;
		do{
		out.println("What would you like to do?");
		out.println("[1] Insert a row into the table.");
		out.println("[2] Remove a row from the table.");
		out.println("[3] View the table.");
		out.println("[4] Exit and purge");
		
		response = getResponse(in);
		resp = Integer.parseInt(response);
		if(resp == 1){insertRow(fileName, in);}
		else if(resp == 2){
			if(table.size() > 1){
			do{
				out.println("What row number would you like to remove? (Must be larger than 0 and no longer than total number of rows.");
				response = getResponse(in);
				resp = Integer.parseInt(response);
			}
			while(resp > table.size() || resp < 1);
			removeTableLine rtl = new removeTableLine();
			try {
				rtl.removeEntry(resp);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
			else{out.println("There are no entries that can be removed from this table");}
		}
		else if(resp == 3){
			ListTableFile ltf = new ListTableFile();
			ltf.listFile();
		}
		else{}
		
		}
		while(resp != 4);
		TablePurge tp = new TablePurge();
		tp.purgeDataBase();
	}
	/**
	 * Inserts a row into the database in both the file and the arrayList
	 * @param fileName - name of table
	 * @param in - global scanner for reading user input
	 */
	private void insertRow(String fileName, Scanner in){
		boolean notFinished = true;
		String columnData = "", response;
		while(notFinished){
			out.println("Column Titles: " + headers);
			out.println("Please enter the data for one column.");
			columnData = columnData.concat(getResponse(in).replaceAll("\\s+", "_") + " ");
			out.println("Do you have more column data to enter?");
			out.println("[1] yes.");
			out.println("[2] no.");
			response = getResponse(in);
			if(Integer.parseInt(response) == 1){}
			else{
				notFinished = false;
			}
		}
		
		InsertIntoTable iit = new InsertIntoTable();
		iit.addToTable(fileName, columnData);
	}
	/**
	 * Updates the header data, when the user loads a file rather than creates, so it can be viewed when inserting.
	 * @param header - header data for the columns.
	 */
	public void setHeaders(String header){
		headers = header;
	}
	
	
}
