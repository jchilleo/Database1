package edu.Duquesne.Database.Main;

import java.io.IOException;

import static java.lang.System.out;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class ReadDbFile {
	
	/**
	 * access method to ReadDbFile
	 */
	public void getTables(){
		readDbFile();
	}
	
	/**
	 * Read the mainDbFile for currently store database files.
	 */
	private void readDbFile(){
		BufferedReader br = null;
		out.println("Current Tables:");
		int tableStructure = 0;
		try{
			String tablesLine;
			br = new BufferedReader(new FileReader("src/edu/Duquesne/Database/files/dbFiles.txt"));
			
			while((tablesLine = br.readLine()) !=null){
				out.println(tablesLine);
				
				switch(tableStructure){
				case 1: tableStructure = 0;
						out.println("Table Name: " + tablesLine);
						tableStructure = (tableStructure + 1)%4;
						break;
				case 2: tableStructure = 1;
						out.println("Number of Columns: " + tablesLine);
						tableStructure = (tableStructure + 1)%4;
						break;
				case 3: tableStructure = 2;
						out.println("Length of one record is: " + tablesLine);
						tableStructure = (tableStructure + 1)%4;
						break;
				case 4: tableStructure = 3;
						out.println("List of all column names/lengths" + tablesLine);
						tableStructure = (tableStructure + 1)%4;
						break;
						//length is reference to record
				}
				
			}
		}
		catch (IOException e){e.printStackTrace();}
		finally{
			try{if(br != null)br.close();}
			catch(IOException ex){ex.printStackTrace();}
			}
		}
	/**
	 * append new tables to the main DbFile.
	 * @param fileName - name of the table
	 * @param columnTotal - number of columns
	 * @param recordLength - total record length of all but first 2 columns
	 * @param columnData - column name and individual lengths.
	 */
	public void addToDbFile(String fileName, int columnTotal, int recordLength, String columnData){
		
		/*
		 * Code segement acquired from:
		 * http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
		 */
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/edu/Duquesne/Database/files/dbFiles.txt", true)))) {
		    out.println(fileName);
		    out.println("Number of columns: " + columnTotal);
		    out.println("Total Record Length: " + recordLength);
		    out.println(columnData);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}

	}
	}


