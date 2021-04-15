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
        bc.setTitle("Summary");
        xAxis.setLabel("Emotional parameters of dreams by gender");       
        yAxis.setLabel("Value");
 
        XYChart.Series male = new XYChart.Series();
        male.setName("Male");       
        male.getData().add(new XYChart.Data(vividity, 5.7366));
        male.getData().add(new XYChart.Data(bizarreness, 6.535));
        male.getData().add(new XYChart.Data(happiness, 5.5926));
        male.getData().add(new XYChart.Data(scariness, 3.247));
        male.getData().add(new XYChart.Data(romance, 4.2428));      
        
        XYChart.Series female = new XYChart.Series();
        female.setName("Female");
        female.getData().add(new XYChart.Data(vividity, 6.4628));
        female.getData().add(new XYChart.Data(bizarreness, 6.8877));
        female.getData().add(new XYChart.Data(happiness, 5.5861));
        female.getData().add(new XYChart.Data(scariness, 3.9109));
        female.getData().add(new XYChart.Data(romance, 4.2906));  

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(male, female);
        stage.setScene(scene);
        stage.show();
        

    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
