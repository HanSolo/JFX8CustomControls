package jfx8controls.extendingcontrol;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 30.08.13
 * Time: 15:32
 */

public class Demo extends Application {

    @Override public void start(Stage stage) {
        SearchTextBox searchTextBox = new SearchTextBox();
        searchTextBox.setLayoutX(10);
        searchTextBox.setLayoutY(50);

        Button button = new Button("just a button to change focus");

        SlideCheckBox checkBox = new SlideCheckBox();
        //checkBox.setScaleX(0.5);
        //checkBox.setScaleY(0.5);

        VBox pane = new VBox();
        pane.setSpacing(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(searchTextBox, button, checkBox);

        Scene scene = new Scene(pane, 225, 150);

        stage.setScene(scene);
        stage.setTitle("JavaFX Extending control");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
