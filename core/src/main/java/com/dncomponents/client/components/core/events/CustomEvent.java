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

package com.dncomponents.client.components.core.events;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;
import org.teavm.jso.dom.events.Event;


public abstract class CustomEvent implements Event {

    @JSProperty
    public abstract Object getDetail();

    @JSBody(params = {"type", "options"}, script = "return new CustomEvent(type, {});")
    public static native CustomEvent createCustomEvent(String type, Options options);

    @JSBody(params = {"type", "detail"}, script = "return new CustomEvent(type, {detail:detail});")
    public static native CustomEvent createCustomEvent(String type, Object detail);

}
