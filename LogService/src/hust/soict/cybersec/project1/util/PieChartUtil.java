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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class PieChartUtil {
	public static void createPieChart (PieChart piechart,String filepath) {
	    File database = new File("C:\\Users\\Lenovo\\Downloads\\GeoLite2-Country.mmdb");
	    ObservableList<PieChart.Data> countryList = FXCollections.observableArrayList();
	    TreeMap<String, Integer> tMap = new TreeMap<>();
	    BufferedReader bufferedReader = null;

	    try {
	        FileReader fileReader = new FileReader(filepath);
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
}
