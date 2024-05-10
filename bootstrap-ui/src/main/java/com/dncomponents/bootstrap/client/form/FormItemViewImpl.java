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

package com.dncomponents.bootstrap.client.form;

import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.events.value.HasValue;
import com.dncomponents.client.views.core.ui.form.FormItemView;
import org.teavm.jso.dom.html.HTMLElement;

public class FormItemViewImpl implements FormItemView {

    @UiField
    public HTMLElement root;
    @UiField
    HTMLElement labelElement;
    @UiField
    HTMLElement mainPanel;
    @UiField
    HTMLElement helperTextElement;

    HtmlBinder uiBinder = HtmlBinder.create(FormItemViewImpl.class, this);

    public FormItemViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    @Override
    public void setLabelText(String labelText) {
        if (labelText != null)
            this.labelElement.setInnerHTML(labelText);
    }

    @Override
    public void setError(String error) {
        if (error != null)
            this.helperTextElement.setInnerHTML(error);
        this.helperTextElement.getClassList().add("invalid-feedback");
//        this.mainPanel.getClassList().add("error_form");
        if (error == null) {
            this.helperTextElement.getClassList().remove("invalid-feedback");
//            this.mainPanel.getClassList().remove("error_form");
        }
        this.helperTextElement.getStyle().setProperty("display", "block");
        this.helperTextElement.setInnerHTML(error);
    }

    @Override
    public void setErrorStyle(boolean b) {
        if (b)
            this.mainPanel.getClassList().add("errorCell");
        else
            this.mainPanel.getClassList().remove("errorCell");
    }

    @Override
    public void setSuccessStyle(boolean b) {
        if (b)
            this.mainPanel.getClassList().add("validCell");
        else
            this.mainPanel.getClassList().remove("validCell");
    }

    @Override
    public HTMLElement getMainPanel() {
        return this.mainPanel;
    }

    @Override
    public void setHelperText(String helperText) {
        if (helperText != null)
            this.helperTextElement.setInnerHTML(helperText);
    }

    @Override
    public void setContent(HTMLElement element) {
        mainPanel.appendChild(element);
    }

    @Override
    public <M> HasValue<M> getHasValue() {
        return null;
    }

    @Override
    public void setSuccess(String validText) {
        if (validText != null) {
            this.helperTextElement.setInnerHTML(validText);
            this.helperTextElement.getClassList().add("valid-feedback");
        } else
            this.helperTextElement.getClassList().remove("valid-feedback");
    }
}
