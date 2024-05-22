package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OverviewController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	Label nameLabel;
	
	@FXML
	TableView<AccessLog> logtable = new TableView<>();;

	@SuppressWarnings("unchecked")
	public void createTable(){
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
		timeColumn.setMaxWidth(150);

		requestColumn.setMinWidth(10);
		requestColumn.setMaxWidth(250);

		statusColumn.setMinWidth(10);
		statusColumn.setMaxWidth(50);

		bytesSentColumn.setMinWidth(10);
		bytesSentColumn.setMaxWidth(50);

		refererColumn.setMinWidth(10);
		refererColumn.setMaxWidth(300);

		// Define onAction for each row
		logtable.setRowFactory(tv -> {
			TableRow<AccessLog> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && ! row.isEmpty()) { // Check for single left click on a populated row
					AccessLog log = row.getItem();
					// Handle the row click event with the selected product
					System.out.println("Row clicked! Product name: " + log.toString());
				}
			});
			return row;
		});
		// Add to table view
		logtable.getColumns().addAll(typeColumn, IpColumn, remoteIdentColumn, remoteUserColumn, timeColumn, requestColumn, statusColumn, bytesSentColumn, refererColumn, userColumn, logColumn);
	}

	public void UpdatingLogTable(ArrayList<String> choices){
		for(int i=0;i<10;i++){
			if (!choices.contains(logtable.getColumns().get(i).getText())){
				logtable.getColumns().get(i).setVisible(false);
			}
		}
	}
	
	public void opensetting(ActionEvent event ) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Setting.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void selectfilelog(ActionEvent event) {
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Select the log source");
		
		File resourceDir = new File("src");
		filechooser.setInitialDirectory(resourceDir);
		
		File selectedFile = filechooser.showOpenDialog(stage);
		
		if (selectedFile != null) {
			try{
				Scanner scanner = new Scanner(selectedFile);
				ObservableList<AccessLog> logs = FXCollections.observableArrayList();
				while (scanner.hasNextLine()) {
					logs.add(new AccessLog(scanner.nextLine()));
				}
				logtable.setItems(logs);
				scanner.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}

        } else {
            System.out.println("Không có file nào được chọn.");
        }	
	}

	public void swichtostream(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Stream.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	public void swichtodashboard(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void swichtooverview(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomePage.fxml"));
		root = loader.load();
		
		
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchtofeature(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Feature.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void logout(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Log out");
		alert.setHeaderText("You're about log out");
		alert.setContentText("Are you sure?");
		if (alert.showAndWait().get() == ButtonType.OK) {
			root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	public void displayName(String username) {
		nameLabel.setText("Welcome " + username);
	}
}
