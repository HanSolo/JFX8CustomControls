package jfx8controls.extendingcontrol;

import javafx.scene.control.TextField;


/**
 * Created by
 * User: hansolo
 * Date: 30.08.13
 * Time: 15:27
 */
public class SearchTextBox extends TextField {

    public SearchTextBox(){
        super();
        getStylesheets().add(getClass().getResource("searchtextbox.css").toExternalForm());
        setSkin(new SearchTextBoxSkin(this));
    }
}