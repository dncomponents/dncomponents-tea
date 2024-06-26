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
import com.dncomponents.client.testing.Person;
import com.dncomponents.client.views.IsElement;
import org.teavm.jso.dom.html.HTMLElement;

@Component(
//language=html
        template = """
                <div class='someCss'>
                    <h2>Testing component attributes</h2>
                     <PersonComponent person='{{pers}}' someNumb='{{number}}' color='red'></PersonComponent>
                </div>

                """,
//language=css
        css = """
                        .someCss{
                          background:    #2ce3c1;
                        }
                """
)
public class AttributesComponent implements IsElement {
    HtmlBinder<AttributesComponent> binder;

    Person pers;
    int number = 200;

    public AttributesComponent() {
        pers = new Person("Peter");
        pers.setAge(32);
        pers.setGender("Male");
        binder = HtmlBinder.create(AttributesComponent.class, this);
        binder.bindAndUpdateUi();
    }

    @Override
    public HTMLElement asElement() {
        return binder.getRoot();
    }


}