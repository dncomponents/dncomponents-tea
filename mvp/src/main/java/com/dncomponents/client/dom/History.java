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

package com.dncomponents.client.dom;


import com.dncomponents.client.components.core.events.HandlerRegistration;
import com.dncomponents.client.dom.handlers.PopstateHandler;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class History {

    private static final List<Consumer<String>> handlerList = new ArrayList<>();

    public static HandlerRegistration addValueChangeHandler(Consumer<String> handler) {
        handlerList.add(handler);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                handlerList.remove(handler);
            }
        };
    }

    public static void removeValueChangeHandler(Consumer<String> handler) {
        handlerList.remove(handler);
    }

    public static void clearHandlers() {
        handlerList.clear();
    }


    public static void newItem(String historyToken, boolean issueEvent) {
        if (issueEvent) {
            Window.current().getLocation().setHash(historyToken);
        } else {
            Window.current().getHistory().replaceState(null, null, "#" + historyToken);
        }
    }

    public static void update(String token) {
        handlerList.forEach(e -> e.accept(token.substring(1)));
    }

    static {
        Window.current().addEventListener(PopstateHandler.TYPE, new PopstateHandler() {
            @Override
            public void onPopStateChanged(Event event) {
                final String hash = Window.current().getLocation().getHash();
                update(hash);
            }
        }.asEventListener());
    }

    public static void fireCurrentHistoryState() {
        String hash = Window.current().getLocation().getHash();
        if (hash.isEmpty()) {
            handlerList.forEach(e -> e.accept(""));
        } else {
            update(hash);
        }
    }

}
