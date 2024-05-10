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

package com.dncomponents.bootstrap.client.tooltip;

import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.views.core.ui.tooltip.TooltipView;
import com.dncomponents.client.views.core.ui.tooltip.TooltipViewSlots;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;
import com.dncomponents.client.components.core.HTMLTemplateElement;


public class TooltipViewImpl<V extends TooltipViewSlots> implements TooltipView<V> {

    public static final String VIEW_ID = "default";
    @UiField
    HTMLElement root;
    @UiField
    protected HTMLElement contentPanel;
    @UiField
    HTMLElement arrowPanel;
    @UiField
    String topStyle;
    @UiField
    String bottomStyle;
    @UiField
    String leftStyle;
    @UiField
    String rightStyle;
    @UiField
    String baseStyle;
    @UiField
    String fadeStyle;
    @UiField
    String showStyle;

    HtmlBinder uiBinder = HtmlBinder.create(TooltipViewImpl.class, this);

    public TooltipViewImpl() {
    }

    public TooltipViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }

    public void setBottomOrientation() {
        asElement().setClassName((baseStyle + " " + fadeStyle + " " + bottomStyle + " " + showStyle));
    }

    @Override
    public void setTopOrientation() {
        asElement().setClassName((baseStyle + " " + fadeStyle + " " + topStyle + " " + showStyle));
    }

    @Override
    public void setLeftOrientation() {
        asElement().setClassName((baseStyle + " " + fadeStyle + " " + leftStyle + " " + showStyle));
    }

    @Override
    public void setRightOrientation() {
        asElement().setClassName((baseStyle + " " + fadeStyle + " " + rightStyle + " " + showStyle));
    }

    @Override
    public void calculatePositionBottom(HTMLElement owner) {
        setLeftArrowPosition(asElement().getOffsetWidth() / 2.0);
    }

    @Override
    public void calculatePositionTop(HTMLElement owner) {
        setLeftArrowPosition(asElement().getOffsetWidth() / 2.0);
    }

    @Override
    public void calculatePositionLeft(HTMLElement owner) {
        setTopArrowPosition((asElement().getOffsetHeight() / 2.0));
    }

    @Override
    public void calculatePositionRight(HTMLElement owner) {
        setTopArrowPosition(asElement().getOffsetHeight() / 2.0);
    }

    private double scrollY() {
        return Window.current().getScrollY();
    }

    @Override
    public void setContent(String text) {
        contentPanel.setTextContent(text);
    }

    @Override
    public void setContent(HTMLElement element) {
        contentPanel.appendChild(element);
    }

    private void setLeftArrowPosition(double n) {
        n -= arrowPanel.getOffsetWidth() / 2;
        arrowPanel.getStyle().setProperty("left", n + "px");
    }

    private void setTopArrowPosition(double n) {
        n -= arrowPanel.getOffsetHeight() / 2.0;
        arrowPanel.getStyle().setProperty("top", n + "px");
    }


    @Override
    public HTMLElement asElement() {
        return root;
    }

    TooltipViewSlots slots = new TooltipViewSlots() {
        @Override
        public HTMLElement getContent() {
            return contentPanel;
        }
    };

    @Override
    public V getViewSlots() {
        return (V) slots;
    }
}
