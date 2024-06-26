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

package com.dncomponents.client.components.list;

import com.dncomponents.client.components.core.events.value.ValueChangeEvent;
import com.dncomponents.client.components.core.events.value.ValueChangeHandler;
import com.dncomponents.client.views.core.ui.list.ListCellCheckBoxView;


public class ListCellCheckbox<T, M> extends ListCell<T, M> {

    public ListCellCheckbox() {
    }

    public ListCellCheckbox(ListCellCheckBoxView view) {
        super(view);
    }

    @Override
    protected void bind() {
        super.bind();
        getCellView().getCheckbox().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                getOwner().getSelectionModel()
                        .setSelected(getModel(), event.getValue(), true);
            }
        });
        getOwner().getSelectionModel().setNavigator(false);
    }

    @Override
    public ListCellCheckBoxView getCellView() {
        return (ListCellCheckBoxView) super.getCellView();
    }

    @Override
    protected void initViewFromOwner() {
        cellView = getUi().getListCheckBoxView();
    }
}
