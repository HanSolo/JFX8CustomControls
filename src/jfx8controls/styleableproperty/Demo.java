package jfx8controls.styleableproperty;

import jfx8controls.csspseudoclass.MyCtrl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 25.07.13
 * Time: 15:10
 */

public class Demo extends Application {
    private MyCtrl myCtrl;
    private ToggleButton buttonStyleable;

    @Override public void init() {
        myCtrl           = new MyCtrl();
        buttonStyleable = new ToggleButton("LIME");
        registerListeners();
    }

    private void registerListeners() {
        buttonStyleable.setOnAction(actionEvent -> {
            if (buttonStyleable.isSelected()) {
                myCtrl.setAreaColor(Color.LIME);
                buttonStyleable.setText("BLUE");
            } else {
                myCtrl.setAreaColor(Color.BLUE);
                buttonStyleable.setText("LIME");
            }
        });
    }

    @Override public void start(Stage stage) throws Exception {
        VBox pane = new VBox();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.getChildren().addAll(myCtrl, buttonStyleable);
        VBox.setMargin(myCtrl, new Insets(10, 10, 10, 10));

        Scene scene = new Scene(pane);

        stage.setTitle("StyleableProperty");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {

    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}