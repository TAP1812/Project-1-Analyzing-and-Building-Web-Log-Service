package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.io.FileReader;
import java.util.TreeMap;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;

import Log.AccessLog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class Test_CountryName_PieChart extends Application{

    @FXML
    PieChart countryPieChart;

    public void loadData(){
        ObservableList<PieChart.Data> countryList = FXCollections.observableArrayList();
        String filePath = "E:\\PROJECT1\\WebLogService\\src\\Resources\\access.log";
        TreeMap<String, Integer> tMap = new TreeMap<>();
        File database = new File("E:\\PROJECT1\\Database\\GeoLite2-Country.mmdb");
        try{
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String ip = (new AccessLog(line)).getIP();
                try{
                    DatabaseReader reader = new DatabaseReader.Builder(database).build();
                    InetAddress ipAddress = InetAddress.getByName(ip);
                    CountryResponse response = reader.country(ipAddress);
                    String countryName = response.getCountry().getName();
                    if (tMap.containsKey(countryName)){
                        tMap.put(countryName, tMap.get(countryName) + 1);
                    }
                    else{
                        tMap.put(countryName, 1);
                    }
                }
                catch(IOException | GeoIp2Exception e){ 
                    e.printStackTrace();
                }
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for(String countryName : tMap.keySet()){
            countryList.add(new PieChart.Data(countryName, tMap.get(countryName)));
        }

        countryPieChart = new PieChart(countryList);

    }
    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        stage.setTitle("Pieeeeee");
        loadData();
        countryPieChart.setTitle("Country Name by Ip");
        Scene scene  = new Scene(countryPieChart,400,300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
