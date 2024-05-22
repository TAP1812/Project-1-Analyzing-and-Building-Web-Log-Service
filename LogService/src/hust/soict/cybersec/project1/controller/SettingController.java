package hust.soict.cybersec.project1.controller;


import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SettingController {

    

//    public void ApplyingSetting(ActionEvent event) throws IOException{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
//        Parent root = loader.load();
//        OverviewController overviewController = loader.getController();
//        overviewController.createTable();
//        overviewController.UpdatingLogTable(selectedColumns());
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public ArrayList<String> selectedColumns(){
        ArrayList<String> selectedColumns = new ArrayList<>();
        for(Node node: choices.getChildren()){
            if (node instanceof CheckBox){
                if (((CheckBox) node).isSelected()){
                    selectedColumns.add(((CheckBox) node).getText());
                }
            }
        }
        return selectedColumns;
    }

    public void ExistingSetting(ActionEvent event){
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }
}
