package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import Dao.DBConnect;
import application.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import models.LoginModel;
import models.Order;
 
public class ClientController implements Initializable {
	
	static int userid;
	public String username;
	Order order;
	
	@FXML
	private TextField TxtName;
	@FXML
	private TextField TxtBa;
	@FXML
	private Button submitTopup;
	@FXML
	private Label LblUpdateAc;
	
	/***** TABLEVIEW  *********************************************************************/

	@FXML
	private TableView<Order> tblOrders;
	@FXML
	private TableColumn<Order, String> combo_name;
	@FXML
	private TableColumn<Order, Number> price;
	@FXML
	private TableColumn<Order, String> name;
	@FXML
	private TableColumn<Order, String> status;
	@FXML
	private TableColumn<Order, Time> createdAt;
	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public void initialize(URL location, ResourceBundle resources) {
		combo_name.setCellValueFactory(new PropertyValueFactory<Order, String>("combo_name"));
		price.setCellValueFactory(new PropertyValueFactory<Order, Number>("price"));
		name.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
		status.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
		createdAt.setCellValueFactory(new PropertyValueFactory<Order, Time>("createdAt"));

		// auto adjust width of columns depending on their content
		tblOrders.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblOrders));

		tblOrders.setVisible(false); // set invisible initially
	}

    public void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }
	public void topup() {
		tblOrders.setVisible(false);
		TxtBa.setVisible(true);
		submitTopup.setVisible(true);
    }
	public void submitTopup(){
		try {
			// Execute a query
			System.out.println("update records");
			
			stmt = conn.getConnection().createStatement();
			String sql = null;
			String sql1 = "Select balance FROM canteen_accounts WHERE name = '"+LoginModel.current_user+"'";
			ResultSet rs = stmt.executeQuery(sql1);
			double balance=0;
			while (rs.next()) {
				System.out.print(rs.getDouble(1));
				 balance = rs.getDouble(1) + Double.parseDouble(TxtBa.getText());
			}
            sql ="UPDATE canteen_accounts SET balance= "+ balance +" WHERE name='"+LoginModel.current_user+"'";
			stmt.executeUpdate(sql);
			System.out.println("canteen account Updated");
			LblUpdateAc.setText("your balance："+ balance);
			LblUpdateAc.setVisible(true);
		//	conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
    
	public void viewOrders() throws IOException {
		TxtBa.setVisible(false);
		submitTopup.setVisible(false);
		System.out.println(LoginModel.current_user);
		tblOrders.getItems().setAll(order.getOrders(LoginModel.current_user)); 
		tblOrders.setVisible(true); 
	}

	/***** End TABLEVIEW  *********************************************************************/

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



	public static void setUser(String username) {
		username = username;
		System.out.println("Welcome name " + username);
	}

	public ClientController() {

		conn = new DBConnect();
		order = new Order();

	}

}
