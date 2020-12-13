package controllers;

//import java.sql.ResultSet;
//import java.util.Date;

//import models.DaoModel;
//import records.AccountRecords;
//import views.LoanView;
import records.AccountRecords;
import models.AccountDaoModel;
//import views.*;
//import java.sql.ResultSet;
import java.util.Date;

public class creatAccountTable {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountRecords ar = new AccountRecords();
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
		AccountDaoModel dao = new AccountDaoModel();
		dao.createTable();
		dao.insertRecords(); // perform inserts
/*
		ResultSet rs;
		rs = dao.retrieveRecords();
		new LoanView().runView(rs);
		*/

	}

}
