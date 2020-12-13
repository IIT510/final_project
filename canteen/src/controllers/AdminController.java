package controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import Dao.DBConnect;
import application.DynamicTable;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Account;

public class AdminController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private Pane pane4;
	@FXML
	private Pane pane5;
	@FXML
	private Pane pane6;
	@FXML
	private Pane pane7;
	@FXML
	private Pane pane8;
	@FXML
	private Pane pane9;
	
	@FXML
	private TextField TxtId;
	@FXML
	private TextField TxtBalance;
	@FXML
	private TextField TxtDelId;
	@FXML
	private TextField TxtDelComboId;
	@FXML
	private TextField TxtDelOrderId;
	
	
	@FXML
	private Label LblDelId;
	@FXML
	private Label LblDelOrderId;
	@FXML
	private Label LblDelComboId;
	
	// add account
	@FXML
	private TextField TxtName;
	@FXML
	private TextField TxtBa;
	@FXML
	private TextField TxtRole;
	@FXML
	private TextField TxtPwd;
	@FXML
	private Label LblAddAc;
	

	
	// ADD order
	@FXML
	private TextField TxtOrderName;
	@FXML
	private TextField TxtOrderPrice;
	@FXML
	private TextField TxtOrderStatus;
	@FXML
	private TextField TxtOrderCombo_name;
	@FXML
	private TextField TxtOrderCreated_at;
	@FXML
	private Label LblAddOrder;
	
	// add combo
	@FXML
	private TextField TxtComboName;
	@FXML
	private TextField TxtComboPrice;
	
	//update combo
	@FXML
	private TextField TxtUpdateComboId;
	@FXML
	private TextField TxtUpdateComboPrice;
	
	@FXML
	private Label LblAddCombo;
	
	@FXML
	private Label LblUpdateAc;
	@FXML
	private Label LblUpdateOrder;
	@FXML
	private Label LblUpdateCombo;
	
	@FXML
	private TextField TxtUpdateOrderId;
	@FXML
	private TextField TxtUpdateOrderStatus;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;
	Account account = null;

	public AdminController() {
		conn = new DBConnect();
		account = new Account();
	}

	public void viewAccounts() {

		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		//d.buildData("Select name,balance,role from canteen_accounts");
		d.buildData("Select name,balance,role from canteen_accounts");
	}
	public void viewOrders() {

		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		//d.buildData("Select name,price,status,combo_name,created_at from canteen_orders");
		d.buildData("Select name,price,status,combo_name,created_at from canteen_orders");
	}
	public void viewCombos() {

		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		//d.buildData("Select name,price from canteen_combos");
		d.buildData("Select name,price from canteen_combos");
	}
	public void updateRec() {

		pane3.setVisible(false);
		pane2.setVisible(false);
		pane1.setVisible(true);

	}
	public void updateOrderRec() {

		pane6.setVisible(false);
		pane5.setVisible(false);
		pane4.setVisible(true);

	}
	public void updateComboRec() {

		pane9.setVisible(false);
		pane8.setVisible(false);
		pane7.setVisible(true);

	}
	public void deleteRec() {

		pane1.setVisible(false);
		pane2.setVisible(true);
		pane3.setVisible(false);
	}
	public void deleteOrderRec() {

		pane6.setVisible(false);
		pane5.setVisible(true);
		pane4.setVisible(false);
	}
	public void deleteComboRec() {

		pane9.setVisible(false);
		pane8.setVisible(true);
		pane7.setVisible(false);
	}
	public void addAccountRec() {

		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(true);

	}
	public void addOrderRec() {

		pane4.setVisible(false);
		pane5.setVisible(false);
		pane6.setVisible(true);

	}
	public void addComboRec() {

		pane7.setVisible(false);
		pane8.setVisible(false);
		pane9.setVisible(true);

	}
	public void submitAccount() throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
//		String name = TxtName.getText();
//		double balance = Double.parseDouble(TxtBa.getText());
//		String role = TxtRole.getText();
		String pwd=EncoderByMd5(TxtPwd.getText());
