package hust.soict.cybersec.project1.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import hust.soict.cybersec.project1.model.AccessLog;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class LineChartUtil {

	@SuppressWarnings("unchecked")
	public static void createLineChart (LineChart linechart, String filepath) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Log per hours");
		
        NumberAxis yAxis = new NumberAxis();
        
//		linechart = new LineChart<String, Number>(xAxis, yAxis);
		
		HashMap<Integer, Integer> hm = loadAccessLog(filepath);
		 
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        
        series.setName("Access Log");
        for (int hour : hm.keySet()){
            series.getData().add(new XYChart.Data<String, Number>(Integer.toString((hour + 1) %24), hm.get(hour)));
        }
  
        linechart.getData().add(series);
        linechart.setTitle("Logs per hour");

	}
	

	private static HashMap<Integer, Integer> loadAccessLog(String filepath){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        
        String filePath= filepath;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                AccessLog tempLog = new AccessLog(line);
                if (hm.containsKey(tempLog.getHour())){
                    hm.put(tempLog.getHour(), hm.get(tempLog.getHour()) + 1);
                }
                else{
                    hm.put(tempLog.getHour(), 1);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hm;
    }
	
	
	
	
	
	
	
	
	
	
}
