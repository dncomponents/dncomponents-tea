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

package com.dncomponents.client.components.core;

import com.dncomponents.client.components.core.events.HandlerRegistration;
import com.dncomponents.client.components.core.events.HasEnabled;
import com.dncomponents.client.components.core.events.focus.Focusable;
import com.dncomponents.client.components.core.events.focus.HasBlurHandlers;
import com.dncomponents.client.components.core.events.focus.HasFocusHandlers;
import com.dncomponents.client.dom.handlers.OnBlurHandler;
import com.dncomponents.client.dom.handlers.OnFocusHandler;
import com.dncomponents.client.views.FocusComponentView;
import org.teavm.jso.browser.TimerHandler;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.html.HTMLElement;


/**
 * Abstract base class for most components that can receive keyboard focus.
 */
public class BaseFocusComponent<T, V extends FocusComponentView> extends BaseComponent<T, V>
        implements Focusable, HasFocusHandlers, HasBlurHandlers, HasEnabled {

    boolean enabled = true;

    public BaseFocusComponent(V view) {
        super(view);
        initComplexFocus();
    }

    @Override
    public int getTabIndex() {
        return focusElement().getTabIndex();
    }

    @Override
    public void setAccessKey(char key) {
        focusElement().setAttribute("accesskey", String.valueOf(key));
    }

    @Override
    public void setFocus(boolean focused) {
        if (focused)
            focusElement().focus();
        else
            asElement().blur();
    }

    @Override
    public void setTabIndex(int index) {
        focusElement().setAttribute("tabindex", String.valueOf(index));
    }

    protected HTMLElement focusElement() {
        return view.getFocusElement();
    }

    @Override
    public HandlerRegistration addFocusHandler(OnFocusHandler handler) {
        return handler.addTo(asElement());
    }

    @Override
    public HandlerRegistration addBlurHandler(OnBlurHandler handler) {
        return handler.addTo(asElement());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        view.setEnabled(enabled);
    }

    protected boolean focused;
    private boolean firstTimeFocused;

    private void initComplexFocus() {
        asElement().addEventListener("focusout", new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                focused = false;
                Window.setTimeout(new TimerHandler() {
                    @Override
                    public void onTimer() {
                        if (!focused) {
                            //                    TODO teavm

//                            asElement().dispatchEvent(new FocusEvent("blur"));
                            firstTimeFocused = false;
                        }

                    }
                }, 200);
            }
        });
        asElement().addEventListener("focusin", new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                if (!firstTimeFocused) {
//                    TODO teavm
//                    asElement().dispatchEvent(new FocusEvent("focus"));
                }
                focused = true;
                firstTimeFocused = true;
            }
        });
    }
}
