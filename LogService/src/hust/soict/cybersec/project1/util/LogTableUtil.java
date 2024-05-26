package hust.soict.cybersec.project1.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import hust.soict.cybersec.project1.model.AccessLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogTableUtil {
	public static void createLogTable(TableView<AccessLog> logtable) {
		
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
		IpColumn.setMaxWidth(500);

		remoteIdentColumn.setMinWidth(10);
		remoteIdentColumn.setMaxWidth(60);

		remoteUserColumn.setMinWidth(10);
		remoteUserColumn.setMaxWidth(60);

		timeColumn.setMinWidth(10);
		timeColumn.setMaxWidth(150);

		requestColumn.setMinWidth(10);
		requestColumn.setMaxWidth(250);

		statusColumn.setMinWidth(10);
		statusColumn.setMaxWidth(50);

		bytesSentColumn.setMinWidth(10);
		bytesSentColumn.setMaxWidth(50);

		refererColumn.setMinWidth(10);
		refererColumn.setMaxWidth(300);
		
		logtable.getColumns().addAll(typeColumn, IpColumn, remoteIdentColumn, remoteUserColumn, timeColumn, requestColumn, statusColumn, bytesSentColumn, refererColumn, userColumn, logColumn);
	
	}
	
	
	public static void loadAccessLogToTable(TableView<AccessLog> logTable, String filePath) {
        ObservableList<AccessLog> logs = FXCollections.observableArrayList();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                AccessLog tempLog = new AccessLog(line);
                logs.add(tempLog);
            }
            logTable.setItems(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
