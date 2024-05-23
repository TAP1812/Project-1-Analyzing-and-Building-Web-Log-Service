package hust.soict.cybersec.project1.stuff;

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

public class DBLineChart extends LineChart<String,Number> {
	
	
	@SuppressWarnings("exports")
	public static CategoryAxis xAxis= new CategoryAxis() ;
	private static NumberAxis yAxis = new NumberAxis();
	
	
	
	public DBLineChart() {
		
		super(xAxis, yAxis);
		// TODO Auto-generated constructor stub
//        xAxis.setLabel("Logs per Hour");
        
        
        HashMap<Integer, Integer> hm = loadAccessLog();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Access Log");
        for (int hour : hm.keySet()){
            series.getData().add(new XYChart.Data<String, Number>(Integer.toString((hour + 1) %24), hm.get(hour)));
        }
        
   
        
        getData().add(series);
        //default characteristic
        setTitle("Logs per hour");
	}

	public DBLineChart(Axis<String> arg0, Axis<Number> arg1, ObservableList<Series<String, Number>> arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}


	
	public HashMap<Integer, Integer> loadAccessLog(){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        String filePath= "D:\\access.log";

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
	
	
	
	
	
	
	
	
	
	public void refresh() {
		
		System.out.println("Refresh");
	}
}
