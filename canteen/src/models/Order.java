package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
 
import Dao.DBConnect;

/**
 * 
 */
public class Order extends DBConnect {

	DBConnect conn = null;
	Statement stmt = null;
    /**
     * Default constructor
     */
    public Order() {
    }


    /**
     * 
     */
    private String name;

    /**
	 * @return the accountName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the combo_name
	 */
	public String getCombo_name() {
		return combo_name;
	}

	/**
	 * @param combo_name the combo_name to set
	 */
	public void setCombo_name(String combo_name) {
		this.combo_name = combo_name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the created_at
	 */
	public Time getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(Time createdAt) {
		this.createdAt = createdAt;
	}


	/**
     * 
     */
    public String combo_name;

    /**
     * 
     */
    private double price;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private Time createdAt;

    /**
     * 
     */
    public void getOrders() {
        // TODO implement here
    }

    /**
     * 
     */
    public void createOrder() {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void delOrder(int id) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void updateOrder(int id) {
        // TODO implement here
    }

    /**
     * @param id
     */
    
    public List<Order> getOrders(String name) {
    	List<Order> orders = new ArrayList<>();
		String query = "SELECT name,combo_name,price,status,created_at FROM canteen_orders WHERE name = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.print(resultSet.getString("combo_name"));
				Order order = new Order();
				// grab record data by table field name into ClientModel account object
				order.setName(resultSet.getString("name"));
				order.setCombo_name(resultSet.getString("combo_name"));
				order.setPrice(resultSet.getDouble("price"));
				order.setStatus(resultSet.getString("status"));
				order.setCreated_at(resultSet.getTime("created_at"));
				orders.add(order); // add account data to arraylist
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Accounts: " + e);
		}
		return orders; // return arraylist
    }

}