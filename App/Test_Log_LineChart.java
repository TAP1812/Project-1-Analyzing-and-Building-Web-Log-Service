package App;

import Log.AccessLog;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test_Log_LineChart extends Application {

    public HashMap<Integer, Integer> loadAccessLog(){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        String filePath= "E:\\PROJECT1\\WebLogService\\src\\Resources\\access.log";

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
    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        stage.setTitle("Logs Chart");
        HashMap<Integer, Integer> hm = loadAccessLog();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Logs per Hour");

        LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Log Chart");

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Access Log");

        for (int hour : hm.keySet()){
            series.getData().add(new XYChart.Data<String, Number>(Integer.toString((hour + 1) %24), hm.get(hour)));
        }
        lineChart.getData().add(series);

        Scene scene  = new Scene(lineChart,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
