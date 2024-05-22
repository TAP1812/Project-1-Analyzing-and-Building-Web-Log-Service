package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Log.AccessLog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;


public class Test_Log_TableView extends Application{

    @FXML
    TableView<AccessLog> logTable;

    @SuppressWarnings("unchecked")
    public void createTable(){
        logTable = new TableView<>();
		// Declare each column
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

		// Set cell 
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
		timeColumn.setMaxWidth(500);

		requestColumn.setMinWidth(10);
		requestColumn.setMaxWidth(250);

		statusColumn.setMinWidth(10);
		statusColumn.setMaxWidth(50);

		bytesSentColumn.setMinWidth(10);
		bytesSentColumn.setMaxWidth(50);

		refererColumn.setMinWidth(10);
		refererColumn.setMaxWidth(300);

		// // Define onAction for each row
		// logTable.setRowFactory(tv -> {
		// 	TableRow<AccessLog> row = new TableRow<>();
		// 	row.setOnMouseClicked(event -> {
		// 		if (event.getClickCount() == 2 && ! row.isEmpty()) { // Check for single left click on a populated row
		// 			AccessLog log = row.getItem();
		// 			// Handle the row click event with the selected product
		// 			System.out.println("Row clicked! Product name: " + log.toString());
		// 		}
		// 	});
		// 	return row;
		// });
		// Add to table view
		logTable.getColumns().addAll(typeColumn, IpColumn, remoteIdentColumn, remoteUserColumn, timeColumn, requestColumn, statusColumn, bytesSentColumn, refererColumn, userColumn, logColumn);
	}

    public void UpdatingLogTable(ArrayList<String> choices){
		for(int i=0;i<10;i++){
			if (!choices.contains(logTable.getColumns().get(i).getText())){
				logTable.getColumns().get(i).setVisible(false);
			}
		}
	}
    public void selectLogFile(){
        String filePath = "E:\\PROJECT1\\WebLogService\\src\\Resources\\access.log";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ObservableList<AccessLog> logs = FXCollections.observableArrayList();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logs.add(new AccessLog(line));                
            }
            logTable.setItems(logs);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        stage.setTitle("Logs Table");
        createTable();
        selectLogFile();
        Scene scene  = new Scene(logTable,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
