package edu.Duquesne.Database.Main;

import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Vector;
import java.util.ArrayList;


public class MainMenu {

	public static void main(String[] args) {
		MainMenu db = new MainMenu();
		db.dbfCheck();
		

	}
	
	
	private void dbfCheck(){
		
		try {
   		 
		      File file = new File("dbFiles.txt");
		      
		      if (file.createNewFile()){
		        out.println("dfFile is created!");
		      }else{
		        out.println("dfFile already exists.");
		      }
		      
	    	} catch (IOException e) {
		      e.printStackTrace();
		}
	}
}
