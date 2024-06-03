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
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;
import org.teavm.jso.browser.Storage;
import org.teavm.jso.browser.Window;
import org.teavm.jso.core.*;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;
import org.teavm.jso.json.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component(template = """
        <section class="todoapp">
            <header class="header">
                <h1>Todos</h1>
                <input class="new-todo" autofocus placeholder="What needs to be done?"
                       dn-on-keyup='addTodo(e)'>
            </header>
            <section class="main" dn-if='!todos.isEmpty()'>
                <input id="toggle-all" class="toggle-all" type="checkbox"
                       checked='{{getRemaining() == 0}}'
                       dn-on-change='toggleAll(e)'>
                <label for="toggle-all">Mark all as complete</label>
                <ul class="todo-list" dn-loop='todo in getFilteredTodos()'>
                    <li class='todo {{getTodoClass(todo)}}'>
                        <div class="view">
                            <input class="toggle" type="checkbox" dn-model='todo.completed'>
                            <label dn-on-dblclick='editTodo(todo)'>{{todo.title}}</label>
                            <button class="destroy" dn-on-click='removeTodo(todo)'></button>
                        </div>
                        <input dn-if='todo.equals(editedTodo)'
                               autofocus
                               class="edit"
                               type="text"
                               dn-on-dnshow='onElementShown(e)'
                               dn-model='todo.title'
                               dn-on-blur='doneEdit(todo)'
                               dn-on-keyup='onEdit(e,todo)'
                        >
                    </li>
                </ul>
            </section>
            <footer class="footer" dn-if='!todos.isEmpty()'>
              <span class="todo-count">
                <strong>{{getRemaining()}}</strong>
                <span>{{getRemaining() == 1 ? "item" : "items"}} left</span>
              </span>
                <ul class="filters">
                    <li>
                        <a href="#/All" class='{{getSelectedClass("All")}}'>All</a>
                    </li>
                    <li>
                        <a href="#/Active" class='{{getSelectedClass("Active")}}'>Active</a>
                    </li>
                    <li>
                        <a href="#/Completed" class='{{getSelectedClass("Completed")}}'>Completed</a>
                    </li>
                </ul>
                <button class="clear-completed" dn-on-click='removeCompleted()' dn-if='todos.size() > getRemaining()'>
                    Clear completed
                </button>
            </footer>
        </section>
        """
)
public class TodoComponent implements IsElement {
    HtmlBinder<TodoComponent> binder = HtmlBinder.create(TodoComponent.class, this);
    List<Todo> todos = new ArrayList<>();
    Filter currentFilter = Filter.All;
    String beforeEditCache = "";
    Todo editedTodo;
    private static final String STORAGE_KEY = "dn-todomvc";


    public TodoComponent() {
        Storage localStorage = Window.current().getLocalStorage();
        binder.bindAndUpdateUi();
        Window.current().addEventListener("hashchange", e -> onHashChange());
        onHashChange();
        //todos variable is not directly referenced from html markup so no state created.
        // That's wy we need this createOrGetState call.
        binder.createOrGetState("todos", () -> todos)
                .addValueChangeHandler(event -> {
                    JSArray jsArray = JSArray.of(todos.stream()
                            .map(todo -> createJsonTodo(
                                    JSDate.create((double) todo.id.getTime()),
                                    JSString.valueOf(todo.title),
                                    JSBoolean.valueOf(todo.completed)))
                            .toArray());
                    localStorage.setItem(STORAGE_KEY, JSON.stringify(jsArray));
                });
        JSObject parse = JSON.parse(localStorage.getItem(STORAGE_KEY));
        if (parse != null && !JSObjects.isUndefined(parse)) {
            JSArray<JsonTodo> parsedArray = (JSArray) parse;
            todos.clear();
            for (int i = 0; i < parsedArray.getLength(); i++) {
                todos.add(new Todo(parsedArray.get(i)));
            }
            binder.updateUi();
        }
    }

