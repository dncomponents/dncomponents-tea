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

package com.dncomponents.client.components.core.events.row;

import com.dncomponents.client.components.core.events.CustomEvent;
import com.dncomponents.client.dom.DomUtil;
import com.dncomponents.client.dom.handlers.BaseEventListener;
import org.teavm.jso.dom.events.Event;


public interface RowEditingStartedHandler<T> extends BaseEventListener {

    void onRowEditingStarted(RowEditingStartedEvent<T> event);

    String TYPE = "rowEditingStarted";

    @Override
    default void handleEvent(Event evt) {
        onRowEditingStarted(DomUtil.cast(((CustomEvent) evt).getDetail()));
    }


    @Override
    default String getType() {
        return TYPE;
    }
}
