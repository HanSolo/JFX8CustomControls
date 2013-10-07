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

import com.sun.javafx.scene.control.skin.LabeledSkinBase;
import javafx.animation.Interpolator;
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
 */
public class SlideCheckBoxSkin extends LabeledSkinBase<SlideCheckBox, SlideCheckBoxBehavior<SlideCheckBox>> {
    private static final double    BOX_WIDTH    = 104;
    private static final double    BOX_HEIGHT   = 42;
    private static final double    THUMB_WIDTH  = 48;
    private static final double    THUMB_HEIGHT = 34;
    private final        StackPane box          = new StackPane();
    private Region   markBox;
    private Region   crossBox;
    private Region   thumb;
    private Timeline timeline;

    private KeyValue kvThumbStartTranslateSelected;
    private KeyValue kvThumbEndTranslateSelected;
    private KeyValue kvMarkStartOpacitySelected;
    private KeyValue kvMarkEndOpacitySelected;
    private KeyValue kvMarkStartScaleXSelected;
    private KeyValue kvMarkEndScaleXSelected;
    private KeyValue kvMarkStartScaleYSelected;
    private KeyValue kvMarkEndScaleYSelected;
    private KeyValue kvMarkStartScaleUpXSelected;
    private KeyValue kvMarkEndScaleUpXSelected;
    private KeyValue kvMarkStartScaleUpYSelected;
    private KeyValue kvMarkEndScaleUpYSelected;
    private KeyValue kvMarkStartScaleDownXSelected;
    private KeyValue kvMarkEndScaleDownXSelected;
    private KeyValue kvMarkStartScaleDownYSelected;
    private KeyValue kvMarkEndScaleDownYSelected;
    private KeyValue kvCrossStartOpacitySelected;
    private KeyValue kvCrossEndOpacitySelected;
    private KeyValue kvCrossStartScaleXSelected;
    private KeyValue kvCrossEndScaleXSelected;
    private KeyValue kvCrossStartScaleYSelected;
    private KeyValue kvCrossEndScaleYSelected;

    private KeyValue kvThumbStartTranslateDeselect;
    private KeyValue kvThumbEndTranslateDeselect;
    private KeyValue kvMarkStartOpacityDeselect;
    private KeyValue kvMarkEndOpacityDeselect;
    private KeyValue kvMarkStartScaleXDeselect;
    private KeyValue kvMarkEndScaleXDeselect;
    private KeyValue kvMarkStartScaleYDeselect;
    private KeyValue kvMarkEndScaleYDeselect;
    private KeyValue kvCrossStartOpacityDeselect;
    private KeyValue kvCrossEndOpacityDeselect;
    private KeyValue kvCrossStartScaleXDeselect;
    private KeyValue kvCrossEndScaleXDeselect;
    private KeyValue kvCrossStartScaleYDeselect;
    private KeyValue kvCrossEndScaleYDeselect;
    private KeyValue kvCrossRotateStart;
    private KeyValue kvCrossRotateEnd;


