/*
 * Copyright (c) 2013. by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class DemoSearchTextBox extends Application {

    @Override public void start(Stage stage) {
        SearchTextBox searchTextBox = new SearchTextBox();
        searchTextBox.setLayoutX(10);
        searchTextBox.setLayoutY(50);

        Button button = new Button("just a button to change focus");
                
        VBox pane = new VBox();
        pane.setSpacing(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(searchTextBox, button);

        Scene scene = new Scene(pane, 225, 150);

        stage.setScene(scene);
        stage.setTitle("JavaFX Extending control");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
