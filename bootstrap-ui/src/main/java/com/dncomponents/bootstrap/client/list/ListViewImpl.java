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

package com.dncomponents.bootstrap.client.list;

import com.dncomponents.Template;
import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.events.HandlerRegistration;
import com.dncomponents.client.dom.DomUtil;
import com.dncomponents.client.dom.handlers.KeyDownHandler;
import com.dncomponents.client.dom.handlers.ScrollHandler;
import com.dncomponents.client.views.core.ui.list.ListView;
import org.teavm.jso.dom.html.HTMLElement;


@Template
public class ListViewImpl implements ListView {

    HtmlBinder uiBinder = HtmlBinder.create(ListViewImpl.class, this);
    @UiField
    public HTMLElement root;

    @UiField
    String rowHeight;

    double currentScrollTop;

    @UiField
    protected String scrollableStyle;

    public ListViewImpl() {
    }

    public ListViewImpl(HTMLTemplateElement listMain) {
        uiBinder.setTemplateElement(listMain);
        uiBinder.bind();
        init();
    }

    protected void init() {
        addScrollHandler(event -> currentScrollTop = getScrollTop());
    }

    @Override
    public void addItem(HTMLElement element) {
        getItemPanel().appendChild(element);
    }

    @Override
    public void addItemAtTop(HTMLElement element) {
        if (getItemPanel().hasChildNodes())
            getItemPanel().insertBefore(element, getItemPanel().getChildNodes().item(0));
    }

    @Override
    public void clear() {
        getItemPanel().setInnerHTML("");
    }

    @Override
    public void setScrollable(boolean b) {
        if (b)
            getItemPanel().getClassList().add(scrollableStyle);
        else
            getItemPanel().getClassList().remove(scrollableStyle);
    }

    @Override
    public void setScrollHeight(String height) {
        DomUtil.setMaxHeight(getScrollPanel(), height);
    }

    @Override
    public HandlerRegistration addScrollHandler(ScrollHandler scrollHandler) {
        return scrollHandler.addTo(getItemPanel());
    }

    @Override
    public double getScrollTop() {
        return getScrollPanel().getScrollTop();
    }

    @Override
    public void resetScrollTop(Double value) {
        if (value == null)
            getScrollPanel().setScrollTop((int) currentScrollTop);
        else {
            getScrollPanel().setScrollTop(value.intValue());
        }
    }

    @Override
    public HTMLElement getScrollPanel() {
        return root;
    }

    @Override
    public HTMLElement createEmptyRow() {
        return DomUtil.createLi();
    }

    @Override
    public int getRowHeight() {
        return Integer.parseInt(rowHeight);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler keyDownHandler) {
        return keyDownHandler.addTo(root);
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    protected HTMLElement getItemPanel() {
        return root;
    }
}
