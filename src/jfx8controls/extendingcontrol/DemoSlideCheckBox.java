package jfx8controls.extendingcontrol;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 30.08.13
 * Time: 15:32
 */
public class DemoSlideCheckBox extends Application {
    
    @Override public void start(Stage stage) {
        SlideCheckBox checkBox = new SlideCheckBox();
        
        StackPane pane = new StackPane();        
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(checkBox);

        Scene scene = new Scene(pane, 225, 150);

        stage.setScene(scene);
        stage.setTitle("JavaFX Extending control");
        stage.show();
    }    

    public static void main(String[] args) {
        launch(args);
    }
}
