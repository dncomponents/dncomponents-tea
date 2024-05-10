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

package com.dncomponents.bootstrap.client.dropdown;

import com.dncomponents.UiField;
import com.dncomponents.Template;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.dom.handlers.ClickHandler;
import com.dncomponents.client.views.MainViewSlots;
import com.dncomponents.client.views.core.ui.dropdown.DropDownItemView;
import org.teavm.jso.dom.html.HTMLElement;
import com.dncomponents.client.components.core.HTMLTemplateElement;


@Template
public class DropDownItemViewImpl implements DropDownItemView {

    @UiField
    HTMLElement root;
    HtmlBinder uiBinder = HtmlBinder.create(DropDownItemViewImpl.class, this);

    public DropDownItemViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }

    @Override
    public void setContent(String content) {
        asElement().setInnerHTML(content);
    }

    @Override
    public void setHtmlContent(HTMLElement content) {
        asElement().setInnerHTML("");
        asElement().appendChild(content);
    }

    @Override
    public void addClickHandler(ClickHandler clickHandler) {
        clickHandler.addTo(asElement());
    }

    @Override
    public void setActive(boolean active) {
        if (active)
            asElement().getClassList().add("active");
        else
            asElement().getClassList().remove("active");
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    @Override
    public MainViewSlots getViewSlots() {
        return this::asElement;
    }
}
