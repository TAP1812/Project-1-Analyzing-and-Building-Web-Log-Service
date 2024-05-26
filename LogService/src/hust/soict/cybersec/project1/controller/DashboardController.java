package hust.soict.cybersec.project1.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;

import hust.soict.cybersec.project1.Main;
import hust.soict.cybersec.project1.model.AccessLog;
import hust.soict.cybersec.project1.model.IpAddress;
import hust.soict.cybersec.project1.stuff.DBLineChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DashboardController implements Initializable {
	private Main mainApp;
	
	
	@FXML
	AnchorPane linechartholder;
	DBLineChart lineChart = new DBLineChart();
	
	@FXML
	PieChart piechart;
	
	public void initializePieChart() {
		String filePath = "D:\\access.log";
	    File database = new File("C:\\Users\\Lenovo\\Downloads\\GeoLite2-Country.mmdb");
	    ObservableList<PieChart.Data> countryList = FXCollections.observableArrayList();
	    TreeMap<String, Integer> tMap = new TreeMap<>();
	    BufferedReader bufferedReader = null;

	    try {
	        FileReader fileReader = new FileReader(filePath);
	        bufferedReader = new BufferedReader(fileReader);
	        DatabaseReader reader = new DatabaseReader.Builder(database).build();
	        String line;

	        while ((line = bufferedReader.readLine()) != null) {
	            String ip = (new AccessLog(line)).getIP();
	            try {
	                InetAddress ipAddress = InetAddress.getByName(ip);
	                CountryResponse response = reader.country(ipAddress);
	                String countryName = response.getCountry().getName();

	                tMap.put(countryName, tMap.getOrDefault(countryName, 0) + 1);
	            } catch (IOException | GeoIp2Exception e) {
	                e.printStackTrace();
	            }
	        }

	        for (String countryName : tMap.keySet()) {
	            countryList.add(new PieChart.Data(countryName, tMap.get(countryName)));
	        }

	        piechart.setData(countryList);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	
	
	@FXML
	TableView<IpAddress> iptable = new TableView<IpAddress>();
	
	public void initializeIPTable() {
		TableColumn<IpAddress, String> ipColumn = new TableColumn<>("IP Address");
        TableColumn<IpAddress, String> countryColumn = new TableColumn<>("Country Name");
        TableColumn<IpAddress, Integer> frequencyColumn = new TableColumn<>("Frequency");

        // Set cell value
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ip"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        frequencyColumn.setSortType(TableColumn.SortType.DESCENDING);

        // Set size
        ipColumn.setMaxWidth(500);
        countryColumn.setMaxWidth(500);
        frequencyColumn.setMaxWidth(200);

        // Add all columns to table
        iptable.getColumns().addAll(ipColumn, countryColumn, frequencyColumn);
        
        
        String filePath = "D:\\access.log";
        File database = new File("C:\\Users\\Lenovo\\Downloads\\GeoLite2-Country.mmdb");
        TreeMap<String, Integer> tMap = new TreeMap<String, Integer>();
        ObservableList<IpAddress> ips = FXCollections.observableArrayList();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                AccessLog temp = new AccessLog(line);
                String ipTemp = (new IpAddress(temp.getIP())).getIp();
                if (tMap.containsKey(ipTemp)){
                    tMap.put(ipTemp, tMap.get(ipTemp) + 1);
                }
                else{
                    tMap.put(ipTemp, 1);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(String ip : tMap.keySet()){
            ips.add(new IpAddress(ip, tMap.get(ip)));
        }
        try{
            DatabaseReader reader = new DatabaseReader.Builder(database).build();
            for(int i = 0; i < ips.size(); i++){
                InetAddress ipAddress = InetAddress.getByName(ips.get(i).getIp());
                CountryResponse response = reader.country(ipAddress);
                ips.get(i).setCountryName(response.getCountry().getName());
            }
        }
        catch(IOException | GeoIp2Exception e){ 
            e.printStackTrace();
        }

        iptable.setItems(ips);
        
        
        
	}
	
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
		initializeIPTable();
		initializePieChart();
	}
}
