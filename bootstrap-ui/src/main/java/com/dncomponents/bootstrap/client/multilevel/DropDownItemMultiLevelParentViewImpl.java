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

package com.dncomponents.bootstrap.client.multilevel;

import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.dom.handlers.ClickHandler;
import com.dncomponents.client.dom.handlers.MouseEnterHandler;
import com.dncomponents.client.dom.handlers.MouseLeaveHandler;
import com.dncomponents.client.views.MainViewSlots;
import com.dncomponents.client.views.MainViewSlotsImpl;
import com.dncomponents.client.views.core.ui.dropdown.DropDownItemMultiLevelParentView;
import org.teavm.jso.dom.html.HTMLElement;


public class DropDownItemMultiLevelParentViewImpl implements DropDownItemMultiLevelParentView {

    @UiField
    HTMLElement root;
    @UiField
    HTMLElement textPanel;

    HtmlBinder uiBinder = HtmlBinder.create(DropDownItemMultiLevelParentViewImpl.class, this);

    public DropDownItemMultiLevelParentViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }

    @Override
    public void setContent(String content) {
        textPanel.setInnerHTML("");
        textPanel.setInnerHTML(content);
    }

    @Override
    public void setHtmlContent(HTMLElement content) {
        textPanel.setInnerHTML("");
        textPanel.appendChild(content);
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
        return new MainViewSlotsImpl(textPanel);
    }

    @Override
    public void addMouseLeaveHandler(MouseLeaveHandler mouseLeaveHandler) {
        mouseLeaveHandler.addTo(asElement());
    }

    @Override
    public void addMouseEnterHandler(MouseEnterHandler mouseEnterHandler) {
        mouseEnterHandler.addTo(asElement());
    }
}
