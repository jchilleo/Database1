package edu.Duquesne.Database.Main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;



public class MainMenu {

	public static void main(String[] args) {
		MainMenu db = new MainMenu();
		db.dbfCheck();
	}
	
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
	
	private void menu(){
		boolean valid = false;
		int resp = 0;
		Scanner in = new Scanner(System.in);
		do{
		out.println("What you like to do?");
		out.println("[1] Create or Load a Table");
		out.println("[2] Exit");
		String response = in.nextLine();
		try{
				resp = Integer.parseInt(response); }
		catch(Exception e){out.println("Response not valid.");}
		}
		while (valid == false);
		
		if(resp == 1){menu2();}
		else{
			TablePurge tp = new TablePurge();
			tp.purgeDataBase();
			
		}
		
	in.close();
	}
	
	private void menu2(){}
}
