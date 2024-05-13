/*
 * Copyright 2024 dncomponents
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

package com.dncomponents.client.components.popover;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.core.JSString;
import org.teavm.jso.dom.html.HTMLElement;

public class Popper  {
    @JSBody(params = {"reference", "toshow", "placementpos"}, script = "return new Popper(reference, toshow, { placement: placementpos});")
    private native static <T> T create(Object reference, Object toshow, Object placementpos);
    JSObject popper;

    public Popper(HTMLElement reference, HTMLElement toshow, String placementpos) {
        this.popper = create(reference, toshow, JSString.valueOf(placementpos));
    }

    public void update() {
        updatePopper(popper);
    }
    public void scheduleUpdate() {
        updatePopper(popper);
    }

    @JSBody(params = {"popper",}, script = "return popper.update();")
    public static native void updatePopper(Object popper);
    @JSBody(params = {"popper",}, script = "return popper.scheduleUpdate();")
    public static native void scheduleUpdate(Object popper);


}
