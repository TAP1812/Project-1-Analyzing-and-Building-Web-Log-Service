package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.TreeMap;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;

import Log.AccessLog;
import Log.IpAddress;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Test_IP_TableView extends Application{

    @FXML
    TableView<IpAddress> ipTable;

    @SuppressWarnings("unchecked")
    public void createTable(){
        ipTable = new TableView<>();

        // Declare all columns match to all attributes
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
        ipTable.getColumns().addAll(ipColumn, countryColumn, frequencyColumn);
    }

    public void addData(){
        String filePath = "E:\\PROJECT1\\WebLogService\\src\\Resources\\access.log";
        File database = new File("E:\\PROJECT1\\Database\\GeoLite2-Country.mmdb");
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

        ipTable.setItems(ips);      
    }
    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        stage.setTitle("Ip Table");
        createTable();
        addData();
        Scene scene  = new Scene(ipTable,400,300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
