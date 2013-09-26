package jfx8controls.extendingcontrol;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.Region;


/**
 * Created by
 * User: hansolo
 * Date: 30.08.13
 * Time: 15:27
 */
public class SearchTextBoxSkin extends TextFieldSkin {
    private Region loupe;
    private Region crossButton;

    public SearchTextBoxSkin(final SearchTextBox CONTROL){
        super(CONTROL);

        initGraphics();
        registerListeners();
    }

    private void initGraphics() {
        loupe = new Region();
        loupe.getStyleClass().add("loupe");
        loupe.setFocusTraversable(false);

        crossButton = new Region();
        crossButton.getStyleClass().add("cross-button");
        crossButton.setFocusTraversable(false);

        getChildren().addAll(loupe, crossButton);
    }

    private void registerListeners() {
        crossButton.setOnMouseClicked(event -> getSkinnable().setText(""));
        getSkinnable().textProperty().addListener(observable ->
            crossButton.setVisible(getSkinnable().getText().isEmpty() ? false : true)
        );
        getSkinnable().focusedProperty().addListener(observable -> {
            loupe.setVisible(!getSkinnable().isFocused() && getSkinnable().getText().isEmpty());
            crossButton.setVisible(getSkinnable().isFocused() &&
                                   !getSkinnable().getText().isEmpty() ? true : false);
        });
        getSkinnable().widthProperty().addListener(observable -> {
            double size = loupe.getMaxWidth() < 0 ? 20.8 : loupe.getWidth();
            loupe.setTranslateX(-getSkinnable().getWidth() * 0.5 + size * 0.7);
            crossButton.setTranslateX(getSkinnable().getWidth() * 0.5 - size * 0.7);
        });
        getSkinnable().heightProperty().addListener(observable -> {
            crossButton.setMaxSize(getSkinnable().getHeight() * 0.8,
                                   getSkinnable().getHeight() * 0.8);
            loupe.setMaxSize(getSkinnable().getHeight() * 0.8,
                             getSkinnable().getHeight() * 0.8);
        });
        getSkinnable().sceneProperty().addListener(observable -> {
            loupe.setTranslateX(-getSkinnable().getWidth() * 0.5 + crossButton.getWidth() * 0.7);
            crossButton.setTranslateX(getSkinnable().getWidth() * 0.5 - loupe.getWidth() * 0.7);
        });
    }
}