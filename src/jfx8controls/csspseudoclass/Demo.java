package jfx8controls.csspseudoclass;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 25.07.13
 * Time: 14:36
 */

public class Demo extends Application {
    private MyCtrl       myCtrl;
    private ToggleButton buttonInteractive;

    @Override public void init() {
        myCtrl            = new MyCtrl();
        buttonInteractive = new ToggleButton("Interactive: false");
        registerListeners();
    }

    private void registerListeners() {
        buttonInteractive.setOnAction(actionEvent -> {
            myCtrl.setInteractive(!myCtrl.isInteractive());
            buttonInteractive.setText("Interactive: " + (myCtrl.isInteractive() ? "true" : "false"));
        });
    }

    @Override public void start(Stage stage) throws Exception {
        VBox pane = new VBox();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.getChildren().addAll(myCtrl, buttonInteractive);
        VBox.setMargin(myCtrl, new Insets(10, 10, 10, 10));

        Scene scene = new Scene(pane);

        stage.setTitle("CSS PseudoClass");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {}

    public static void main(final String[] args) {
        Application.launch(args);
    }
}