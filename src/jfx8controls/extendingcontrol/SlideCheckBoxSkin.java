package jfx8controls.extendingcontrol;

import com.sun.javafx.scene.control.skin.LabeledSkinBase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.NodeOrientation;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


/**
 * User: hansolo
 * Date: 07.10.13
 * Time: 07:45
 * To change this template use File | Settings | File Templates.
 */
public class SlideCheckBoxSkin extends LabeledSkinBase<SlideCheckBox, SlideCheckBoxBehavior<SlideCheckBox>> {
    private static final double    BOX_WIDTH    = 104;
    private static final double    BOX_HEIGHT   = 42;
    private static final double    THUMB_WIDTH  = 48;
    private static final double    THUMB_HEIGHT = 34;
    private final        StackPane box          = new StackPane();
    private              Region    markBox;
    private              Region    crossBox;
    private              Region    thumb;
    private              Timeline  timeline;


    public SlideCheckBoxSkin(SlideCheckBox checkbox) {
        super(checkbox, new SlideCheckBoxBehavior<>(checkbox));

        init();
        initGraphics();
        registerListeners();
    }

    private void init() {
        timeline = new Timeline();
    }

    private void initGraphics() {
        markBox = new Region();
        markBox.getStyleClass().setAll("mark");
        markBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        markBox.setTranslateX(-27);

        crossBox = new Region();
        crossBox.getStyleClass().setAll("cross");
        crossBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        crossBox.setTranslateX(27);

        thumb = new Region();
        thumb.getStyleClass().setAll("thumb");
        thumb.setMinSize(48, 34);
        thumb.setPrefSize(48, 34);
        thumb.setMaxSize(48, 34);

        if (getSkinnable().isSelected()) {
            crossBox.setOpacity(0);
            thumb.setTranslateX(24);
        } else {
            markBox.setOpacity(0);
            thumb.setTranslateX(-24);
        }

        box.getStyleClass().setAll("box");
        box.getChildren().addAll(markBox, crossBox, thumb);
        updateChildren();
    }

    private void registerListeners() {
        getSkinnable().selectedProperty().addListener(observable -> toggle());
    }

    @Override protected void updateChildren() {
        super.updateChildren();
        if (box != null) { getChildren().add(box); }
    }

