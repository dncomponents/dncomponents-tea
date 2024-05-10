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

package com.dncomponents.bootstrap.client.textbox;

import com.dncomponents.Template;
import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.dom.handlers.KeyUpHandler;
import com.dncomponents.client.dom.handlers.OnBlurHandler;
import com.dncomponents.client.views.core.ui.textbox.TextBoxView;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.html.HTMLInputElement;


@Template
public class TextBoxViewImpl implements TextBoxView {

    public static final String VIEW_ID = "default";
    @UiField
    HTMLInputElement root;

    HtmlBinder uiBinder = HtmlBinder.create(TextBoxViewImpl.class, this);

    public TextBoxViewImpl() {
        uiBinder.setTemplateContent("<input ui-field=\"root\" class=\"form-control\" type=\"text\">\n");
        uiBinder.bind();
    }


    public TextBoxViewImpl(String template) {
        uiBinder.setTemplateContent(template);
        uiBinder.bind();
    }

    public TextBoxViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }

    @Override
    public String getValue() {
        return asElement().getValue();
    }

    @Override
    public void setValue(String value) {
        asElement().setValue(value);
    }

    @Override
    public void addOnInputChangeHandler(EventListener listener) {

    }

    @Override
    public void addOnBlurHandler(OnBlurHandler handler) {
        asElement().addEventListener(handler.getType(), handler.asEventListener());
    }

    @Override
    public void addOnKeyUpHandler(KeyUpHandler handler) {
        asElement().addEventListener(handler.getType(), handler.asEventListener());
    }

    @Override
    public void setError(boolean b) {
        if (b)
            root.getClassList().add("is-invalid");
        else
            root.getClassList().remove("is-invalid");
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        setError(errorMessage != null);
    }

    @Override
    public void setValid(boolean b) {
        if (b)
            root.getClassList().add("is-valid");
        else
            root.getClassList().remove("is-valid");
    }

    @Override
    public void setPlaceHolder(String placeHolder) {
        root.setAttribute("placeholder", placeHolder);
    }

    @Override
    public void setLabel(String label) {

    }

    @Override
    public HTMLInputElement asElement() {
        return root;
    }

}
