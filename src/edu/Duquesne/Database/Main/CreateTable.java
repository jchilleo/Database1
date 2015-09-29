package edu.Duquesne.Database.Main;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.ArrayList;

import static java.lang.System.out;

public class CreateTable extends TableContainer{

	private ArrayList<ArrayList<String>> table = getTable();
	//TODO add a setTable() call
public void getTableFile(String fileName){
	try {
	      File file = new File(fileName + ".txt");
	      if (file.createNewFile()){
	        out.println(fileName + ".txt is created!");
	      }else{
	        out.println(fileName + ".txt has been loaded.");
	      } 
  	} catch (IOException e) {e.printStackTrace();}
}


private void createTable(String fileName){
	
	ArrayList<String> tableLine = new ArrayList<String>();
	//meta data located in first index of table array list, containing what each index of tableLine
	//has, and their lengths.
	//First two indexes will always represent the tombstone, and table name, all other indexes will
	//reference the column names and lengths. A simple deliminater will extract the column name from
	//the int length.
	tableLine.add("tombstone");
	tableLine.add(fileName);
	
	
}





}
