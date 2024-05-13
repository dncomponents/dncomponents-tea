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

package com.dncomponents.client.reactive;

import com.dncomponents.Component;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.views.IsElement;
import org.teavm.jso.dom.html.HTMLElement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

//language=html
@Component(template = """
              <div>
                  <h2>Testing conditional rendering</h2>
                  <button on-click='changeType()'>{{type}}</button>
                    <div dn-if='type=="A"'>
                          A block
                      </div>
                      <div dn-else-if='type=="B"'>
                          B block
                      </div>
                      <div dn-else-if='type=="C"'>
                          C block
                      </div>
                      <div dn-else>
                          Not A/B/C
                      </div>
               </div>
        """,
//language=css
        css = """
                        .otherCss{
                          background: blue;
                        }
                """
)
public class ConditionalComponent implements IsElement {
    HtmlBinder<ConditionalComponent> binder = HtmlBinder.create(ConditionalComponent.class, this);
    String type = "B";
    LinkedList<String> types = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));
    Iterator<String> iterator = types.iterator();

    public ConditionalComponent() {
        binder.bindAndUpdateUi();
    }

    void changeType() {
        if (!iterator.hasNext()) {
            iterator = types.listIterator();
        }
        type = iterator.next();
        binder.updateUi();
    }

    @Override
    public HTMLElement asElement() {
        return binder.getRoot();
    }
}