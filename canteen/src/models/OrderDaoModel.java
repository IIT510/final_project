package models;

import Dao.*;
//import records.*;
//import records.ComboRecords;
import java.sql.SQLException;
import java.sql.Statement;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.io.*;





	
	public class OrderDaoModel{
		//Declare DB objects 
		DBConnect conn = null;	
		Statement stmt = null;

		// constructor
		public OrderDaoModel() { 
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
		 	 System.out.println("Creating canteen_orders table in given database...");
		 	
		 	 String sql = "CREATE TABLE canteen_orders " + 
		              "( id INTEGER not NULL AUTO_INCREMENT, " + 
		              " name VARCHAR(64), " +
		             " price numeric(8,2), " + 
		            " status VARCHAR(64), " +   
		           " combo_name VARCHAR(255), " + 
		          "created_at VARCHAR(128), " +    
			  " PRIMARY KEY ( id ))";
		 stmt = conn.getConnection().createStatement();
		 stmt.executeUpdate(sql);
		 System.out.println("Created canteen_orders table in given database..."); 
		 
		 //conn.getConnection().close(); //close db connection 
		}catch (SQLException se) { // Handle errors for JDBC
		 se.printStackTrace();
		}
		 
		     }
		
	
}

