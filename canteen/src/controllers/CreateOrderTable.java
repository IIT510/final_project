package controllers;



import models.OrderDaoModel;
//import records.ComboRecords;
//import views.LoanView;
//import records.*;
//import models.*;
//import views.*;
//import java.sql.ResultSet;
//import java.util.Date;

public class CreateOrderTable {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OrderDaoModel dao = new OrderDaoModel();
		dao.createTable();
		//dao.insertRecords(); // perform inserts
/*
		ResultSet rs;
		rs = dao.retrieveRecords();
		new LoanView().runView(rs);
		*/

	}

}
