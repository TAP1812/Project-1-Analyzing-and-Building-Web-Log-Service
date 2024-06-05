package hust.soict.cybersec.project1.util;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class IPTableUtil {
	
	public static void loadIPTable(TableView iptable , String filepath) {
		
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
        
        //
        File database = new File("C:\\Users\\Lenovo\\Downloads\\GeoLite2-Country.mmdb");
        TreeMap<String, Integer> tMap = new TreeMap<String, Integer>();
        ObservableList<IpAddress> ips = FXCollections.observableArrayList();
        try {
            FileReader fileReader = new FileReader(filepath);
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
}
