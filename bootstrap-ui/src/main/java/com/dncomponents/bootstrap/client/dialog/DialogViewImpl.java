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

package com.dncomponents.bootstrap.client.dialog;

import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.events.Command;
import com.dncomponents.client.components.core.events.HandlerRegistration;
import com.dncomponents.client.dom.DomUtil;
import com.dncomponents.client.dom.handlers.*;
import com.dncomponents.client.views.IsElement;
import com.dncomponents.client.views.core.ui.modal.DialogView;
import com.dncomponents.client.views.core.ui.modal.DialogViewSlots;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;


public class DialogViewImpl implements DialogView {

    public static final String VIEW_ID = "default";
    @UiField
    public HTMLElement root;
    @UiField
    public HTMLElement modalDialogPanel;
    @UiField
    public HTMLElement header;
    @UiField
    public HTMLElement body;
    @UiField
    public HTMLElement footer;
    @UiField
    public HTMLElement closeButton;
    @UiField
    public HTMLElement okButton;
    @UiField
    public HTMLElement closeHeader;
    @UiField
    public HTMLElement titleHeader;
    @UiField
    HTMLElement backDropDiv;
    private boolean backdrop;
    private boolean closeOnEscape;
    private boolean draggable;
    private HandlerRegistration backDrophandlerRegistration;
    private HandlerRegistration escapeHandlerRegistration;
    private HandlerRegistration mouseDownHandlerRegistration;
    HtmlBinder uiBinder = HtmlBinder.create(DialogViewImpl.class, this);

