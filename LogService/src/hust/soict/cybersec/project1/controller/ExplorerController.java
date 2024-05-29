package hust.soict.cybersec.project1.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Predicate;

import hust.soict.cybersec.project1.Main;
import hust.soict.cybersec.project1.model.AccessLog;
import hust.soict.cybersec.project1.util.LogTableUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ExplorerController implements Initializable {
	
	
	
	private Main mainApp;
	

	@FXML
	TableView logtable;
	
	@FXML
	TextField searching;
	
	
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
	
	
	public void filter (String keyword) {
		FilteredList<AccessLog> filteredData = LogTableUtil.getFilteredData();
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

	
	
	@FXML
	public void clickFilter(ActionEvent event ) throws IOException {
		String filtertext = searching.getText();
		filter(filtertext);

	}


	public void openSetting(ActionEvent event ) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExplorerSetting.fxml"));
		Parent root = loader.load();
		Stage setting  = new Stage();
		Scene settingscene = new Scene(root);
		setting.setScene(settingscene);
		setting.show();
		setting.setResizable(false);
		
	}
	
	
	
	public void selectFileLog(ActionEvent event) {
		System.out.println("This function is not available");
//		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//		FileChooser filechooser = new FileChooser();
//		filechooser.setTitle("Select the log source");
//		
//		File resourceDir = new File("src/logsample");
//		filechooser.setInitialDirectory(resourceDir);
//
//		File selectedFile = filechooser.showOpenDialog(stage);
//		if (selectedFile != null) {
//			try{
//				Scanner scanner = new Scanner(selectedFile);
//				ObservableList<AccessLog> logs = FXCollections.observableArrayList();
//				while (scanner.hasNextLine()) {
//					logs.add(new AccessLog(scanner.nextLine()));
//					
//				}
//				
//				logtable.setItems(logs);
//				
//				scanner.close();
//			}
//			catch (Exception e){
//				e.printStackTrace();
//			}
//            
//        } else {
//            System.out.println("Không có file nào được chọn.");
//        }
		
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
		
		
		LogTableUtil.loadLogTable(logtable);
		LogTableUtil.loadAccessLogToTable(logtable,mainApp.getAccesslog());
		
		
	}
}