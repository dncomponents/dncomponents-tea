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

package com.dncomponents.bootstrap.client.cell;

import com.dncomponents.UiField;
import com.dncomponents.client.dom.DomUtil;
import com.dncomponents.client.dom.handlers.ClickHandler;
import com.dncomponents.client.dom.handlers.DoubleClickHandler;
import com.dncomponents.client.dom.handlers.KeyDownHandler;
import com.dncomponents.client.views.core.pcg.cell.BaseCellView;
import org.teavm.jso.dom.html.HTMLElement;


public abstract class BaseCellViewImpl implements BaseCellView {

    @UiField
    protected String modelSelected;
    @UiField
    protected String cellHighlighted;
    @UiField
    protected String errorCell;
    @UiField
    protected String valueChanged;
    @UiField
    public HTMLElement valuePanel;
    @UiField
    public HTMLElement root;

    @Override
    public void addClickHandler(ClickHandler clickHandler) {
        asElement().addEventListener(clickHandler.getType(), clickHandler.asEventListener());
    }

    @Override
    public void addDoubleClickHandler(DoubleClickHandler doubleClickHandler) {
        doubleClickHandler.addTo(asElement());
    }

    @Override
    public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
        keyDownHandler.addTo(asElement());
    }

    @Override
    public void setErrorStyle(boolean b) {
        if (b)
            asElement().getClassList().add(errorCell);
        else
            asElement().getClassList().remove(errorCell);
    }

    @Override
    public void setValueChangedStyle(boolean b) {
        if (b)
            asElement().getClassList().add(valueChanged);
        else
            asElement().getClassList().remove(valueChanged);
    }

    @Override
    public void setSelected(boolean b) {
        if (b)
            asElement().getClassList().add(modelSelected);
        else
            asElement().getClassList().remove(modelSelected);
    }

    @Override
    public void setFocus(boolean b) {
        if (b) {
            asElement().setAttribute("tabindex", "0");
            asElement().focus();
        } else {
            asElement().removeAttribute("tabindex");
            asElement().blur();
        }
    }

    public void setToValuePanel(HTMLElement element) {
        getValuePanel().setInnerHTML(""); //clear
        getValuePanel().appendChild(element);
    }

    @Override
    public HTMLElement getValuePanel() {
        return valuePanel;
    }


    private HTMLElement successDiv = DomUtil.createDiv();
    private HTMLElement errorDiv = DomUtil.createDiv();

    @Override
    public void showSuccessMessage(String successText) {
        if (errorDiv.getParentNode() != null) {
            DomUtil.removeElement(errorDiv);
        }
        if (successText == null) {
            if (successDiv.getParentNode() != null) {
                DomUtil.removeElement(successDiv);
            }
        } else {
            successDiv.getStyle().setProperty("position", "absolute");
            successDiv.getStyle().setProperty("width", "100%");
            successDiv.setInnerHTML("<div class=\"valid-tooltip d-block\">" + successText + "</div>");
            getValuePanel().appendChild(successDiv);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        if (successDiv.getParentNode() != null) {
            DomUtil.removeElement(successDiv);
        }

        if (message == null) {
            if (errorDiv.getParentNode() != null) {
                DomUtil.removeElement(errorDiv);
            }
        } else {
            errorDiv.getStyle().setProperty("position", "absolute");
            errorDiv.getStyle().setProperty("width", "100%");
            errorDiv.setInnerHTML("<div class=\"invalid-tooltip  d-block\">" + message + "</div>");
            getValuePanel().appendChild(errorDiv);
        }
    }


    @Override
    public HTMLElement asElement() {
        return root;
    }


}