    @Override protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefWidth(height, topInset, rightInset, bottomInset, leftInset) + snapSize(box.minWidth(-1));
    }
    @Override protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return Math.max(super.computeMinHeight(width - box.minWidth(-1), topInset, rightInset, bottomInset, leftInset), topInset + box.minHeight(-1) + bottomInset);
    }
    @Override protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefWidth(height, topInset, rightInset, bottomInset, leftInset) + snapSize(box.prefWidth(-1));
    }
    @Override protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return Math.max(super.computePrefHeight(width - box.prefWidth(-1), topInset, rightInset, bottomInset, leftInset), topInset + box.prefHeight(-1) + bottomInset);
    }

    @Override protected void layoutChildren(final double x, final double y, final double w, final double h) {
        final SlideCheckBox checkBox = getSkinnable();
        final double computeWidth    = Math.max(checkBox.prefWidth(-1), checkBox.minWidth(-1));
        final double labelWidth      = Math.min( computeWidth - BOX_WIDTH, w - snapSize(BOX_WIDTH)) + 100;
        final double labelHeight     = Math.min(checkBox.prefHeight(labelWidth), h);
        final double maxHeight       = Math.max(BOX_HEIGHT, labelHeight);
        final double xOffset         = computeXOffset(w, labelWidth + BOX_WIDTH, checkBox.getAlignment().getHpos()) + x;
        final double yOffset         = computeYOffset(h, maxHeight, checkBox.getAlignment().getVpos()) + x;

        layoutLabelInArea(xOffset + BOX_WIDTH, yOffset, labelWidth, maxHeight, checkBox.getAlignment());
        thumb.resize(THUMB_WIDTH, THUMB_HEIGHT);
        box.resize(BOX_WIDTH, BOX_HEIGHT);
        positionInArea(box, xOffset, yOffset, BOX_WIDTH, maxHeight, 0, checkBox.getAlignment().getHpos(), checkBox.getAlignment().getVpos());
    }

    private void toggle() {
        if (getSkinnable().isSelected()) {
            // switch to deselected
            KeyValue kvThumbStartTranslate = new KeyValue(thumb.translateXProperty(), -24);
            KeyValue kvThumbEndTranslate   = new KeyValue(thumb.translateXProperty(), 24);

            KeyValue kvMarkStartOpacity    = new KeyValue(markBox.opacityProperty(), 0);
            KeyValue kvMarkEndOpacity      = new KeyValue(markBox.opacityProperty(), 1);

            KeyValue kvMarkStartScaleX     = new KeyValue(markBox.scaleXProperty(), 0);
            KeyValue kvMarkEndScaleX       = new KeyValue(markBox.scaleXProperty(), 1);

            KeyValue kvMarkStartScaleY     = new KeyValue(markBox.scaleYProperty(), 0);
            KeyValue kvMarkEndScaleY       = new KeyValue(markBox.scaleYProperty(), 1);

            KeyValue kvCrossStartOpacity   = new KeyValue(crossBox.opacityProperty(), 1);
            KeyValue kvCrossEndOpacity     = new KeyValue(crossBox.opacityProperty(), 0);

            KeyValue kvCrossStartScaleX    = new KeyValue(crossBox.scaleXProperty(), 1);
            KeyValue kvCrossEndScaleX      = new KeyValue(crossBox.scaleXProperty(), 0);

            KeyValue kvCrossStartScaleY    = new KeyValue(crossBox.scaleYProperty(), 1);
            KeyValue kvCrossEndScaleY      = new KeyValue(crossBox.scaleYProperty(), 0);

            KeyFrame kfStart = new KeyFrame(Duration.ZERO, kvThumbStartTranslate,
                                                           kvMarkStartOpacity, kvMarkStartScaleX, kvMarkStartScaleY,
                                                           kvCrossStartOpacity, kvCrossStartScaleX, kvCrossStartScaleY);
            KeyFrame kfEnd   = new KeyFrame(Duration.millis(200), kvThumbEndTranslate,
                                                                  kvMarkEndOpacity, kvMarkEndScaleX, kvMarkEndScaleY,
                                                                  kvCrossEndOpacity, kvCrossEndScaleX, kvCrossEndScaleY);

            timeline.getKeyFrames().setAll(kfStart, kfEnd);
            timeline.play();
        } else {
            // switch to selected
            KeyValue kvThumbStartTranslate = new KeyValue(thumb.translateXProperty(), 24);
            KeyValue kvThumbEndTranslate   = new KeyValue(thumb.translateXProperty(), -24);

            KeyValue kvMarkStartOpacity    = new KeyValue(markBox.opacityProperty(), 1);
            KeyValue kvMarkEndOpacity      = new KeyValue(markBox.opacityProperty(), 0);

            KeyValue kvMarkStartScaleX     = new KeyValue(markBox.scaleXProperty(), 1);
            KeyValue kvMarkEndScaleX       = new KeyValue(markBox.scaleXProperty(), 0);

            KeyValue kvMarkStartScaleY     = new KeyValue(markBox.scaleYProperty(), 1);
            KeyValue kvMarkEndScaleY       = new KeyValue(markBox.scaleYProperty(), 0);

            KeyValue kvCrossStartOpacity   = new KeyValue(crossBox.opacityProperty(), 0);
            KeyValue kvCrossEndOpacity     = new KeyValue(crossBox.opacityProperty(), 1);

            KeyValue kvCrossStartScaleX    = new KeyValue(crossBox.scaleXProperty(), 0);
            KeyValue kvCrossEndScaleX      = new KeyValue(crossBox.scaleXProperty(), 1);

            KeyValue kvCrossStartScaleY    = new KeyValue(crossBox.scaleYProperty(), 0);
            KeyValue kvCrossEndScaleY      = new KeyValue(crossBox.scaleYProperty(), 1);

            KeyFrame kfStart = new KeyFrame(Duration.ZERO, kvThumbStartTranslate,
                                            kvMarkStartOpacity, kvMarkStartScaleX, kvMarkStartScaleY,
                                            kvCrossStartOpacity, kvCrossStartScaleX, kvCrossStartScaleY);
            KeyFrame kfEnd   = new KeyFrame(Duration.millis(200), kvThumbEndTranslate,
                                            kvMarkEndOpacity, kvMarkEndScaleX, kvMarkEndScaleY,
                                            kvCrossEndOpacity, kvCrossEndScaleX, kvCrossEndScaleY);

            timeline.getKeyFrames().setAll(kfStart, kfEnd);
            timeline.play();
        }
    }

    private static double computeXOffset(double width, double contentWidth, HPos hpos) {
        switch(hpos) {
            case LEFT:
                return 0;
            case CENTER:
                return (width - contentWidth) / 2;
            case RIGHT:
                return width - contentWidth;
        }
        return 0;
    }
    private static double computeYOffset(double height, double contentHeight, VPos vpos) {
        switch(vpos) {
            case TOP:
                return 0;
            case CENTER:
                return (height - contentHeight) / 2;
            case BOTTOM:
                return height - contentHeight;
            default:
                return 0;
        }
    }
}
