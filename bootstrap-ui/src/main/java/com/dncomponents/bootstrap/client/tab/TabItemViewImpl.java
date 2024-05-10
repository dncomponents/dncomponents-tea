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

package com.dncomponents.bootstrap.client.tab;

import com.dncomponents.Template;
import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.events.Command;
import com.dncomponents.client.views.core.ui.tab.TabItemView;
import com.dncomponents.client.views.core.ui.tab.TabItemViewSlots;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.html.HTMLElement;

@Template
public class TabItemViewImpl implements TabItemView {

    @UiField
    HTMLElement tabItemNav;
    @UiField
    HTMLElement tabItemPanel;
    @UiField
    HTMLElement tabItemContent;
    @UiField
    String showTabContentStyle;
    @UiField
    String active;


    HtmlBinder uiBinder = HtmlBinder.create(TabItemViewImpl.class, this);

    public TabItemViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }


    @Override
    public void addItemSelectedHandler(EventListener handler) {
        tabItemPanel.addEventListener("click", handler);
    }

    @Override
    public void select(boolean b) {
        String[] ll = showTabContentStyle.split(" ");
        if (b) {
            tabItemPanel.getClassList().add(active);
            for (String s : ll) {
                tabItemContent.getClassList().add(s);
            }
        } else {
            tabItemPanel.getClassList().remove(active);
            for (String s : ll) {
                tabItemContent.getClassList().remove(s);
            }
        }
    }

    @Override
    public void setItemTitle(String html) {
        tabItemPanel.setTextContent(html);
    }

    @Override
    public void setItemTitleHtml(String html) {
        tabItemPanel.setInnerHTML(html);
    }

    @Override
    public void setItemTitle(HTMLElement html) {
        tabItemPanel.setInnerHTML(html.getInnerHTML());
    }

    @Override
    public void setItemContent(String html) {
        tabItemContent.setTextContent(html);
    }

    @Override
    public void setItemContent(HTMLElement htmlElement) {
        tabItemContent.setInnerHTML(htmlElement.getInnerHTML());
    }

    @Override
    public void setImmediate(Command command) {

    }

    @Override
    public HTMLElement getTabItemNav() {
        return tabItemNav;
    }

    @Override
    public HTMLElement getTabItemContent() {
        return tabItemContent;
    }

    TabItemViewSlots tabItemViewSlots = new TabItemViewSlots() {
        @Override
        public HTMLElement getTitle() {
            return tabItemPanel;
        }

        @Override
        public HTMLElement getIcon() {
            return null;
        }

        @Override
        public HTMLElement getContent() {
            return tabItemContent;
        }
    };

    @Override
    public TabItemViewSlots getViewSlots() {
        return tabItemViewSlots;
    }


    @Override
    public HTMLElement asElement() {
        return tabItemPanel;
    }
}
