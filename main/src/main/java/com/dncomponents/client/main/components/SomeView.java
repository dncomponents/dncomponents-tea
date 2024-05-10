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

package com.dncomponents.client.main.components;

import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.views.IsElement;
import org.teavm.jso.dom.html.HTMLElement;

public class SomeView implements IsElement {
    @UiField
    public HTMLElement root;

    {
        HtmlBinder.create(SomeView.class, this).bind();
    }

    public SomeView() {
        init();
    }

    private void init() {
        //init code here
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

}
