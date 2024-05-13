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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//language=html
@Component(template = """
        <div>
            <h2>*** input and two-way binding ***</h2>
            <h2>Text Input</h2>
            <input dn-model="text">
            <p>{{text}}</p>
            <h2>Checkbox</h2>
            <input type="checkbox" id="checkbox" dn-model="checked">
            <label for="checkbox">Checked: {{checked}}</label>
            <h2>Multi Checkbox</h2>
            <input type="checkbox" id="jack" value="Jack" dn-model="checkedNames">
            <label for="jack">Jack</label>
            <input type="checkbox" id="john" value="John" dn-model="checkedNames">
            <label for="john">John</label>
            <input type="checkbox" id="mike" value="Mike" dn-model="checkedNames">
            <label for="mike">Mike</label>
            <p>Checked names:{{text}} and {{checkedNames}} and {{selected}} and {{week}} and {{text}}</p>
            <h2>Radio</h2>
            <input type="radio" id="one" value="One" dn-model="picked">
            <label for="one">One</label>
            <br>
            <input type="radio" id="two" value="Two" dn-model="picked">
            <label for="two">Two</label>
            <p>Picked: {{picked}}</p>
            <h2>Select</h2>
            <select dn-model="selected">
                <option disabled value="">Please select one</option>
                <option>A</option>
                <option>B</option>
                <option>C</option>
            </select>
            <p>Selected: {{selected}}</p>
            <h2>Multi Select</h2>
            <select dn-model="multiSelected" multiple style="width:100px">
                <option>A</option>
                <option>B</option>
                <option>C</option>
            </select>
            <p>Selected: {{multiSelected}}</p>
            </template>
            <h2>Different value examples</h2>
            <label for="week">What week would you like to start?</label>
            <input id="week" type="week" name="week" dn-model="week"/> <br>
            <label for="week">Change week value in text box: </label>
            <input type='text' dn-model="week">
            <p>Selected week: {{week}}</p>
            <h2>Select single</h2>
            <input type='text' dn-model="selected">
            <select dn-model="selected">
                <option disabled value="">Please select one</option>
                <option>A</option>
                <option>B</option>
                <option>C</option>
            </select>
            <p>Selected: {{selected}}</p>
            <h2>Select multiple</h2>
            <button dn-on-click='addFruit("Orange")'>Add/remove Orange</button>
            <button dn-on-click='addFruit("Grape")'>Add/remove Grape</button>
            <select dn-model="selectedFruits" multiple>
                <option value="apple">Apple</option>
                <option value="banana">Banana</option>
                <option value="orange">Orange</option>
                <option value="grape">Grape</option>
                <option value="strawberry">Strawberry</option>
            </select>
            <p>Selected fruits: </p>
            <template dn-loop='fruit in selectedFruits'>
                <b>{{fruit}}</b>
            </template>
            <div dn-loop='fruit in selectedFruits'>
                <b>{{fruit}}</b>
            </div>
        </div>
        """)
public class UserInputComponent implements IsElement {
    String value = "Type";
    String selected = "A";
    List<String> selectedFruits = new ArrayList<>(Arrays.asList("Banana"));
    List<String> checkedNames = new ArrayList<>();
    List<String> multiSelected = new ArrayList<>();
    String text = "message";
    String week = "2024-W33";
    boolean checked = true;
    String picked = "One";

    void addFruit(String fruit) {
        if (!selectedFruits.contains(fruit)) {
            selectedFruits.add(fruit);
        } else {
            selectedFruits.remove(fruit);
        }
        binder.updateUi();
    }

    HtmlBinder<UserInputComponent> binder = HtmlBinder.create(UserInputComponent.class, this);

    public UserInputComponent() {
        binder.bindAndUpdateUi();
    }

    @Override
    public HTMLElement asElement() {
        return binder.getRoot();
    }
}