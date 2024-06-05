package hust.soict.cybersec.project1.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.cybersec.project1.Main;
import hust.soict.cybersec.project1.model.AccessLog;
import hust.soict.cybersec.project1.util.LogTableUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class StreamController implements Initializable  {
	
	private Main mainApp;
	private String filepath;
	
	@FXML 
	TableView<AccessLog> logtable;
	

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
    }
	
	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Log out");
		alert.setHeaderText("You're about log out");
		alert.setContentText("Are you sure?");
		if (alert.showAndWait().get() == ButtonType.OK) {
			mainApp.logout();
		}
	}
	public void switchToWelcome(ActionEvent event) {
		mainApp.switchToOverview();
	}
	public void switchToOverview(ActionEvent event) {
		mainApp.switchToOverview();
	}
	public void switchToDashboard(ActionEvent event) {
		mainApp.switchToDashboard();
	}
	public void switchToStream(ActionEvent event) {
		mainApp.switchToStream();
	}
	public void switchToExplorer(ActionEvent event) {
		mainApp.switchToExplorer();
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		LogTableUtil.loadLogTable(logtable);
		LogTableUtil.loadAccessLogToTable(logtable,mainApp.getAccesslog());
		
	}
}
