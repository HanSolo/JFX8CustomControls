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

import javafx.scene.control.CheckBox;


/**
 * User: hansolo
 * Date: 07.10.13
 * Time: 07:44
 * Original version: http://dribbble.com/shots/765858-Switch-gif
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
