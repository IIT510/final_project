package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect  {

	protected Connection connection;
	public Connection getConnection() {
		return connection;
	}
	
	private static String url ="jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
	private static String username = "fp510";
	private static String password = "510";
//	private static String url = "jdbc:mysql://localhost:3306/510fp";
//	private static String username = "root";
//	private static String password = "526563Kk~!";

	public DBConnect() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
	}

	
}
