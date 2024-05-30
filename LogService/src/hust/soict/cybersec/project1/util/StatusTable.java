package hust.soict.cybersec.project1.util;

import java.util.Map;
import java.util.stream.Collectors;

import hust.soict.cybersec.project1.model.AccessLog;
import hust.soict.cybersec.project1.model.StatusCount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatusTable {
	
	@SuppressWarnings("unchecked")
	public static void loadStatusTable (TableView<StatusCount> statustable) {
		
		//load columns
		TableColumn<StatusCount, String> statuscodeColumn = new TableColumn<>("StatusCode");
		statuscodeColumn.setCellValueFactory(new PropertyValueFactory<>("statuscode"));

        TableColumn<StatusCount, Integer> countColumn = new TableColumn<>("Count");
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        statustable.getColumns().addAll(statuscodeColumn, countColumn);
        
      //load data to table
        ObservableList<StatusCount> statusCountData = countStatusCode(LogTableUtil.getLogs());
        
        statustable.setItems(statusCountData);

        
	}
	

    private static ObservableList<StatusCount> countStatusCode(ObservableList<AccessLog> logData) {
        Map<Integer, Long> statusCountMap = logData.stream()
        		.collect(Collectors.groupingBy(log -> Integer.valueOf(log.getStatusCode()), Collectors.counting()));

        ObservableList<StatusCount> statusCounts = FXCollections.observableArrayList();
        statusCountMap.forEach((status, count) ->
               	statusCounts.add(new StatusCount(status, count.intValue()))
        );
        return statusCounts;
    }
}
