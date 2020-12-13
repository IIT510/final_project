package models;

import Dao.*;
import records.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;


//to creat canteen_accounts1 table with accountrecords

public class AccountDaoModel{
	//Declare DB objects 
	DBConnect conn = null;	
	Statement stmt = null;

	// constructor
	public AccountDaoModel() { 
		//create db object instance
		 conn = new DBConnect();
	}
	
	// CREATE TABLE METHOD
	public void createTable() {
	 try {

	 // Open a connection
	 System.out.println("Connecting to database to create Table...");
	 System.out.println("Connected database successfully...");
	 
	 stmt = conn.getConnection().createStatement();
	 //stmt = conn.connect().createStatement();
	 
	 // Execute create query
	 	 System.out.println("Creating canteen_accounts table in given database...");
	 	
		 String sql = "CREATE TABLE canteen_accounts " + 
		              "( id INTEGER not NULL AUTO_INCREMENT, " + 
		  	        " name VARCHAR(64), " +
				  " balance numeric(8,2), " + 
				  " role VARCHAR(64), " + 
				  "pwd VARCHAR(255),"+
			  " PRIMARY KEY ( id ))";
	 stmt = conn.getConnection().createStatement();
	 stmt.executeUpdate(sql);
	 System.out.println("Created canteen_accounts table in given database..."); 
	 
	 //conn.getConnection().close(); //close db connection 
	}catch (SQLException se) { // Handle errors for JDBC
	 se.printStackTrace();
	}
	 
	     }
	
	//CREATE TABLE1 MEHTOD
		public void createTable1() {
			try {
			 
			 // Open a connection
			 System.out.println("Connecting to database to create Table...");
			 System.out.println("Connected database successfully...");
			 
			// stmt = conn.connect().createStatement();
			 		 		 
			 // Execute create query
			  System.out.println("Creating table in given database...");
			 // String sql = "truncate TABLE jr_dong_tab";
			//  stmt.executeUpdate(sql);
			//  conn.connect().close(); //close db connection 
			 System.out.println("Created table in given database..."); 
			 
			}catch(Exception se) {  // Handle errors for JDBC
				 se.printStackTrace();
			
				
			}
			 
			     }
	
		
			
	// INSERT INTO METHOD
		@SuppressWarnings("unchecked")
		public void insertRecords() {
			try {
			
			FileInputStream fi=new FileInputStream(".\\temp\\AccountRecords.ser");
		 	ObjectInputStream oi=new ObjectInputStream(fi);
		 	Map<Integer,AccountRecords> recorddata=null;
			
			try {
			  // Execute a query
			   // finish string assignment below to insert all array object data 
				// (id, income, pep) into your database table
				System.out.println("Start to deserialize and insert records into the table...");
				
				
				String sql = "INSERT INTO canteen_accounts (  name , balance,role,pwd ) VALUES ( ?, ?,?,?)";
				PreparedStatement stmt0=null;
				
			 	stmt0 = conn.getConnection().prepareStatement(sql);
				Object readobj=oi.readObject();
			 	recorddata = (HashMap<Integer,AccountRecords>)readobj;			// using the java.util.Map&hashmap class to read objects			 				 	
			 						  
			  // Include all object data to the database table
			  
			 	
			 	for (int i = 0; i < recorddata.size(); ++i) {
			 		
					stmt0.setString(1,recorddata.get(i).getName());
					stmt0.setString(2,String.valueOf(recorddata.get(i).getBalance()));
					stmt0.setString(3,recorddata.get(i).getRole());
					stmt0.setString(4,recorddata.get(i).getPwd());					

					stmt0.executeUpdate(); 
			 
			  }
			 	
			 	oi.close();
			 	fi.close();
			 	conn.getConnection().close();
			 	System.out.println("Records inserted! ");
			  } catch (SQLException se) { se.printStackTrace();  }
			  
			}catch (Exception e) {e.printStackTrace();}
			
			 		
		 }// INSERT INTO METHOD
		
		//RETRIEVE RECORDS METHOD
			public ResultSet retrieveRecords() {
				 ResultSet rs = null;

				 try {
					stmt = conn.getConnection().createStatement();
					//String sql = "SELECT * from yourTableName_tab";
					String sql = 
							"select id, name,balance, role, psw from  order by balance desc";

					 rs = stmt.executeQuery(sql);
					 conn.getConnection().close();
					 
										
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 return rs;

				 
				}

	

}


