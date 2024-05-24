package hust.soict.cybersec.project1.stufftesting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.TreeMap;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;

import hust.soict.cybersec.project1.model.AccessLog;
import hust.soict.cybersec.project1.model.IpAddress;
import hust.soict.cybersec.project1.stuff.IPTable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Test_IP_TableView extends Application {
	
	
    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        stage.setTitle("Ip Table");
        IPTable iptable = new IPTable();
        
        Scene scene  = new Scene(iptable,400,300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }



}


