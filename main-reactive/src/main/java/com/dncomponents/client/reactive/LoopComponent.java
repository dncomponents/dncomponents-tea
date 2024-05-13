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
import com.dncomponents.client.testing.TestingHelper;
import com.dncomponents.client.views.IsElement;
import org.teavm.jso.dom.html.HTMLElement;

import java.util.List;

//language=html
@Component(template = """
        <div>
            <h2>Loop in table</h2>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                </tr>
                </thead>
                <tbody>
                <template dn-loop='person in persons'>
                    <tr>
                        <td>
                            {{person.getName()}}
                        </td>
                        <td>
                            {{person.getAge()}}
                        </td>
                    </tr>
                </template>
                </tbody>
            </table>
            <h2>Loop in list</h2>
            <ul dn-loop='person 1in persons'>
                <li>{{person.getName()}}</li>
            </ul>
            <h2>Loop components in list</h2>
            <ul dn-loop='person in persons'>
                <PersonComponent person='{{person}}' color='red'></PersonComponent>
            </ul>
        </div>
        """)
public class LoopComponent implements IsElement {
    HtmlBinder<LoopComponent> binder = HtmlBinder.create(LoopComponent.class, this);
    List<Person> persons = TestingHelper.getPeople(10);

    public LoopComponent() {
        binder.bindAndUpdateUi();
    }

    @Override
    public HTMLElement asElement() {
        return binder.getRoot();
    }
}