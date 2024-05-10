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

package com.dncomponents.bootstrap.client.button;

import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.views.MainViewSlots;
import com.dncomponents.client.views.core.ui.button.ButtonView;
import org.teavm.jso.dom.html.HTMLElement;


public class ButtonIconViewImpl implements ButtonView {

    @UiField
    HTMLElement root;
    @UiField
    HTMLElement textPanel;
    @UiField
    HTMLElement iconPanel;
    @UiField
    String disabledStyle;

    HtmlBinder uiBinder = HtmlBinder.create(ButtonIconViewImpl.class, this);


    public ButtonIconViewImpl(String template) {
        uiBinder.setTemplateContent(template);
        uiBinder.bind();
    }

    public ButtonIconViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }


    @Override
    public String getText() {
        return textPanel.getTextContent();
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (enabled)
            root.getClassList().remove(disabledStyle);
        else
            root.getClassList().add(disabledStyle);
    }

    @Override
    public void setText(String text) {
        textPanel.setTextContent(text);
    }

    @Override
    public String getHTML() {
        return textPanel.getInnerHTML();
    }

    @Override
    public void setHTML(String html) {
        textPanel.setInnerHTML(html);
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    @Override
    public MainViewSlots getViewSlots() {
        return () -> textPanel;
    }


    protected static final String VIEW_ID = "ICON";

}
