package controllers;

import models.ComboDaoModel;
import records.ComboRecords;
//import views.LoanView;
//import records.*;
//import models.*;
//import views.*;
//import java.sql.ResultSet;
import java.util.Date;

//create combo table with  combo records data
public class creatComboTable {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComboRecords ar = new ComboRecords();
		ar.readData();		
		ar.processData();
		try {
			 Date d = new Date();
			 System.out.println("Now: "+d); 
			 System.out.println("Sleeping for 5 seconds...");
		     Thread.currentThread();
		     Thread.sleep(5000);
		     System.out.println("Wake up..."); 
		     Date d1 = new Date();
		     System.out.println("Now: "+d1);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
			e.printStackTrace();
		}
		ComboDaoModel dao = new ComboDaoModel();
		dao.createTable();
		dao.insertRecords(); // perform inserts
/*
		ResultSet rs;
		rs = dao.retrieveRecords();
		new LoanView().runView(rs);
		*/

	}

}
