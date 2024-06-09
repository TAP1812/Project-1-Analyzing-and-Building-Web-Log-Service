package hust.soict.cybersec.project1.controller;

import java.io.IOException;

import hust.soict.cybersec.project1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;




public class LoginController {
	
	@FXML
	TextField usernameField;
	
	
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Main mainApp;
	
	
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
	
	public void register(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fxml/RegisterPage.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void login(ActionEvent event) throws IOException {
		
		String username = usernameField.getText();
		
		if(username.equals("1") ) {
		
			mainApp.switchToWelcome();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid credential");
			alert.setContentText("Username or password is incorrect");
			alert.showAndWait();
			usernameField.setText("");
		}
	}
}
