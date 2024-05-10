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

package com.dncomponents.bootstrap.client.multilevel;

import com.dncomponents.bootstrap.client.BootstrapUi;
import com.dncomponents.client.components.core.AbstractPluginHelper;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.views.core.ViewFactory;
import com.dncomponents.client.views.core.ui.dropdown.DropDownMultiLevelUi;

import java.util.Map;


public class DropDownMultiLevelViewFactory {

    public static class DefaultDropDownMultiLevelViewFactory extends AbstractPluginHelper implements ViewFactory<DropDownMultiLevelUi> {

        private static DefaultDropDownMultiLevelViewFactory instance;

        private DefaultDropDownMultiLevelViewFactory() {
        }

        public static DefaultDropDownMultiLevelViewFactory getInstance() {
            if (instance == null)
                instance = new DefaultDropDownMultiLevelViewFactory();
            return instance;
        }

        @Override
        public DropDownMultiLevelUi getView(Map<String, String> attributes, HTMLTemplateElement templateElement) {
            if (templateElement == null)
                templateElement = (BootstrapUi.getUi()).dropDownMultiLevelUi;
            return new DropDownMultiLevelUiImpl(templateElement);
        }

        @Override
        public String getId() {
            return DropDownMultiLevelUiImpl.VIEW_ID;
        }

        @Override
        public Class getClazz() {
            return DropDownMultiLevelUiImpl.class;
        }
    }

}
