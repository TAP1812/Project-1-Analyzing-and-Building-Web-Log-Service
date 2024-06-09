package hust.soict.cybersec.project1.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    private Label accessLocation;

    @FXML
    private Label errorLocation;

    @FXML
    private PasswordField psPinNumber;

    @FXML
    private TextField tfProfileName;

    @FXML
    void chooseAccessLogPath(ActionEvent event) {

    }

    @FXML
    void chooseErrorLogPath(ActionEvent event) {

    }

    @FXML
    void create(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("/fxml/LoginPage.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
	
	
	
	
}