    public SlideCheckBoxSkin(SlideCheckBox checkbox) {
        super(checkbox, new SlideCheckBoxBehavior<>(checkbox));

        init();
        initGraphics();
        initKeyValues();
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

    private void initKeyValues() {
        // KeyValues selected
        kvThumbStartTranslateSelected = new KeyValue(thumb.translateXProperty(), -24, Interpolator.EASE_BOTH);
        kvThumbEndTranslateSelected   = new KeyValue(thumb.translateXProperty(), 24, Interpolator.EASE_BOTH);

        kvMarkStartOpacitySelected    = new KeyValue(markBox.opacityProperty(), 0, Interpolator.EASE_BOTH);
        kvMarkEndOpacitySelected      = new KeyValue(markBox.opacityProperty(), 1, Interpolator.EASE_BOTH);

        kvMarkStartScaleXSelected     = new KeyValue(markBox.scaleXProperty(), 0, Interpolator.EASE_BOTH);
        kvMarkEndScaleXSelected       = new KeyValue(markBox.scaleXProperty(), 1, Interpolator.EASE_BOTH);

        kvMarkStartScaleYSelected     = new KeyValue(markBox.scaleYProperty(), 0, Interpolator.EASE_BOTH);
        kvMarkEndScaleYSelected       = new KeyValue(markBox.scaleYProperty(), 1, Interpolator.EASE_BOTH);

        kvMarkStartScaleUpXSelected   = new KeyValue(markBox.scaleXProperty(), 1, Interpolator.EASE_BOTH);
        kvMarkEndScaleUpXSelected     = new KeyValue(markBox.scaleXProperty(), 1.5, Interpolator.EASE_BOTH);

        kvMarkStartScaleUpYSelected   = new KeyValue(markBox.scaleYProperty(), 1, Interpolator.EASE_BOTH);
        kvMarkEndScaleUpYSelected     = new KeyValue(markBox.scaleYProperty(), 1.5, Interpolator.EASE_BOTH);

        kvMarkStartScaleDownXSelected = new KeyValue(markBox.scaleXProperty(), 1.5, Interpolator.EASE_BOTH);
        kvMarkEndScaleDownXSelected   = new KeyValue(markBox.scaleXProperty(), 1, Interpolator.EASE_BOTH);

        kvMarkStartScaleDownYSelected = new KeyValue(markBox.scaleYProperty(), 1.5, Interpolator.EASE_BOTH);
        kvMarkEndScaleDownYSelected   = new KeyValue(markBox.scaleYProperty(), 1, Interpolator.EASE_BOTH);

        kvCrossStartOpacitySelected   = new KeyValue(crossBox.opacityProperty(), 1, Interpolator.EASE_BOTH);
        kvCrossEndOpacitySelected     = new KeyValue(crossBox.opacityProperty(), 0, Interpolator.EASE_BOTH);

        kvCrossStartScaleXSelected    = new KeyValue(crossBox.scaleXProperty(), 1, Interpolator.EASE_BOTH);
        kvCrossEndScaleXSelected      = new KeyValue(crossBox.scaleXProperty(), 0, Interpolator.EASE_BOTH);

        kvCrossStartScaleYSelected    = new KeyValue(crossBox.scaleYProperty(), 1, Interpolator.EASE_BOTH);
        kvCrossEndScaleYSelected      = new KeyValue(crossBox.scaleYProperty(), 0, Interpolator.EASE_BOTH);

        // KeyValues deselected
        kvThumbStartTranslateDeselect = new KeyValue(thumb.translateXProperty(), 24);
        kvThumbEndTranslateDeselect   = new KeyValue(thumb.translateXProperty(), -24);

        kvMarkStartOpacityDeselect    = new KeyValue(markBox.opacityProperty(), 1);
        kvMarkEndOpacityDeselect      = new KeyValue(markBox.opacityProperty(), 0);

        kvMarkStartScaleXDeselect     = new KeyValue(markBox.scaleXProperty(), 1);
        kvMarkEndScaleXDeselect       = new KeyValue(markBox.scaleXProperty(), 0);

        kvMarkStartScaleYDeselect     = new KeyValue(markBox.scaleYProperty(), 1);
        kvMarkEndScaleYDeselect       = new KeyValue(markBox.scaleYProperty(), 0);

        kvCrossStartOpacityDeselect   = new KeyValue(crossBox.opacityProperty(), 0);
        kvCrossEndOpacityDeselect     = new KeyValue(crossBox.opacityProperty(), 1);

        kvCrossStartScaleXDeselect    = new KeyValue(crossBox.scaleXProperty(), 0);
        kvCrossEndScaleXDeselect      = new KeyValue(crossBox.scaleXProperty(), 1);

        kvCrossStartScaleYDeselect    = new KeyValue(crossBox.scaleYProperty(), 0);
        kvCrossEndScaleYDeselect      = new KeyValue(crossBox.scaleYProperty(), 1);

        kvCrossRotateStart            = new KeyValue(crossBox.rotateProperty(), 0);
        kvCrossRotateEnd              = new KeyValue(crossBox.rotateProperty(), 360);
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
            KeyFrame kfStart = new KeyFrame(Duration.ZERO, kvThumbStartTranslateSelected,
                                                           kvMarkStartOpacitySelected, kvMarkStartScaleXSelected, kvMarkStartScaleYSelected,
                                                           kvCrossStartOpacitySelected, kvCrossStartScaleXSelected, kvCrossStartScaleYSelected);
            KeyFrame kfEnd   = new KeyFrame(Duration.millis(180), kvThumbEndTranslateSelected,
                                                                  kvMarkEndOpacitySelected, kvMarkEndScaleXSelected, kvMarkEndScaleYSelected,
                                                                  kvCrossEndOpacitySelected, kvCrossEndScaleXSelected, kvCrossEndScaleYSelected);
            KeyFrame kfScaleUpStart   = new KeyFrame(Duration.millis(250), kvMarkStartScaleUpXSelected, kvMarkStartScaleUpYSelected);
            KeyFrame kfScaleUpEnd     = new KeyFrame(Duration.millis(350), kvMarkEndScaleUpXSelected, kvMarkEndScaleUpYSelected);
            KeyFrame kfScaleDownStart = new KeyFrame(Duration.millis(350), kvMarkStartScaleDownXSelected, kvMarkStartScaleDownYSelected);
            KeyFrame kfScaleDownEnd   = new KeyFrame(Duration.millis(500), kvMarkEndScaleDownXSelected, kvMarkEndScaleDownYSelected);

            timeline.getKeyFrames().setAll(kfStart, kfEnd, kfScaleUpStart, kfScaleUpEnd, kfScaleDownStart, kfScaleDownEnd);
            timeline.play();
        } else {
            KeyFrame kfStart       = new KeyFrame(Duration.ZERO, kvThumbStartTranslateDeselect,
                                                  kvMarkStartOpacityDeselect, kvMarkStartScaleXDeselect, kvMarkStartScaleYDeselect,
                                                  kvCrossStartOpacityDeselect, kvCrossStartScaleXDeselect, kvCrossStartScaleYDeselect);
            KeyFrame kfEnd         = new KeyFrame(Duration.millis(180), kvThumbEndTranslateDeselect,
                                                  kvMarkEndOpacityDeselect, kvMarkEndScaleXDeselect, kvMarkEndScaleYDeselect,
                                                  kvCrossEndOpacityDeselect, kvCrossEndScaleXDeselect, kvCrossEndScaleYDeselect);
            KeyFrame kfRotateStart = new KeyFrame(Duration.millis(250), kvCrossRotateStart);
            KeyFrame kfRotateEnd   = new KeyFrame(Duration.millis(750), kvCrossRotateEnd);

            timeline.getKeyFrames().setAll(kfStart, kfEnd, kfRotateStart, kfRotateEnd);
            timeline.play();
            timeline.setOnFinished(actionEvent -> crossBox.setRotate(0));
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
