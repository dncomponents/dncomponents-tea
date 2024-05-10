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
import com.dncomponents.client.components.button.Button;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.views.IsElement;
import org.teavm.jso.dom.html.HTMLAnchorElement;
import org.teavm.jso.dom.html.HTMLElement;

public class HomeView implements IsElement {
    @UiField
    public HTMLElement root;
    @UiField
    Button buttonField;
    @UiField
    HTMLAnchorElement anchorField;


    {
        HtmlBinder.create(HomeView.class, this).bind();
    }

    public HomeView() {
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
