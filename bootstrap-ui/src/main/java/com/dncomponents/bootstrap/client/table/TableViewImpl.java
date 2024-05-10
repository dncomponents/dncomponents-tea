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

package com.dncomponents.bootstrap.client.table;

import com.dncomponents.UiField;
import com.dncomponents.bootstrap.client.list.ListViewImpl;
import com.dncomponents.client.components.core.Console;
import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.events.HandlerRegistration;
import com.dncomponents.client.dom.DomUtil;
import com.dncomponents.client.dom.handlers.ScrollHandler;
import com.dncomponents.client.views.IsElement;
import com.dncomponents.client.views.core.ui.table.TableView;
import org.teavm.jso.dom.html.HTMLElement;


public class TableViewImpl extends ListViewImpl implements TableView {

    @UiField
    HTMLElement contentColgroupPanel;
    @UiField
    HTMLElement rowsPanel;
    @UiField
    HTMLElement headerColgroupPanel;
    @UiField
    HTMLElement headerRow;
    @UiField
    HTMLElement headerBodyPanel;
    @UiField
    HTMLElement footerBodyPanel;
    @UiField
    HTMLElement tableContent;
    @UiField
    public HTMLElement tableContentPanel;
    @UiField
    HTMLElement tableHeader;
    @UiField
    HTMLElement headerBarRow;
    //footer
    @UiField
    HTMLElement footerPanel;
    @UiField
    HTMLElement tableFooter;
    @UiField
    HTMLElement tableFooterColGroup;
    @UiField
    HTMLElement tableFooterRow;
    @UiField
    HTMLElement footerPagerPanel;

    HtmlBinder uiBinder = HtmlBinder.create(TableViewImpl.class, this);


    public TableViewImpl(String template) {
        uiBinder.setTemplateContent(template);
        uiBinder.bind();
        init();
    }


    public TableViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
        init();
    }

    @Override
    public HTMLElement createEmptyRow() {
        return DomUtil.createTr();
    }

    @Override
    public void addHeaderItem(HTMLElement element) {
        headerRow.appendChild(element);
        headerColgroupPanel.appendChild(DomUtil.createCol());
        contentColgroupPanel.appendChild(DomUtil.createCol());
    }

    @Override
    public void clearHeaders() {
        headerRow.setInnerHTML("");
        headerColgroupPanel.setInnerHTML("");
        contentColgroupPanel.setInnerHTML("");
    }

    @Override
    public void setColumnWidth(int column, String width) {
        if (column > contentColgroupPanel.getChildNodes().getLength())
            return;
        if ((headerColgroupPanel
                .getChildNodes()
                .item(column)) == null) {
            Console.log("Warning: this shouldn't be a null ?");
            return;
        }
        ((HTMLElement) contentColgroupPanel
                .getChildNodes().item(column))
                .getStyle().setProperty("width", width);
    }

    @Override
    public void setHeaderColumnWidth(int column, String width) {
        if (column > contentColgroupPanel.getChildNodes().getLength())
            return;
        if ((headerColgroupPanel
                .getChildNodes()
                .item(column)) == null) {
            //todo
            Console.log("Warning: this shouldn't be a null ?");
            return;
        }

        ((HTMLElement) headerColgroupPanel
                .getChildNodes()
                .item(column))
                .getStyle().setProperty("width,", width);
    }

    @Override
    public void setScrollable(boolean b) {
        if (b)
            tableContentPanel.getClassList().add(scrollableStyle);
        else
            tableContentPanel.getClassList().remove(scrollableStyle);
    }

    @Override
    public HandlerRegistration addScrollHandler(ScrollHandler scrollHandler) {
        return scrollHandler.addTo(tableContentPanel);
    }

    @Override
    public HTMLElement getScrollPanel() {
        return tableContentPanel;
    }

    @Override
    public HTMLElement insertAfter(IsElement rowTable, int size) {
        HTMLElement tr = addToRow(size);
        if (rowTable != null && rowTable.asElement().getParentNode() != null)
            rowTable.asElement().getParentNode().insertBefore(tr, rowTable.asElement().getNextSibling());
        return (HTMLElement) tr.getFirstChild();
    }

    @Override
    public HTMLElement addItemToRowColSpan(IsElement toAdd, int colSize) {
        HTMLElement row = addToRow(colSize);
        addItem(row);
        return row;
    }

    private HTMLElement addToRow(int colSize) {
        HTMLElement row = DomUtil.createTr();
        HTMLElement column = DomUtil.createTd();
        column.setAttribute("colspan", colSize + "");
        row.appendChild(column);
        return row;
    }

    @Override
    public void setPager(IsElement pager) {
        footerPagerPanel.setInnerHTML("");
        footerPagerPanel.appendChild(pager.asElement());
    }

    @Override
    public void addFooterItem(IsElement element) {
        tableFooterColGroup.appendChild(DomUtil.createCol());
        tableFooterRow.appendChild(element == null ? DomUtil.createTd() : element.asElement());
    }


    @Override
    public void clearFooter() {
        tableFooterRow.setInnerHTML("");
        tableFooterColGroup.setInnerHTML("");
    }

    @Override
    public void setFooterColumnWidth(int column, String width) {
        if (tableFooterColGroup.hasChildNodes())
            ((HTMLElement) tableFooterColGroup
                    .getChildNodes()
                    .item(column))
                    .getStyle().setProperty("width", width);
    }

    @Override
    public HTMLElement getHeaderRow() {
        return headerBodyPanel;
    }

    @Override
    public HTMLElement getFooterRow() {
        return footerBodyPanel;
    }

    @Override
    public void initFilteringHeader() {
        tableHeader.getClassList().remove("table-header-style");
    }

    @Override
    protected HTMLElement getItemPanel() {
        return rowsPanel;
    }
}
