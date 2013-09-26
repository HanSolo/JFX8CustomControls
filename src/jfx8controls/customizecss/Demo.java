package jfx8controls.customizecss;

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
 * Time: 07:55
 */

public class Demo extends Application {

    @Override public void start(Stage stage) {
        Button styledButton = new Button("My Button");
        styledButton.getStyleClass().add("my-button");

        Button stdButton = new Button("Std. Button");

        VBox pane = new VBox();
        pane.setSpacing(20);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(styledButton, stdButton);

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("JavaFX Custom CSS style");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}