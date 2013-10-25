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

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.KeyBinding;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.SPACE;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;


/**
 * User: hansolo
 * Date: 07.10.13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class SlideCheckBoxBehavior <C extends ButtonBase> extends BehaviorBase<C> {

    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/
    public SlideCheckBoxBehavior(final C button) {
        super(button, BUTTON_BINDINGS);
    }
    public SlideCheckBoxBehavior(final C button, final List<KeyBinding> bindings) {
        super(button, bindings);
    }

    /***************************************************************************
     *                                                                         *
     * Focus change handling                                                   *
     *                                                                         *
     **************************************************************************/

    @Override protected void focusChanged() {
        // If we did have the key down, but are now not focused, then we must
        // disarm the button.
        final ButtonBase button = getControl();
        if (keyDown && !button.isFocused()) {
            keyDown = false;
            button.disarm();
        }
    }

    /***************************************************************************
     *                                                                         *
     * Key event handling                                                      *
     *                                                                         *
     **************************************************************************/

    /**
     * Indicates that a keyboard key has been pressed which represents the
     * event (this could be space bar for example). As long as keyDown is true,
     * we are also armed, and will ignore mouse events related to arming.
     * Note this is made package private solely for the sake of testing.
     */
    private boolean keyDown;

    private static final String PRESS_ACTION   = "Press";
    private static final String RELEASE_ACTION = "Release";

    protected static final List<KeyBinding> BUTTON_BINDINGS = new ArrayList<KeyBinding>();

    static {
        BUTTON_BINDINGS.add(new KeyBinding(SPACE, KEY_PRESSED, PRESS_ACTION));
        BUTTON_BINDINGS.add(new KeyBinding(SPACE, KEY_RELEASED, RELEASE_ACTION));
    }

    @Override protected void callAction(String name) {
        if (!getControl().isDisabled()) {
            if (PRESS_ACTION.equals(name)) {
                keyPressed();
            } else if (RELEASE_ACTION.equals(name)) {
                keyReleased();
            } else {
                super.callAction(name);
            }
        }
    }

    /**
     * This function is invoked when an appropriate keystroke occurs which
     * causes this button to be armed if it is not already armed by a mouse
     * press.
     */
    private void keyPressed() {
        final ButtonBase button = getControl();
        if (!button.isPressed() && !button.isArmed()) {
            keyDown = true;
            button.arm();
        }
    }

    /**
     * Invoked when a valid keystroke release occurs which causes the button
     * to fire if it was armed by a keyPress.
     */
    private void keyReleased() {
        final ButtonBase button = getControl();
        if (keyDown) {
            keyDown = false;
            if (button.isArmed()) {
                button.disarm();
                button.fire();
            }
        }
    }

    /***************************************************************************
     *                                                                         *
     * Mouse event handling                                                    *
     *                                                                         *
     **************************************************************************/

    /**
     * Invoked when a mouse press has occurred over the button. In addition to
     * potentially arming the Button, this will transfer focus to the button
     */
    @Override public void mousePressed(MouseEvent e) {
        final ButtonBase button = getControl();
        super.mousePressed(e);
        // if the button is not already focused, then request the focus
        if (! button.isFocused() && button.isFocusTraversable()) {
            button.requestFocus();
        }

        // arm the button if it is a valid mouse event
        // Note there appears to be a bug where if I press and hold and release
        // then there is a clickCount of 0 on the release, whereas a quick click
        // has a release clickCount of 1. So here I'll check clickCount <= 1,
        // though it should really be == 1 I think.
        boolean valid = (e.getButton() == MouseButton.PRIMARY &&
            ! (e.isMiddleButtonDown() || e.isSecondaryButtonDown() ||
                e.isShiftDown() || e.isControlDown() || e.isAltDown() || e.isMetaDown()));

        if (! button.isArmed() && valid) {
            button.arm();
        }
    }

    /**
     * Invoked when a mouse release has occurred. We determine whether this
     * was done in a manner that would fire the button's action. This happens
     * only if the button was armed by a corresponding mouse press.
     */
    @Override public void mouseReleased(MouseEvent e) {
        // if armed by a mouse press instead of key press, then fire!
        final ButtonBase button = getControl();
        if (! keyDown && button.isArmed()) {
            button.fire();
            button.disarm();
        }
    }

    /**
     * Invoked when the mouse enters the Button. If the Button had been armed
     * by a mouse press and the mouse is still pressed, then this will cause
     * the button to be rearmed.
     */
    @Override public void mouseEntered(MouseEvent e) {
        // rearm if necessary
        final ButtonBase button = getControl();
        super.mouseEntered(e);
        if (! keyDown && button.isPressed()) {
            button.arm();
        }
    }

    /**
     * Invoked when the mouse exits the Button. If the Button is armed due to
     * a mouse press, then this function will disarm the button upon the mouse
     * exiting it.
     */
    @Override public void mouseExited(MouseEvent e) {
        // Disarm if necessary
        final ButtonBase button = getControl();
        super.mouseExited(e);
        if (! keyDown && button.isArmed()) {
            button.disarm();
        }
    }
}