//				
//		account.createAccount(name,balance,role,pwd);
		// INSERT INTO BANK TABLE
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

	//		sql = "insert into canteen_accounts(name,balance,role,pwd) values ('" + TxtName.getText() + "','" + TxtBa.getText()+ "','" + TxtRole.getText()+ "','" + LoginController.EncoderByMd5(TxtPwd.getText())
	//				+ "')";
			sql = "insert into canteen_accounts(name,balance,role,pwd) values ('" + TxtName.getText() + "','" + TxtBa.getText()+ "','" + TxtRole.getText()+ "','" + pwd + "')";
			stmt.executeUpdate(sql);
			System.out.println("account added");
			LblAddAc.setText("account added");
			LblAddAc.setVisible(true);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}

	private String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5=MessageDigest.getInstance("MD5");
	    Base64.Encoder encoder = Base64.getEncoder();

	    byte[] newstr=encoder.encode(md5.digest(str.getBytes("utf-8")));
	   
	    return new String(newstr);
		
	}

	public void submitAddOrder() {

		try {
			// Execute a query
			System.out.println("Add order into the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table
           Date date = new Date();
            SimpleDateFormat f = new SimpleDateFormat();
            System.out.println(f.format(date));
	//		sql = "insert into canteen_orders(name,price,status,combo_name,created_at) values ('" + TxtOrderName.getText() + "','" + TxtOrderPrice.getText()+ "','" + TxtOrderStatus.getText()+ "','" + TxtOrderCombo_name.getText()+ "','" + date.toString()
	//				+ "')";
			sql = "insert into canteen_orders(name,price,status,combo_name,created_at) values ('" + TxtOrderName.getText() + "','" + TxtOrderPrice.getText()+ "','" + TxtOrderStatus.getText()+ "','" + TxtOrderCombo_name.getText()+ "','" + date.toString()
			+ "')";
			stmt.executeUpdate(sql);
			//conn.getConnection().close();
			System.out.println("order added");
			LblAddOrder.setText("order added");
			LblAddOrder.setVisible(true);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}
	
	public void submitAddCombo() {

		// INSERT INTO BANK TABLE
		try {

			stmt = conn.getConnection().createStatement();
			String sql = null;

		//	sql = "insert into canteen_combos(name,price) values ('" + TxtComboName.getText() + "','" + TxtComboPrice.getText()
		//	+ "')";
			sql = "insert into canteen_combos(name,price) values ('" + TxtComboName.getText() + "','" + TxtComboPrice.getText()
					+ "')";
			stmt.executeUpdate(sql);
			//conn.getConnection().close();
			System.out.println("Combo added");
			LblAddCombo.setText("Combo added");
			LblAddCombo.setVisible(true);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}
	public void submitUpdate() {

		System.out.println("Update Submit button pressed");
		System.out.println(TxtId.getText());
		System.out.println(TxtBalance.getText());
		try {
			// Execute a query
			System.out.println("update records");
			stmt = conn.getConnection().createStatement();
			String sql = null;

            sql ="UPDATE canteen_accounts SET balance= "+TxtBalance.getText()+" WHERE id="+TxtId.getText();
			stmt.executeUpdate(sql);
			System.out.println("canteen account Updated");
			LblUpdateAc.setText("canteen account Updated");
			LblUpdateAc.setVisible(true);
		//	conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
	public void submitUpdateOrder() {

		System.out.println("Update Submit button pressed");
		System.out.println(TxtId.getText());
		System.out.println(TxtBalance.getText());
		// INSERT INTO BANK TABLE
		try {
			// Execute a query
			System.out.println("update order");
			System.out.println(TxtUpdateOrderId.getText());
			stmt = conn.getConnection().createStatement();
			String sql = null;

            sql ="UPDATE canteen_orders SET status= '"+TxtUpdateOrderStatus.getText()+"' WHERE id="+TxtUpdateOrderId.getText();
			stmt.executeUpdate(sql);
			System.out.println("canteen order Updated");
			LblUpdateOrder.setText("canteen order Updated");
			LblUpdateOrder.setVisible(true);
		//	conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
	
	public void submitUpdateCombo() {

		System.out.println("Update Submit button pressed");
		System.out.println(TxtId.getText());
		System.out.println(TxtBalance.getText());
		// INSERT INTO BANK TABLE
		try {
			// Execute a query
			System.out.println("update Combo");
			stmt = conn.getConnection().createStatement();
			String sql = null;

            sql ="UPDATE canteen_combos SET price= '"+TxtUpdateComboPrice.getText()+"' WHERE id="+TxtUpdateComboId.getText();
			stmt.executeUpdate(sql);
			System.out.println("canteen Combo Updated");
			LblUpdateCombo.setText("canteen Combo Updated");
			LblUpdateCombo.setVisible(true);
		//	conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public void submitDelete() {
		System.out.println("Del Submit button pressed");
		System.out.println(TxtDelId.getText());
		// INSERT INTO BANK TABLE
		try {
			// Execute a query
			stmt = conn.getConnection().createStatement();
			String sql = null;

            sql ="Delete from canteen_combos  WHERE id="+TxtDelId.getText();
			stmt.executeUpdate(sql);
			LblDelId.setText("delete account success");
			LblDelId.setVisible(true);
			System.out.println("canteen accounts deleted");

		//	conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	public void submitDeleteOrder() {
		System.out.println("Del Submit button pressed");
		System.out.println(TxtDelId.getText());
		// INSERT INTO BANK TABLE
		try {
			// Execute a query
			System.out.println("update records");
			stmt = conn.getConnection().createStatement();
			String sql = null;

            sql ="Delete from canteen_orders  WHERE id="+TxtDelOrderId.getText();
			stmt.executeUpdate(sql);
			LblDelOrderId.setText("delete Combo success");
			LblDelOrderId.setVisible(true);

		//	conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	public void submitDeleteCombo() {
		System.out.println(TxtDelId.getText());
		// INSERT INTO BANK TABLE
		try {
			// Execute a query
			System.out.println("delete Combo");
			stmt = conn.getConnection().createStatement();
			String sql = null;

            sql ="Delete from canteen_combos  WHERE id="+TxtDelComboId.getText();
			stmt.executeUpdate(sql);
			LblDelComboId.setText("delete Combo success");
			LblDelComboId.setVisible(true);
			System.out.println("canteen Combo deleted");

		//	conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public void logout() {
		// System.exit(0);
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/styles.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
}
