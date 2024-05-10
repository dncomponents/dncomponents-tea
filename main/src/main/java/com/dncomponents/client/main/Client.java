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

package com.dncomponents.client.main;

import com.dncomponents.bootstrap.client.BootstrapUi;
import com.dncomponents.client.components.core.AppTemplates;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.dom.History;
import com.dncomponents.client.main.components.appviews.MainApp;
import com.dncomponents.client.views.Ui;
import org.teavm.jso.dom.html.HTMLDocument;


public class Client {

    public static void main(String[] args) {
        AppTemplates.register();
        Ui.setDebug(true);

        testComponents();
//        testReactive();
        HtmlBinder.cssDevMode();
        History.fireCurrentHistoryState();
    }

    private static void testComponents() {
        Ui.set(new BootstrapUi());
        MainApp mainApp = new MainApp();
        HTMLDocument.current().getBody().appendChild(mainApp.asNode());
    }

    private static void testReactive() {
        com.dncomponents.client.main.reactive.MainApp mainApp2 = new com.dncomponents.client.main.reactive.MainApp();
        HTMLDocument.current().getBody().appendChild(mainApp2.asElement());
    }
}