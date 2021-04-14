import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class GenderComparison extends Application {
    final static String vividity = "Vividity";
    final static String bizarreness = "Bizarreness";
    final static String happiness = "Happiness";
    final static String scariness = "Scariness";
    final static String romance = "Romance";
 
    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Emotional parameters of dreams by gender");       
        yAxis.setLabel("Value");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Male");       
        series1.getData().add(new XYChart.Data(vividity, 25601.34));
        series1.getData().add(new XYChart.Data(bizarreness, 20148.82));
        series1.getData().add(new XYChart.Data(happiness, 10000));
        series1.getData().add(new XYChart.Data(scariness, 35407.15));
        series1.getData().add(new XYChart.Data(romance, 12000));      
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Female");
        series2.getData().add(new XYChart.Data(vividity, 57401.85));
        series2.getData().add(new XYChart.Data(bizarreness, 41941.19));
        series2.getData().add(new XYChart.Data(happiness, 45263.37));
        series2.getData().add(new XYChart.Data(scariness, 117320.16));
        series2.getData().add(new XYChart.Data(romance, 14845.27));  

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1, series2);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
