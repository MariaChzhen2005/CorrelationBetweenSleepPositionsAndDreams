import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class GenderPositions extends Application {
    final static String back = "Back";
    final static String front = "Front";
    final static String left = "Left";
    final static String right = "Right";
 
    @Override public void start(Stage stage) {
        stage.setTitle("Sleep Positions by Gender");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Sleep Positions by Gender");
        xAxis.setLabel("Sleep positions");       
        yAxis.setLabel("Percentage of participants");
 
        XYChart.Series male = new XYChart.Series();
        male.setName("Male");       
        male.getData().add(new XYChart.Data(back, 44.4444));
        male.getData().add(new XYChart.Data(front, 32.9218));
        male.getData().add(new XYChart.Data(left, 13.9918));
        male.getData().add(new XYChart.Data(right, 8.642));     
        
        XYChart.Series female = new XYChart.Series();
        female.setName("Female");
        female.getData().add(new XYChart.Data(back, 41.7582));
        female.getData().add(new XYChart.Data(front, 26.8620));
        female.getData().add(new XYChart.Data(left, 22.1001));
        female.getData().add(new XYChart.Data(right, 9.2796)); 

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(male, female);
        stage.setScene(scene);
        stage.show();
        

    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
