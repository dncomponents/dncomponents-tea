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
            <h1 class='main-title'>Main App</h1>
            <select dn-model="currentScreens" multiple style='height: 140px;'>
                <option>HelloComponent</option>
                <option>UserInputComponent</option>
                <option>EventsComponent</option>
                <option>AttributesComponent</option>
                <option>LoopComponent</option>
                <option>ValuesBindingComponent</option>
            </select>
            <hello-component dn-if='currentScreens.contains("HelloComponent")'></hello-component>
            <UserInputComponent dn-if='currentScreens.contains("UserInputComponent")'></UserInputComponent>
            <AttributesComponent dn-if='currentScreens.contains("AttributesComponent")'></AttributesComponent>
            <EventsComponent dn-if='currentScreens.contains("EventsComponent")'></EventsComponent>
            <LoopComponent dn-if='currentScreens.contains("LoopComponent")'></LoopComponent>
            <ValuesBindingComponent dn-if='currentScreens.contains("ValuesBindingComponent")'></ValuesBindingComponent>
        </div>
        """,
        //language=css
        css = """
                .main-title{
                    margin-top: 10px;
                    margin-bottom: 10px;
                }
                """
)
public class MainApp implements IsElement {
    HtmlBinder<MainApp> binder = HtmlBinder.create(MainApp.class, this);
    List<String> currentScreens = new ArrayList<>(Arrays.asList("HelloComponent"));

    public MainApp() {
        binder.bindAndUpdateUi();
    }

    @Override
    public HTMLElement asElement() {
        return binder.getRoot();
    }
}