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

package com.dncomponents.client.components.html;

import com.dncomponents.client.dom.DomUtil;
import com.dncomponents.client.views.IsElement;
import org.teavm.jso.dom.html.HTMLElement;


public class HtmlComponent implements IsElement {
    final HTMLElement element;

    public HtmlComponent(String tag, String content) {

        element = DomUtil.cast(DomUtil.createElement(tag));
        setHtml(content);
    }

    public void setAttribute(String key, String value) {
        element.setAttribute(key, value);
    }

    @Override
    public HTMLElement asElement() {
        return element;
    }

    public void setHtml(String html) {
        element.setInnerHTML(html);
    }
}
