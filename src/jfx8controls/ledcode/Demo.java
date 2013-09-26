package jfx8controls.ledcode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfx8controls.ledcss.LedBuilder;


public class Demo extends Application {
    private static int noOfNodes = 0;

    @Override public void start(Stage stage) {
        jfx8controls.ledcss.Led control = LedBuilder.create()
                                       .prefWidth(200)
                                       .prefHeight(200)
                                       .build();

        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane);

        stage.setTitle("JavaFX Led Code");
        stage.setScene(scene);
        stage.show();

        control.setBlinking(true);

        calcNoOfNodes(scene.getRoot());
        System.out.println(noOfNodes + " Nodes in SceneGraph");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


    // ******************** Misc **********************************************
    private static void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) {
                    calcNoOfNodes(n);
                    //System.out.println(n.getStyleClass().toString());
                }
            }
        }
    }
}

