package models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DBConnect;

public class Account extends DBConnect  {

	DBConnect conn = null;
	Statement stmt = null;

    /**
     * 
     */
    private String name;

	/**
     * 
     */
    private String role;

    /**
     * 
     */
    private double balance;
    
    private String psw;
    
    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//get&set password
	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

    /**
     * @param id
     */
    public void delAccount(int id) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void updateAccount(int id) {
        // TODO implement here
    }
	
    public void createAccount(String name,Double balance,String role,String pwd) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
    	System.out.println("Inserting records into the table...");
		stmt = conn.getConnection().createStatement();
		String sql = null;

		// Include all object data to the database table

		sql = "insert into canteen_accounts(name,balance,role,pwd) values ('" + name + "','" + balance+ "','" + role+ "','" + pwd
				+ "')";
		stmt.executeUpdate(sql);
    	
    }
	public Account() {
		 
	}
    public Account(String name , double balance) {
    	conn = new DBConnect();
		this.name = name;
 		this.balance = balance;
	}

	
	

  
    /*
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	*/
}