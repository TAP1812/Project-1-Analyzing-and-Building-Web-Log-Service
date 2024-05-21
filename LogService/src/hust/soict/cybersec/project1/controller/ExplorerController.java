package hust.soict.cybersec.project1.controller;

import java.io.File;
import java.io.IOException;

import hust.soict.cybersec.project1.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ExplorerController {
	private Main mainApp;
	
	
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
	
	public void opensetting(ActionEvent event ) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExplorerSetting.fxml"));
		Parent root = loader.load();
		
		Stage setting  = new Stage();
		Scene settingscene = new Scene(root);
		setting.setScene(settingscene);
		setting.show();
		setting.setResizable(false);
		
	}
	
	public void selectfilelog(ActionEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Select the log source");
		
//		File resourceDir = new File("resource/LogResource");
//		filechooser.setInitialDirectory(resourceDir);
		
		java.io.File selectedFile = filechooser.showOpenDialog(stage);
		
		if (selectedFile != null) {
            System.out.println("File được chọn: " + selectedFile.getAbsolutePath());
            // Xử lý file được chọn ở đây
        } else {
            System.out.println("Không có file nào được chọn.");
        }
		
		
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
	public void switchToOverview(ActionEvent event) {
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
}