    public DialogViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
        init();
    }

    public DialogViewImpl(String templateContent) {
        uiBinder.setTemplateContent(templateContent);
        uiBinder.bind();
        init();
    }

    double pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
    double p;

    private void init() {
        DomUtil.removeElement(backDropDiv);
        //todo teavm check
        modalDialogPanel.getStyle().setProperty("margin-right", "0px");
        modalDialogPanel.getStyle().setProperty("margin-left", "0px");
//        modalDialogPanel.style.marginRight = CSSProperties.MarginRightUnionType.of(0);
//        modalDialogPanel.style.marginLeft = CSSProperties.MarginLeftUnionType.of(0);
    }


    @Override
    public void setHeader(IsElement element) {
        header.setInnerHTML("");
        header.appendChild(element.asElement());
    }

    @Override
    public void setContent(HTMLElement element) {
        body.setInnerHTML("");
        body.appendChild(element);
    }

    @Override
    public void setTitle(String title) {
        titleHeader.setInnerHTML(title);
    }

    @Override
    public void setFooter(IsElement el) {
        footer.setInnerHTML("");
        footer.appendChild(el.asElement());
    }

    @Override
    public void addOkHandler(ClickHandler clickHandler, String text) {
        okButton.getStyle().setProperty("display", "block");
        clickHandler.addTo(okButton);
        okButton.setTextContent(text);
    }

    @Override
    public void addCloseHandler(Command onCloseCmd) {
        final ClickHandler clkHandler = mouseEvent -> {
            mouseEvent.stopPropagation();
            //todo teavm
//            mouseEvent.stopImmediatePropagation();
            onCloseCmd.execute();
        };
        DomUtil.addHandler(closeHeader, clkHandler);
        DomUtil.addHandler(closeButton, clkHandler);
    }

    @Override
    public void show() {
        asElement().getStyle().setProperty("display", "block");
        asElement().getClassList().add("show");
        bodyClassShown(true);
        modalDialogPanel.getStyle().setProperty("top", ((Window.current().getInnerHeight() / 2) - (modalDialogPanel.getOffsetHeight() / 2)) + "px");
        modalDialogPanel.getStyle().setProperty("left", ((Window.current().getInnerWidth() / 2) - (modalDialogPanel.getOffsetWidth() / 2)) + "px");
        if (backdrop)
            DomUtil.addToBody(backDropDiv);
        if (closeOnEscape)
            root.focus();
    }

    private void bodyClassShown(boolean b) {

        if (b)
            HTMLDocument.current().getBody().getClassList().add("modal-open");
        else
            HTMLDocument.current().getBody().getClassList().remove("modal-open");
    }

    @Override
    public void hide() {
        asElement().getClassList().remove("show");
        asElement().getStyle().setProperty("display", "none");
        bodyClassShown(false);
        if (backdrop) {
            HTMLDocument.current().getBody().removeChild(backDropDiv);
        }
    }

    @Override
    public void addFooterElement(HTMLElement element) {
        footer.appendChild(element);
    }

    @Override
    public void clearFooter() {
        footer.setInnerHTML("");
    }

    @Override
    public void setWidth(String width) {
        DomUtil.setMinWidth(modalDialogPanel, width);
    }

    @Override
    public void setHeight(String height) {
        DomUtil.setMinHeight(modalDialogPanel, height);
    }


    @Override
    public void setBackDrop(boolean backdrop) {
        this.backdrop = backdrop;
        remove(backDrophandlerRegistration);
        if (backdrop) {
            backDrophandlerRegistration =
                    DomUtil.addHandler(backDropDiv, (ClickHandler) mouseEvent -> hide());
        }
    }


    @Override
    public void setCloseOnEscape(boolean closeOnEscape) {
        this.closeOnEscape = closeOnEscape;
        if (closeOnEscape) {
            root.setAttribute("tabindex", "-1");
            remove(escapeHandlerRegistration);
            escapeHandlerRegistration = DomUtil.addHandler(root, (KeyDownHandler) event -> {
                if (event.getCode().equals("Escape")) {
                    hide();
                }
            });
        } else {
            remove(escapeHandlerRegistration);
            root.removeAttribute("tabindex");
        }
    }


    @Override
    public void setDraggable(boolean draggable) {
        remove(mouseDownHandlerRegistration);
        this.draggable = draggable;
        if (this.draggable) {
            mouseDownHandlerRegistration = ((MouseDownHandler) e -> {
                e.preventDefault();
                pos3 = e.getClientX();
                pos4 = e.getClientY();
                p = (pos4 - modalDialogPanel.getOffsetTop());
                final HandlerRegistration mouseMoveRegistration = ((MouseMoveHandler) mm -> {
                    mm.preventDefault();
                    // calculate the new cursor position:
                    pos1 = pos3 - mm.getClientX();
                    pos2 = pos4 - mm.getClientY();
                    pos3 = mm.getClientX();
                    pos4 = mm.getClientY();
                    // set the element's new position:
                    modalDialogPanel.getStyle().setProperty("top", (pos4 - p) - 25 + "px");
                    ;
                    modalDialogPanel.getStyle().setProperty("left", (modalDialogPanel.getOffsetLeft() - pos1) + "px");
                }).addTo(asElement());
                final HandlerRegistration mouseUpRegistration = ((MouseUpHandler) mouseEvent -> {
                    remove(mouseMoveRegistration);
                }).addTo(HTMLDocument.current());
            }).addTo(header);
        }
    }

    @Override
    public void setPosition(int top, int left) {
        modalDialogPanel.getStyle().setProperty("top", top + "px");
        modalDialogPanel.getStyle().setProperty("left", left + "px");
    }

    @Override
    public int getTopPosition() {
        final String top = modalDialogPanel.getStyle().getPropertyValue("top");
        return Integer.parseInt(top.substring(0, top.length() - 2));
    }

    @Override
    public int getLeftPosition() {
        final String left = modalDialogPanel.getStyle().getPropertyValue("left");
        return Integer.parseInt(left.substring(0, left.length() - 2));
    }

    private void remove(HandlerRegistration registration) {
        if (registration != null)
            registration.removeHandler();
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }


    @Override
    public DialogViewSlots getViewSlots() {
        return slots;
    }

    DialogViewSlots slots = new DialogViewSlots() {
        @Override
        public HTMLElement getHeaderPanel() {
            return titleHeader;
        }

        @Override
        public HTMLElement getContentPanel() {
            return body;
        }

        @Override
        public HTMLElement getFooterPanel() {
            return footer;
        }
    };
}
