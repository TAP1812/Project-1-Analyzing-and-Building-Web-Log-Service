package hust.soict.cybersec.project1.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import hust.soict.cybersec.project1.model.AccessLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogTableUtil {
	
	private static ObservableList<AccessLog> logs = FXCollections.observableArrayList();
	
	
	private static FilteredList<AccessLog> filteredData;
	
	
	
	public static FilteredList<AccessLog> getFilteredData() {
		
		if (filteredData == null) {
			return filteredData = new FilteredList<>(logs, l -> true);
		}
		return filteredData;
	}
	
	public static void detectByIP (String keyword) {
		
		if (filteredData == null) {

			System.out.println("Log source is not loaded");
		}
		
		filteredData.setPredicate(accesslog -> {
			
            if (keyword == null || keyword.isEmpty()) {
                return true;
            }
            if (accesslog.getIP().contains(keyword)) {
                return true;
            } else {
                return false;
            }
        });
	}

	public static void filter (String keyword) {
		
		if (filteredData == null) {

			System.out.println("null filtered list");
		}
		filteredData.setPredicate(accesslog -> {
			
            if (keyword == null || keyword.isEmpty()) {
                return true;
            }
            if (accesslog.getType().contains(keyword)) {
                return true;

            } else {
                return false;
            }
        });
	}


	public static ObservableList<AccessLog> getLogs() {
		return logs;
	}


	
	public static void loadLogTable(TableView<AccessLog> logtable) {
		
		TableColumn<AccessLog, String> typeColumn = new TableColumn<>("Type");
		TableColumn<AccessLog, String> IpColumn = new TableColumn<>("IP");
		TableColumn<AccessLog, String> remoteIdentColumn = new TableColumn<>("User Ident");
		TableColumn<AccessLog, String> remoteUserColumn = new TableColumn<>("User Name");
		TableColumn<AccessLog, String> timeColumn = new TableColumn<>("Timestamp");
		TableColumn<AccessLog, String> requestColumn = new TableColumn<>("Request Message");
		TableColumn<AccessLog, Integer> statusColumn = new TableColumn<>("Status");
		TableColumn<AccessLog, Integer> bytesSentColumn = new TableColumn<>("Bytes");
		TableColumn<AccessLog, String> refererColumn = new TableColumn<>("Referer");
		TableColumn<AccessLog, String> userColumn = new TableColumn<>("User Agent");
		TableColumn<AccessLog, String> logColumn = new TableColumn<>("Log");
		
		
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		IpColumn.setCellValueFactory(new PropertyValueFactory<>("IP"));
		remoteIdentColumn.setCellValueFactory(new PropertyValueFactory<>("remoteIdent"));
		remoteUserColumn.setCellValueFactory(new PropertyValueFactory<>("remoteUser"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
		userColumn.setCellValueFactory(new PropertyValueFactory<>("userAgent"));
		requestColumn.setCellValueFactory(new PropertyValueFactory<>("requestUrl"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusCode"));
		bytesSentColumn.setCellValueFactory(new PropertyValueFactory<>("bytesSent"));
		refererColumn.setCellValueFactory(new PropertyValueFactory<>("referer"));
		logColumn.setCellValueFactory(new PropertyValueFactory<>("logEntry"));

		// Format each column
		IpColumn.setMinWidth(10);
		IpColumn.setPrefWidth(70);
		IpColumn.setMaxWidth(500);

		remoteIdentColumn.setMinWidth(10);
		remoteIdentColumn.setPrefWidth(30);
		remoteIdentColumn.setMaxWidth(60);

		remoteUserColumn.setMinWidth(10);
		remoteUserColumn.setPrefWidth(30);
		remoteUserColumn.setMaxWidth(60);

		timeColumn.setMinWidth(10);
		timeColumn.setPrefWidth(200);
		timeColumn.setMaxWidth(200);

		requestColumn.setMinWidth(10);
		requestColumn.setPrefWidth(300);
		requestColumn.setMaxWidth(400);

		statusColumn.setMinWidth(30);
		statusColumn.setPrefWidth(30);
		statusColumn.setMaxWidth(50);

		bytesSentColumn.setMinWidth(30);
		bytesSentColumn.setPrefWidth(30);
		bytesSentColumn.setMaxWidth(50);

		refererColumn.setMinWidth(10);
		refererColumn.setPrefWidth(10);
		refererColumn.setMaxWidth(300);
		
		logtable.getColumns().addAll(typeColumn, IpColumn, remoteIdentColumn, remoteUserColumn, timeColumn, requestColumn, statusColumn, bytesSentColumn, refererColumn, userColumn, logColumn);
	
	}
	
	
	public static void loadAccessLogToTable(TableView<AccessLog> logTable, String filePath) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                AccessLog tempLog = new AccessLog(line);
                logs.add(tempLog);
            }
            filteredData = new FilteredList<>(logs, l -> true);
            logTable.setItems(filteredData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
