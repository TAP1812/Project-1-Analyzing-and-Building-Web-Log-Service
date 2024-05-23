package hust.soict.cybersec.project1.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.cybersec.project1.Main;
import hust.soict.cybersec.project1.stuff.DBLineChart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DashboardController implements Initializable {
	private Main mainApp;
	
	
	@FXML
	AnchorPane linechartholder;
	DBLineChart lineChart = new DBLineChart();
	
	public void showlinechart() {
		linechartholder.getChildren().add(lineChart);
	    AnchorPane.setTopAnchor(lineChart, 10.0);
	    AnchorPane.setLeftAnchor(lineChart, 10.0);
	    AnchorPane.setRightAnchor(lineChart, 10.0);
	    AnchorPane.setBottomAnchor(lineChart, 10.0);
	    
	}
	
	
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
		// TODO Auto-generated method stub
		showlinechart();
	}
}
