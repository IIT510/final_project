package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.DBConnect;

public class LoginModel extends DBConnect {
 
	private int id;
	@SuppressWarnings("unused")
	private String role;
	/**
	 * @return the current_user
	 */

	 public static String current_user;
 
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}
		
	public Boolean getCredentials(String username, String password){
		LoginModel.current_user = username;
        	String query = "SELECT * FROM canteen_accounts WHERE name = ? and pwd = ?;";
            try(PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) { 
                 
                	setId(rs.getInt("id"));
                	setRole(rs.getString("role"));
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace();   
             }
			return false;
    }

}//end class