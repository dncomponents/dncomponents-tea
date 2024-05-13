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
import com.dncomponents.client.components.popover.Popper;
import com.dncomponents.client.dom.handlers.MouseEnterHandler;
import com.dncomponents.client.dom.handlers.MouseLeaveHandler;
import com.dncomponents.client.views.IsElement;
import com.dncomponents.client.views.core.ui.dropdown.DropDownTreeNodePanelView;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;

public class DropDownTreeNodePanelViewImpl implements DropDownTreeNodePanelView {


    private HtmlBinder uiBinder = HtmlBinder.create(DropDownTreeNodePanelViewImpl.class, this);

    @UiField
    public HTMLElement root;

    //todo teavm
    Popper popper;


    public DropDownTreeNodePanelViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
        Window.setTimeout(() ->
                        root.getStyle().setProperty("left", "-30px !important"),
                200);

    }


    @Override
    public HTMLElement asElement() {
        return root;
    }

    @Override
    public void add(IsElement item) {
        root.appendChild(item.asElement());
    }

    @Override
    public void clear() {
        root.setInnerHTML("");
    }

    @Override
    public void show(IsElement relativeTo, boolean b, String orientation) {
        if (b)
            root.getClassList().add("show");
        else
            root.getClassList().remove("show");
        //TODO teavm
        if (popper == null) {
            popper = new Popper(relativeTo.asElement(), this.asElement(), orientation);
        }
        popper.scheduleUpdate();
    }

    @Override
    public void addMouseEnterHandler(MouseEnterHandler mouseEnterHandler) {
        mouseEnterHandler.addTo(asElement());
    }

    @Override
    public void addMouseLeaveHandler(MouseLeaveHandler mouseLeaveHandler) {
        mouseLeaveHandler.addTo(asElement());
    }


}
