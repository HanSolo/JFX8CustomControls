package jfx8controls.extendingcontrol;

import javafx.scene.control.CheckBox;


/**
 * User: hansolo
 * Date: 07.10.13
 * Time: 07:44
 * To change this template use File | Settings | File Templates.
 */
public class SlideCheckBox extends CheckBox {

    public SlideCheckBox() {
        this("");
    }
    public SlideCheckBox(final String TEXT) {
        super(TEXT);
        getStylesheets().add(getClass().getResource("slidecheckbox.css").toExternalForm());
        setSkin(new SlideCheckBoxSkin(this));
    }
}
