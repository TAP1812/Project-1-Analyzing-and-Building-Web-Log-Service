package hust.soict.cybersec.project1.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.cybersec.project1.Main;
import hust.soict.cybersec.project1.model.IpAddress;
//import hust.soict.cybersec.project1.model.StatusCount;
import hust.soict.cybersec.project1.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.control.Alert.AlertType;

public class DashboardController implements Initializable {
	
	private Main mainApp;
	
	@FXML
	LineChart<String, Number> linechart;
	
	@FXML
	PieChart piechart;
	
	@FXML
	TableView<IpAddress> iptable = new TableView<IpAddress>();
	
//	@FXML 
//	TableView<StatusCount> statustable;
	
	
	public void iptableDetection() {
		ContextMenu contextMenu = new ContextMenu();
	    MenuItem detailsItem = new MenuItem("Detect this IP address");
	    contextMenu.getItems().add(detailsItem);
	    
	    //set action for menu
	    detailsItem.setOnAction(event -> {
            IpAddress selectedIPAddress = iptable.getSelectionModel().getSelectedItem();
            
            if (selectedIPAddress != null) {
            	
            	//filter
            	LogTableUtil.detectByIP(selectedIPAddress.getIp());

            	//change view
            	mainApp.switchToExplorer();

            }
        });
	    iptable.setRowFactory(tv -> {
            TableRow<IpAddress> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && !row.isEmpty()) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });

	}
	
    
    
	
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
	

	public void logout(ActionEvent event) {
//		StatusTable.loadStatusTable(statustable);
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
		
		//load iptable
		IPTableUtil.loadIPTable(iptable, mainApp.getAccesslog());

		//load linechart
		LineChartUtil.createLineChart(this.linechart, mainApp.getAccesslog());
		
		//load piechart
		PieChartUtil.createPieChart(piechart, mainApp.getAccesslog());
		
		//load rule violation table
		
		//load status table
		
		
		iptableDetection();
		
		
	}
}