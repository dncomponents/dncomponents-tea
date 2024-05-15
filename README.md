# dncomponents


&nbsp;&nbsp;Client side java UI framework for building rich web applications written purely in Java language using [TeaVM](https://teavm.org/) compiler without any external js libraries.
http://dncomponents.com

Note: There is the same project using [GWT](https://www.gwtproject.org/) compiler [here](https://github.com/dncomponents/dncomponents)


| Module                                |                                                                      Info |
|---------------------------------------|--------------------------------------------------------------------------:|
| <b>core</b>                           |                 Basic classes to build user interface with dn HTML binder |
| <b> template_annotation_processor</b> |                Annotation processor to generate code from java/html pairs |
| <b> mvp</b>                           |                                                   Lightweight MVP pattern |
| <b>core-uui</b>                       |                                                Components of dncomponents |
| <b>bootstrap-ui</b>                   | Views implementation of dncomponents in bootstrap front-end framework</b> |
| <b>main</b>                           |                             Testing of components and HTMLBinder     </b> |
| <b>main-reactive</b>                  |                                                   Tests reactive features |

## Getting started

See [project web site](https://dncomponents.com/index.html).

### To run components examples

Get a stable set of components, same as you can see at  [dncompoentns bootstrap example](https://dncomponents.com/demo/index.html).

Clone the project `git clone https://github.com/dncomponents/dncomponents-tea.git`

`cd main` and `run mvn clean package` 

Open `index-package.html` to see output.

To debug follow instruction on [teavm tooling section](https://teavm.org/docs/tooling/idea.html)

### To test new reactivity features

This attempts to bring some features of popular frameworks - Vue, React, and Angular, such as:

* **Reactivity** Data-binding system that synchronize the state of the application with DOM.<br>
* **Component based** Organizing code into reusable UI components encapsulating their own logic, styling, and structure. <br>
* **Two-way data binding** Changes in the model affect the view and vice versa. <br>
* **Loops** Iterating over arrays and objects using the **dn-loop** directive, handy for rendering lists and tables dynamically in the DOM. <br>
* **Conditional Rendering** With **dn-if, dn-if-else dn-else** directives, you can conditionally render elements based on the value of expressions or data properties.


Read more [here](https://github.com/dncomponents/dncomponents-tea/blob/master/main-reactive/README.md)

Note that this is still work in progress and we need feedback and more testing.

cd `main-reactivity` and `run mvn clean package`

Open `index-package.html` to see output.

To debug follow instruction on [teavm tooling section](https://teavm.org/docs/tooling/idea.html)


## Contact
Join gitter room https://app.gitter.im/#/room/#dncomponents.com:gitter.im<br>
support@dncomponents.com

## License

This project is licensed under

* [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