    void onElementShown(Event e) {
        Window.setTimeout(() -> ((HTMLInputElement) e.getTarget()).focus(), 111);
    }

    void onHashChange() {
        String route = Window.current().getLocation().getHash().replaceAll("^#/?", "");
        try {
            Filter filter = Filter.valueOf(route);
            currentFilter = filter;
        } catch (IllegalArgumentException ex) {
            Window.current().getLocation().setHash("");
            currentFilter = Filter.All;
        }
        binder.updateUi();
    }

    void addTodo(Event e) {
        HTMLInputElement target = (HTMLInputElement) e.getTarget();
        String value = target.getValue().trim();
        if (((KeyboardEvent) e).getKey().equals("Enter")) {
            if (!value.isEmpty()) {
                todos.add(new Todo(value));
                binder.updateUi();
                target.setValue("");
                target.focus();
            }
        }
    }

    String getSelectedClass(String cls) {
        return Filter.valueOf(cls).equals(currentFilter) ? "selected" : "";
    }

    void removeTodo(Todo item) {
        todos.remove(item);
        binder.updateUi();
    }

    void editTodo(Todo item) {
        beforeEditCache = item.title;
        editedTodo = item;
        //we need to force update because there is no changes in data of list getFilteredTodos()
        // but there is a change in todo.equals(editedTodo) after editing starts and we need to refresh list items.
        binder.getState("getFilteredTodos()").forceUpdateUI();
    }

    void cancelEdit(Todo item) {
        editedTodo = null;
        item.title = beforeEditCache;
        binder.updateUi();
    }

    void doneEdit(Todo todo) {
        if (editedTodo != null) {
            editedTodo = null;
            todo.title = todo.title.trim();
            if (todo.title.isEmpty())
                removeTodo(todo);
        }
        binder.updateUi();
        binder.getState("getFilteredTodos()").forceUpdateUI();
    }

    void onEdit(Event e, Todo todo) {
        if (((KeyboardEvent) e).getKey().equals("Enter")) {
            doneEdit(todo);
        } else if (((KeyboardEvent) e).getKey().equals("Escape")) {
            cancelEdit(todo);
        }
    }

    void removeCompleted() {
        todos = getFilteredTodos(Filter.Active);
        binder.updateUi();
    }

    String getTodoClass(Todo todo) {
        String result = "";
        if (todo.completed) {
            result += "completed";
        }
        if (todo.equals(editedTodo)) {
            result += " editing";
        }
        return result;
    }

    List<Todo> getFilteredTodos(Filter f) {
        return todos.stream().filter(f.filter).collect(Collectors.toList());
    }

    List<Todo> getFilteredTodos() {
        return getFilteredTodos(currentFilter);
    }

    void toggleAll(Event e) {
        todos.forEach(todo -> todo.completed = (((HTMLInputElement) e.getTarget()).isChecked()));
    }

    int getRemaining() {
        return getFilteredTodos(Filter.Active).size();
    }

    @Override
    public HTMLElement asElement() {
        return binder.getRoot();
    }

    enum Filter {
        All(e -> true),
        Active(e -> !e.completed),
        Completed(e -> e.completed);
        private Predicate<Todo> filter;

        Filter(Predicate<Todo> filter) {
            this.filter = filter;
        }
    }

    @JSBody(params = {"id", "title", "completed"}, script = "return {id: id, title: title, completed: completed}")
    private native static JsonTodo createJsonTodo(JSDate id, JSString title, JSBoolean completed);

}


class Todo {
    Date id = new Date();
    String title;
    boolean completed = false;

    public Todo(String title) {
        this.title = title;
    }

    public Todo(JsonTodo todo) {
        double time = JSDate.parse(todo.getId().toString());
        this.id = new Date((long) time);
        this.title = String.valueOf(todo.getTitle().stringValue());
        this.completed = todo.getCompleted().booleanValue();
    }
}

interface JsonTodo extends JSObject {

    @JSProperty
    JSDate getId();

    @JSProperty
    JSString getTitle();

    @JSProperty
    JSBoolean getCompleted();
}
