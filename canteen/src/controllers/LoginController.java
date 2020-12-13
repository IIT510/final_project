package controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.LoginModel;

public class LoginController {

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Label lblError;

	private LoginModel model;

	public LoginController() {
		model = new LoginModel();
	}

	public void login() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		lblError.setText("");
		String username = this.txtUsername.getText();
		String password = EncoderByMd5(this.txtPassword.getText());
		//String password=this.txtPassword.getText();
		System.out.println(password);

		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setText("Password Cannot be empty or spaces");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setText("User name / Password Cannot be empty or spaces");
			return;
		}

		// authentication check
		checkCredentials(username, password);

	}
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	    MessageDigest md5=MessageDigest.getInstance("MD5");
	    Base64.Encoder encoder = Base64.getEncoder();

	    byte[] newstr=encoder.encode(md5.digest(str.getBytes("utf-8")));
	   
	    return new String(newstr);
	  }
	public void checkCredentials(String username, String password) {
		Boolean isValid = model.getCredentials(username, password);
		if (!isValid) {
			//lblError.setText("User does not exist!");
			lblError.setText("Wrong user or and wrong password!");
			return;
		}
		try {
			AnchorPane root;
			System.out.println(model.getRole());
			if (model.getRole().equals("admin") && isValid) {
				// If user is admin, inflate admin view

				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
				Main.stage.setTitle("Admin View");

			} else {
				// If user is customer, inflate customer view

				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ClientView.fxml"));
				// ***Set user ID acquired from db****
				int user_id = model.getId();  
				ClientController.setUser(username);
				Main.stage.setTitle("Client View");
			}

			Scene scene = new Scene(root);
			Main.stage.setScene(scene);

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}
}