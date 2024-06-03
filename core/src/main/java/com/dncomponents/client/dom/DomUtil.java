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

package com.dncomponents.client.dom;

import com.dncomponents.client.components.core.HTMLTemplateElement;
import com.dncomponents.client.components.core.events.CustomEvent;
import com.dncomponents.client.components.core.events.HandlerRegistration;
import com.dncomponents.client.dom.handlers.BaseEventListener;
import com.dncomponents.client.views.IsElement;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventTarget;
import org.teavm.jso.dom.html.*;
import org.teavm.jso.dom.xml.*;
import org.teavm.jso.json.JSON;

import java.util.*;


public class DomUtil {

    public static <T extends HTMLElement> T createElement(String tag) {
        return DomUtil.cast(HTMLDocument.current().createElement(tag));
    }

    public static void removeElement(Element element) {
        element.getParentNode().removeChild(element);
    }

    public static boolean contains(Node first, Node second) {
        // If either node is null, return false
        if (first == null || second == null) {
            return false;
        }

        // Traverse up the DOM tree from the second node to its ancestors
        Node current = second;
        while (current != null) {
            if (current == first) {
                // If the current ancestor matches the first node, return true
                return true;
            }
            // Move up to the parent node
            current = current.getParentNode();
        }

        // If the loop completes without finding a match, return false
        return false;
    }

    public static Element findFirstElement(Node node) {
        if (node == null || !node.hasChildNodes()) {
            return null;
        }

        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node child = childNodes.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) child;
            }
            Element element = findFirstElement(child);
            if (element != null) {
                return element;
            }
        }
        return null;
    }


    public static HTMLElement createH1() {
        return createElement("h1");
    }

    public static HTMLElement createH2() {
        return createElement("h2");
    }

    public static HTMLElement createH3() {
        return createElement("h3");
    }

    public static HTMLElement createH4() {
        return createElement("h4");
    }

    public static HTMLTemplateElement createTemplate() {
        return createElement("template");
    }

    public static HTMLElement createDiv() {
        return createElement("div");
    }

    public static HTMLBodyElement createBody() {
        return createElement("body");
    }

    public static HTMLElement createAddress() {
        return createElement("address");
    }

    public static HTMLElement createArticle() {
        return createElement("article");
    }

    public static HTMLElement createAside() {
        return createElement("aside");
    }

    public static HTMLElement createFooter() {
        return createElement("footer");
    }

    public static HTMLElement createHgroup() {
        return createElement("hgroup");
    }

    public static HTMLElement createHeader() {
        return createElement("header");
    }

    public static HTMLElement createNav() {
        return createElement("nav");
    }

    public static HTMLElement createSection() {
        return createElement("section");
    }

    public static HTMLElement createBlockquote() {
        return createElement("blockquote");
    }

    public static HTMLElement createDd() {
        return createElement("dd");
    }

    public static HTMLElement createDl() {
        return createElement("dl");
    }

    public static HTMLElement createDt() {
        return createElement("dt");
    }

    public static HTMLElement createFigcaption() {
        return createElement("figcaption");
    }

    public static HTMLElement createFigure() {
        return createElement("figure");
    }

    public static HTMLElement createHr() {
        return createElement("hr");
    }

    public static HTMLElement createLi() {
        return createElement("li");
    }

    public static HTMLElement createMain() {
        return createElement("main");
    }

    public static HTMLElement createOl() {
        return createElement("ol");
    }

    public static HTMLElement createP() {
        return createElement("p");
    }

    public static HTMLElement createPre() {
        return createElement("pre");
    }

    public static HTMLElement createUl() {
        return createElement("ul");
    }

    public static HTMLAnchorElement createA() {
        return createElement("a");
    }

    public static HTMLElement createAbbr() {
        return createElement("abbr");
    }

    public static HTMLElement createB() {
        return createElement("b");
    }

    public static HTMLElement createBr() {
        return createElement("br");
    }

    public static HTMLElement createCite() {
        return createElement("cite");
    }

    public static HTMLElement createCode() {
        return createElement("code");
    }

    public static HTMLElement createDfn() {
        return createElement("dfn");
    }

    public static HTMLElement createEm() {
        return createElement("em");
    }

    public static HTMLElement createI() {
        return createElement("i");
    }

    public static HTMLElement createKbd() {
        return createElement("kbd");
    }

    public static HTMLElement createMark() {
        return createElement("mark");
    }

    public static HTMLElement createQ() {
        return createElement("q");
    }

    public static HTMLElement createSmall() {
        return createElement("small");
    }

    public static HTMLElement createSpan() {
        return createElement("span");
    }

    public static HTMLElement createStrong() {
        return createElement("strong");
    }

    public static HTMLElement createSub() {
        return createElement("sub");
    }

    public static HTMLElement createSup() {
        return createElement("sup");
    }

    public static HTMLElement createTime() {
        return createElement("time");
    }

    public static HTMLElement createU() {
        return createElement("u");
    }

    public static HTMLElement createVar() {
        return createElement("var");
    }

    public static HTMLElement createWbr() {
        return createElement("wbr");
    }

    public static HTMLElement createArea() {
        return createElement("area");
    }

    public static HTMLAudioElement createAudio() {
        return createElement("audio");
    }

    public static HTMLImageElement createImg() {
        return createElement("img");
    }

    public static HTMLElement createMap() {
        return createElement("map");
    }

    public static HTMLElement createTrack() {
        return createElement("track");
    }

    public static HTMLVideoElement createVideo() {
        return createElement("video");
    }

    public static HTMLCanvasElement createCanvas() {
        return createElement("canvas");
    }

    public static HTMLElement createEmbed() {
        return createElement("embed");
    }

    public static HTMLElement createObject() {
        return createElement("object");
    }

    public static HTMLElement createParam() {
        return createElement("param");
    }

    public static HTMLSourceElement createSource() {
        return createElement("source");
    }

    public static HTMLElement createNoscript() {
        return createElement("noscript");
    }

    public static HTMLScriptElement createScript() {
        return createElement("script");
    }

    public static HTMLElement createDel() {
        return createElement("del");
    }

    public static HTMLElement createIns() {
        return createElement("ins");
    }

    public static HTMLElement createCaption() {
        return createElement("caption");
    }

    public static HTMLElement createCol() {
        return createElement("col");
    }

    public static HTMLElement createColgroup() {
        return createElement("colgroup");
    }

    public static HTMLElement createTable() {
        return createElement("table");
    }

    public static HTMLElement createTbody() {
        return createElement("tbody");
    }

    public static HTMLElement createTd() {
        return createElement("td");
    }

    public static HTMLElement createTfoot() {
        return createElement("tfoot");
    }

    public static HTMLElement createTh() {
        return createElement("th");
    }

    public static HTMLElement createThead() {
        return createElement("thead");
    }

    public static HTMLElement createTr() {
        return createElement("tr");
    }

    public static HTMLButtonElement createButton() {
        return createElement("button");
    }

    public static HTMLElement createDatalist() {
        return createElement("datalist");
    }

    public static HTMLElement createFieldset() {
        return createElement("fieldset");
    }

    public static HTMLFormElement createForm() {
        return createElement("form");
    }

    public static HTMLElement createLabel() {
        return createElement("label");
    }

    public static HTMLElement createLegend() {
        return createElement("legend");
    }

    public static HTMLElement createMeter() {
        return createElement("meter");
    }

    public static HTMLElement createOptgroup() {
        return createElement("optgroup");
    }

    public static HTMLOptionElement createOption() {
        return createElement("option");
    }

    public static HTMLElement createOutput() {
        return createElement("output");
    }

    public static HTMLElement createProgress() {
        return createElement("progress");
    }

    public static HTMLSelectElement createSelect() {
        return createElement("select");
    }

    public static HTMLTextAreaElement createTextarea() {
        return createElement("textarea");
    }

    public static HTMLElement createDiv(String innerHtml) {
        HTMLElement div = createDiv();
        div.setInnerHTML(innerHtml);
        return div;
    }

    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    public static <T extends Element> T getElementById(String id) {
        return cast(HTMLDocument.current().getElementById(id));
    }

    public static <T extends HTMLElement> T createRoot(String id, String tag) {
        Element el = HTMLDocument.current().createElement(tag);
        el.setAttribute("id", id);
        return cast(el);
    }

    public static void addToBody(IsElement element) {
        addToBody(element.asElement());
    }


    public static void addToBody(HTMLElement element) {
        HTMLDocument.current().getBody().appendChild(element);
    }

    /**
     * Replaces element with style preserved.
     *
     * @param n1 element that replaces n2
     * @param n2 element to be replaced
     */
    public static void replace(Object n1, Object n2) {
        if (n1 == null || n2 == null) return;
        DomUtil.copyAllAttributes(asHtmlElement(n2), asHtmlElement(n1));
        replace(asHtmlElement(n1), asHtmlElement(n2));
    }

    public static <T> T replaceRaw(Object n1, Object n2) {
        if (n1 == null || n2 == null) throw new NullPointerException("Elements can't be null");
        replaceRaw(asHtmlElement(n1), asHtmlElement(n2));
        return (T) n1;
    }

    public static HTMLElement asHtmlElement(Object obj) throws IllegalArgumentException {
        HTMLElement n1;
        if (obj instanceof IsElement)
            n1 = ((IsElement) obj).asElement();
        else if (obj instanceof HTMLElement)
            n1 = (HTMLElement) obj;
        else
            throw new IllegalArgumentException("Arguments must be HTMLElement or IsElement type!");
        return n1;
    }

    /**
     * @param n1 to be set
     * @param n2 to be removed and replaced
     */
    public static void replace(HTMLElement n1, HTMLElement n2) {
        Node parent = n2.getParentNode();
        n1.setClassName(n1.getClassName() + " " + n2.getClassName());
        n1.setAttribute("style", n2.getAttribute("style"));
        parent.replaceChild(n1, n2);
    }

    /**
     * Replace element without style preserved
     *
     * @param n1 to be set
     * @param n2 to be removed and replaced
     */
    public static void replaceRaw(Element n1, Element n2) {
        Node parent = n2.getParentNode();
        parent.replaceChild(n1, n2);
    }

    public static Node replaceRawNodes(Node n1, Node n2) {
        Node parent = n2.getParentNode();
        return parent.replaceChild(n1, n2);
    }

    public static Node unwrap(Element element) {
        DocumentFragment fragment = HTMLDocument.current().createDocumentFragment();
        while (element.getFirstChild() != null) {
            fragment.appendChild(element.getFirstChild());
        }
        return element.getParentNode().replaceChild(fragment, element);
    }

    public static void wrap(HTMLElement el, HTMLElement wrapper) {
        el.getParentNode().insertBefore(wrapper, el);
        wrapper.appendChild(el);
    }

    public static void copyStyle(HTMLElement element1, HTMLElement element2) {
        String style = element1.getClassName();
        if (style != null)
            element2.setClassName(style);
    }

    public static List<Attr> getAttributes(Element element) {
        List<Attr> attrList = new ArrayList<>();
        for (int i = 0; i < element.getAttributes().getLength(); i++) {
            final Attr attr = element.getAttributes().get(i);
            attrList.add(attr);
        }
        return attrList;
    }

    public static List<String> getAttributesNames(Element element) {
        List<String> attrList = new ArrayList<>();
        for (int i = 0; i < element.getAttributes().getLength(); i++) {
            final Attr attr = element.getAttributes().get(i);
            attrList.add(attr.getName());
        }
        return attrList;
    }

    public static void copyAllAttributes(Element element1, Element element2) {
        for (Attr attr : getAttributes(element1)) {
            element2.setAttribute(attr.getName(), element1.getAttribute(attr.getName()));
        }
    }

    public static void setVisible(HTMLElement element, boolean visible) {
        element.getStyle().setProperty("display", visible ? "" : "none");
    }

    public static void setElementStyleProperty(HTMLElement element, String property, String value) {
        element.getStyle().setProperty(property, value);
    }

    public static void setPadding(HTMLElement element, String padding) {
        setElementStyleProperty(element, "padding", padding);
    }

    public static void setPaddingLeft(HTMLElement element, String padding) {
        setElementStyleProperty(element, "padding-left", padding);
    }

    public static void setPaddingRight(HTMLElement element, String padding) {
        setElementStyleProperty(element, "padding-right", padding);
    }

    public static void setPaddingBottom(HTMLElement element, String padding) {
        setElementStyleProperty(element, "padding-bottom", padding);
    }

    public static void setPaddingTop(HTMLElement element, String padding) {
        setElementStyleProperty(element, "padding-top", padding);
    }

    public static void setBackgroundColor(HTMLElement element, String color) {
        setElementStyleProperty(element, "background-color", color);
    }

    public static void setMaxHeight(HTMLElement element, String height) {
        setElementStyleProperty(element, "max-height", height);
    }

    public static void setMinHeight(HTMLElement element, String height) {
        setElementStyleProperty(element, "min-height", height);
    }

    public static void setHeight(HTMLElement element, String height) {
        setElementStyleProperty(element, "height", height);
    }

    public static void setWidth(HTMLElement element, String width) {
        setElementStyleProperty(element, "width", width);
    }

    public static void setAlignContent(HTMLElement element, String value) {
        setElementStyleProperty(element, "align-content", value);
    }

    public static void alignItems(HTMLElement element, String value) {
        setElementStyleProperty(element, "align-items", value);
    }

    public static void alignSelf(HTMLElement element, String value) {
        setElementStyleProperty(element, "align-self", value);
    }

    public static void azimuth(HTMLElement element, String value) {
        setElementStyleProperty(element, "azimuth", value);
    }

    public static void backfaceVisibility(HTMLElement element, String value) {
        setElementStyleProperty(element, "backface-visibility", value);
    }

    public static void background(HTMLElement element, String value) {
        setElementStyleProperty(element, "background", value);
    }

    public static void backgroundAttachment(HTMLElement element, String value) {
        setElementStyleProperty(element, "background-attachment", value);
    }

    public static void backgroundColor(HTMLElement element, String value) {
        setElementStyleProperty(element, "background-color", value);
    }

    public static void backgroundImage(HTMLElement element, String value) {
        setElementStyleProperty(element, "background-image", value);
    }

    public static void backgroundPosition(HTMLElement element, String value) {
        setElementStyleProperty(element, "background-position", value);
    }

    public static void backgroundRepeat(HTMLElement element, String value) {
        setElementStyleProperty(element, "background-repeat", value);
    }

    public static void backgroundSize(HTMLElement element, String value) {
        setElementStyleProperty(element, "background-size", value);
    }

    public static void border(HTMLElement element, String value) {
        setElementStyleProperty(element, "border", value);
    }

    public static void borderBottom(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-bottom", value);
    }

    public static void borderBottomColor(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-bottom-color", value);
    }

    public static void borderBottomLeftRadius(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-bottom-left-radius", value);
    }

    public static void borderBottomRightRadius(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-bottom-right-radius", value);
    }

    public static void borderBottomStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-bottom-style", value);
    }

    public static void borderBottomWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-bottom-width", value);
    }

    public static void borderCollapse(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-collapse", value);
    }

    public static void borderColor(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-color", value);
    }

    public static void borderImage(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-image", value);
    }

    public static void borderImageOutset(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-image-outset", value);
    }

    public static void borderImageRepeat(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-image-repeat", value);
    }

    public static void borderImageSlice(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-image-slice", value);
    }

    public static void borderImageSource(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-image-source", value);
    }

    public static void borderImageWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-image-width", value);
    }

    public static void borderLeft(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-left", value);
    }

    public static void borderTopColor(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-top-color", value);
    }

    public static void borderTop(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-top", value);
    }

    public static void borderStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-style", value);
    }

    public static void borderSpacing(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-spacing", value);
    }

    public static void borderRightWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-right-width", value);
    }

    public static void borderRightStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-right-style", value);
    }

    public static void borderRightColor(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-right-color", value);
    }

    public static void borderRight(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-right", value);
    }

    public static void borderRadius(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-radius", value);
    }

    public static void borderLeftWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-left-width", value);
    }

    public static void borderLeftStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-left-style", value);
    }

    public static void borderLeftColor(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-left-color", value);
    }

    public static void borderWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-width", value);
    }

    public static void borderTopWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-top-width", value);
    }

    public static void borderTopStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-top-style", value);
    }

    public static void borderTopRightRadius(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-top-right-radius", value);
    }

    public static void borderTopLeftRadius(HTMLElement element, String value) {
        setElementStyleProperty(element, "border-top-left-radius", value);
    }

    public static void bottom(HTMLElement element, String value) {
        setElementStyleProperty(element, "bottom", value);
    }

    public static void boxShadow(HTMLElement element, String value) {
        setElementStyleProperty(element, "box-shadow", value);
    }

    public static void boxSizing(HTMLElement element, String value) {
        setElementStyleProperty(element, "box-sizing", value);
    }

    public static void captionSide(HTMLElement element, String value) {
        setElementStyleProperty(element, "caption-side", value);
    }

    public static void clear(HTMLElement element, String value) {
        setElementStyleProperty(element, "clear", value);
    }

    public static void clip(HTMLElement element, String value) {
        setElementStyleProperty(element, "clip", value);
    }

    public static void color(HTMLElement element, String value) {
        setElementStyleProperty(element, "color", value);
    }

    public static void content(HTMLElement element, String value) {
        setElementStyleProperty(element, "content", value);
    }

    public static void counterIncrement(HTMLElement element, String value) {
        setElementStyleProperty(element, "counter-increment", value);
    }

    public static void counterReset(HTMLElement element, String value) {
        setElementStyleProperty(element, "counter-reset", value);
    }

    public static void cssFloat(HTMLElement element, String value) {
        setElementStyleProperty(element, "css-float", value);
    }

    public static void cssText(HTMLElement element, String value) {
        setElementStyleProperty(element, "css-text", value);
    }

    public static void cue(HTMLElement element, String value) {
        setElementStyleProperty(element, "cue", value);
    }

    public static void cueAfter(HTMLElement element, String value) {
        setElementStyleProperty(element, "cue-after", value);
    }

    public static void cueBefore(HTMLElement element, String value) {
        setElementStyleProperty(element, "cue-before", value);
    }

    public static void cursor(HTMLElement element, String value) {
        setElementStyleProperty(element, "cursor", value);
    }

    public static void direction(HTMLElement element, String value) {
        setElementStyleProperty(element, "direction", value);
    }

    public static void display(HTMLElement element, String value) {
        setElementStyleProperty(element, "display", value);
    }

    public static void elevation(HTMLElement element, String value) {
        setElementStyleProperty(element, "elevation", value);
    }

    public static void emptyCells(HTMLElement element, String value) {
        setElementStyleProperty(element, "empty-cells", value);
    }

    public static void flex(HTMLElement element, String value) {
        setElementStyleProperty(element, "flex", value);
    }

    public static void flexBasis(HTMLElement element, String value) {
        setElementStyleProperty(element, "flex-basis", value);
    }

    public static void flexDirection(HTMLElement element, String value) {
        setElementStyleProperty(element, "flex-direction", value);
    }

    public static void flexFlow(HTMLElement element, String value) {
        setElementStyleProperty(element, "flex-flow", value);
    }

    public static void flexGrow(HTMLElement element, String value) {
        setElementStyleProperty(element, "flex-grow", value);
    }

    public static void flexShrink(HTMLElement element, String value) {
        setElementStyleProperty(element, "flex-shrink", value);
    }

    public static void flexWrap(HTMLElement element, String value) {
        setElementStyleProperty(element, "flex-wrap", value);
    }

    public static void font(HTMLElement element, String value) {
        setElementStyleProperty(element, "font", value);
    }

    public static void fontFamily(HTMLElement element, String value) {
        setElementStyleProperty(element, "font-family", value);
    }

    public static void fontSize(HTMLElement element, String value) {
        setElementStyleProperty(element, "font-size", value);
    }

    public static void fontSizeAdjust(HTMLElement element, String value) {
        setElementStyleProperty(element, "font-size-adjust", value);
    }

    public static void fontStretch(HTMLElement element, String value) {
        setElementStyleProperty(element, "font-stretch", value);
    }

    public static void fontStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "font-style", value);
    }

    public static void fontVariant(HTMLElement element, String value) {
        setElementStyleProperty(element, "font-variant", value);
    }

    public static void fontWeight(HTMLElement element, String value) {
        setElementStyleProperty(element, "font-weight", value);
    }

    public static void height(HTMLElement element, String value) {
        setElementStyleProperty(element, "font-weight", value);
    }

    public static void justifyContent(HTMLElement element, String value) {
        setElementStyleProperty(element, "justify-content", value);
    }

    public static void left(HTMLElement element, String value) {
        setElementStyleProperty(element, "left", value);
    }

    public static void letterSpacing(HTMLElement element, String value) {
        setElementStyleProperty(element, "letter-spacing", value);
    }

    public static void lineHeight(HTMLElement element, String value) {
        setElementStyleProperty(element, "line-height", value);
    }

    public static void listStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "list-style", value);
    }

    public static void listStyleImage(HTMLElement element, String value) {
        setElementStyleProperty(element, "list-style-image", value);
    }

    public static void listStylePosition(HTMLElement element, String value) {
        setElementStyleProperty(element, "list-style-position", value);
    }

    public static void listStyleType(HTMLElement element, String value) {
        setElementStyleProperty(element, "list-style-type", value);
    }

    public static void margin(HTMLElement element, String value) {
        setElementStyleProperty(element, "margin", value);
    }

    public static void marginBottom(HTMLElement element, String value) {
        setElementStyleProperty(element, "margin-bottom", value);
    }

    public static void marginLeft(HTMLElement element, String value) {
        setElementStyleProperty(element, "margin-left", value);
    }

    public static void marginRight(HTMLElement element, String value) {
        setElementStyleProperty(element, "margin-right", value);
    }

    public static void marginTop(HTMLElement element, String value) {
        setElementStyleProperty(element, "margin-top", value);
    }

    public static void markerOffset(HTMLElement element, String value) {
        setElementStyleProperty(element, "marker-offset", value);
    }

    public static void marks(HTMLElement element, String value) {
        setElementStyleProperty(element, "marks", value);
    }

    public static void maxHeight(HTMLElement element, String value) {
        setElementStyleProperty(element, "max-height", value);
    }

    public static void maxWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "max-width", value);
    }

    public static void minHeight(HTMLElement element, String value) {
        setElementStyleProperty(element, "min-height", value);
    }

    public static void minWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "min-width", value);
    }

    public static void opacity(HTMLElement element, String value) {
        setElementStyleProperty(element, "opacity", value);
    }

    public static void order(HTMLElement element, String value) {
        setElementStyleProperty(element, "order", value);
    }

    public static void orphans(HTMLElement element, String value) {
        setElementStyleProperty(element, "orphans", value);
    }

    public static void outlineColor(HTMLElement element, String value) {
        setElementStyleProperty(element, "outline-color", value);
    }

    public static void outlineStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "outline-style", value);
    }

    public static void outlineWidth(HTMLElement element, String value) {
        setElementStyleProperty(element, "outline-width", value);
    }

    public static void overflow(HTMLElement element, String value) {
        setElementStyleProperty(element, "overflow", value);
    }

    public static void padding(HTMLElement element, String value) {
        setElementStyleProperty(element, "padding", value);
    }

    public static void paddingBottom(HTMLElement element, String value) {
        setElementStyleProperty(element, "padding-bottom", value);
    }

    public static void paddingLeft(HTMLElement element, String value) {
        setElementStyleProperty(element, "padding-left", value);
    }

    public static void paddingRight(HTMLElement element, String value) {
        setElementStyleProperty(element, "padding-Right", value);
    }

    public static void paddingTop(HTMLElement element, String value) {
        setElementStyleProperty(element, "padding-Top", value);
    }

    public static void page(HTMLElement element, String value) {
        setElementStyleProperty(element, "page", value);
    }

    public static void pageBreakAfter(HTMLElement element, String value) {
        setElementStyleProperty(element, "page-break-after", value);
    }

    public static void pageBreakBefore(HTMLElement element, String value) {
        setElementStyleProperty(element, "page-break-before", value);
    }

    public static void pageBreakInside(HTMLElement element, String value) {
        setElementStyleProperty(element, "page-break-inside", value);
    }

    public static void pause(HTMLElement element, String value) {
        setElementStyleProperty(element, "pause", value);
    }

    public static void pauseAfter(HTMLElement element, String value) {
        setElementStyleProperty(element, "pause-after", value);
    }

    public static void pauseBefore(HTMLElement element, String value) {
        setElementStyleProperty(element, "pause-before", value);
    }

    public static void perspective(HTMLElement element, String value) {
        setElementStyleProperty(element, "perspective", value);
    }

    public static void perspectiveOrigin(HTMLElement element, String value) {
        setElementStyleProperty(element, "perspective-origin", value);
    }

    public static void pitch(HTMLElement element, String value) {
        setElementStyleProperty(element, "pitch", value);
    }

    public static void pitchRange(HTMLElement element, String value) {
        setElementStyleProperty(element, "pitch-range", value);
    }

    public static void playDuring(HTMLElement element, String value) {
        setElementStyleProperty(element, "play-during", value);
    }

    public static void pointerEvents(HTMLElement element, String value) {
        setElementStyleProperty(element, "pointer-events", value);
    }

    public static void position(HTMLElement element, String value) {
        setElementStyleProperty(element, "position", value);
    }

    public static void quotes(HTMLElement element, String value) {
        setElementStyleProperty(element, "quotes", value);
    }

    public static void resize(HTMLElement element, String value) {
        setElementStyleProperty(element, "resize", value);
    }

    public static void richness(HTMLElement element, String value) {
        setElementStyleProperty(element, "richness", value);
    }

    public static void size(HTMLElement element, String value) {
        setElementStyleProperty(element, "size", value);
    }

    public static void speak(HTMLElement element, String value) {
        setElementStyleProperty(element, "speak", value);
    }

    public static void speakHeader(HTMLElement element, String value) {
        setElementStyleProperty(element, "speak-header", value);
    }

    public static void speakNumeral(HTMLElement element, String value) {
        setElementStyleProperty(element, "speak-numeral", value);
    }

    public static void speakPunctuation(HTMLElement element, String value) {
        setElementStyleProperty(element, "speak-punctuation", value);
    }

    public static void speechRate(HTMLElement element, String value) {
        setElementStyleProperty(element, "speech-rate", value);
    }

    public static void stress(HTMLElement element, String value) {
        setElementStyleProperty(element, "stress", value);
    }

    public static void tableLayout(HTMLElement element, String value) {
        setElementStyleProperty(element, "table-layout", value);
    }

    public static void textAlign(HTMLElement element, String value) {
        setElementStyleProperty(element, "text-align", value);
    }

    public static void textDecoration(HTMLElement element, String value) {
        setElementStyleProperty(element, "text-decoration", value);
    }

    public static void textIndent(HTMLElement element, String value) {
        setElementStyleProperty(element, "text-indent", value);
    }

    public static void textOverflow(HTMLElement element, String value) {
        setElementStyleProperty(element, "text-overflow", value);
    }

    public static void textShadow(HTMLElement element, String value) {
        setElementStyleProperty(element, "text-shadow", value);
    }

    public static void textTransform(HTMLElement element, String value) {
        setElementStyleProperty(element, "text-transform", value);
    }

    public static void top(HTMLElement element, String value) {
        setElementStyleProperty(element, "top", value);
    }

    public static void transform(HTMLElement element, String value) {
        setElementStyleProperty(element, "transform", value);
    }

    public static void transformOrigin(HTMLElement element, String value) {
        setElementStyleProperty(element, "transform-origin", value);
    }

    public static void transformStyle(HTMLElement element, String value) {
        setElementStyleProperty(element, "transform-style", value);
    }

    public static void transition(HTMLElement element, String value) {
        setElementStyleProperty(element, "transition", value);
    }

    public static void transitionDelay(HTMLElement element, String value) {
        setElementStyleProperty(element, "transition-delay", value);
    }

    public static void transitionDuration(HTMLElement element, String value) {
        setElementStyleProperty(element, "transition-duration", value);
    }

    public static void transitionProperty(HTMLElement element, String value) {
        setElementStyleProperty(element, "transition-property", value);
    }

    public static void transitionTimingFunction(HTMLElement element, String value) {
        setElementStyleProperty(element, "transition-timing-function", value);
    }

    public static void unicodeBidi(HTMLElement element, String value) {
        setElementStyleProperty(element, "unicode-bidi", value);
    }

    public static void verticalAlign(HTMLElement element, String value) {
        setElementStyleProperty(element, "vertical-align", value);
    }

    public static void visibility(HTMLElement element, String value) {
        setElementStyleProperty(element, "visibility", value);
    }

    public static void voiceFamily(HTMLElement element, String value) {
        setElementStyleProperty(element, "voiceFamily", value);
    }

    public static void volume(HTMLElement element, String value) {
        setElementStyleProperty(element, "volume", value);
    }

    public static void whiteSpace(HTMLElement element, String value) {
        setElementStyleProperty(element, "whiteSpace", value);
    }

    public static void widows(HTMLElement element, String value) {
        setElementStyleProperty(element, "widows", value);
    }

    public static void width(HTMLElement element, String value) {
        setElementStyleProperty(element, "width", value);
    }

    public static void willChange(HTMLElement element, String value) {
        setElementStyleProperty(element, "will-change", value);
    }

    public static void wordSpacing(HTMLElement element, String value) {
        setElementStyleProperty(element, "word-spacing", value);
    }

    public static void wordWrap(HTMLElement element, String value) {
        setElementStyleProperty(element, "word-wrap", value);
    }

    public static void zIndex(HTMLElement element, String value) {
        setElementStyleProperty(element, "z-index", value);
    }


    //Json


    public static String getStringFromObject(Object obj) {
        return JSON.stringify((JSObject) obj);
    }

    public static <T> T getObjectFromJsonString(String json) {
        return (T) JSON.parse(json);
    }
    //end Json


    public static void setMaxWidth(HTMLElement element, String width) {
        element.getStyle().setProperty("max-width", width);
    }

    public static void setMinWidth(HTMLElement element, String width) {
        element.getStyle().setProperty("min-width", width);
    }

    public static Map<String, String> getAllAttributes(Element html) {
        Map<String, String> map = new HashMap<>();
        for (String s : getAttributesNames(html)) {
            map.put(s, html.getAttribute(s));
        }
        return map;
    }

    public static HandlerRegistration addHandler(HTMLElement element, BaseEventListener handler) {
        return handler.addTo(element);
    }

    public static boolean isDescendant(Element parent, Element child) {
        Node node = child.getParentNode();
        while (node != null) {
            if (node == parent) {
                return true;
            }
            node = node.getParentNode();
        }
        return false;
    }

    public static boolean isChildOf(Element el, Element o) {
        NodeList<Node> childNodes = el.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.get(i) == (o)) {
                return true;
            }
        }
        return false;
    }


    private static void checkArgumentsAndTrim(HTMLElement element, String style) {
        if (element == null) {
            throw new RuntimeException("Element can't be null!");
        }
        style = style.trim();
        if (style.length() == 0) {
            throw new IllegalArgumentException("Style names cannot be empty");
        }
    }

    public static void addStyle(HTMLElement element, String style) {
        checkArgumentsAndTrim(element, style);
        element.getClassList().remove(style);
    }

    public static void removeStyle(HTMLElement element, String style) {
        checkArgumentsAndTrim(element, style);
        element.getClassList().remove(style);
    }

    public static void setStyle(HTMLElement element, String style) {
        checkArgumentsAndTrim(element, style);
        element.setClassName(style);
    }

    public static void setStyle(IsElement element, String style) {
        setStyle(element.asElement(), style);
    }

    public static String escapeHtml(String html) {
        final Text textNode = HTMLDocument.current().createTextNode(html);
        HTMLElement p = createDiv();
        p.appendChild(textNode);
        return p.getInnerHTML();
    }

    public static void fireCustomEvent(HTMLElement element, String type, Object detail) {
        CustomEvent customEvent = CustomEvent.createCustomEvent(type, detail);
        element.dispatchEvent(customEvent);
    }

    public static <T> T getValueFromEvent(Event evt) {
        return (T) ((CustomEvent) evt).getDetail();
    }

    public static <P> P getSelection(EventTarget eventTarget) {
        HTMLSelectElement element = (HTMLSelectElement) eventTarget;
        if (element.isMultiple()) {
            List<String> values = new ArrayList<>();
            for (int i = 0; i < element.getOptions().getLength(); i++) {
                HTMLOptionElement option = element.getOptions().item(i);
                if (option.isSelected()) {
                    values.add(option.getValue());
                }
            }
            return (P) values;
        } else {
            HTMLOptionElement selectedOption = (HTMLOptionElement) element.getOptions()
                    .get(element.getSelectedIndex());
            // Get the value of the selected option
            String selectedValue = selectedOption.getValue();
            return (P) selectedValue;
        }
    }

    public static <T> T checkBoxSelection(Object obj, Event e) {
        if (obj instanceof Collection) {
            return (T) DomUtil.checkBoxCollectionSelection(e.getTarget(), (Collection<String>) obj);
        } else if (obj instanceof Boolean) {
            return (T) (Boolean.valueOf(((HTMLInputElement) e.getTarget()).isChecked()));
        }else{
            return (T) ((HTMLInputElement) e.getTarget()).getValue();
        }
    }

    private static Collection<String> checkBoxCollectionSelection(EventTarget eventTarget, Collection<String> selection) {
        HTMLInputElement element = (HTMLInputElement) eventTarget;
        String value = element.getValue();
        boolean containsIgnoreCase = selection.stream()
                .anyMatch(str -> str.equalsIgnoreCase(value));
        if (element.isChecked()) {
            if (!containsIgnoreCase) {
                selection.add(value);
            }
        } else {
            selection.removeIf(str -> str.equalsIgnoreCase(value));
        }
        return selection;
    }

    //call method that is missing with arguments
    @JSBody(params = {"element", "methodName", "args"}, script = "return element[methodName](arg)")
    public native static <T> T callMethod(Object element, String methodName, Object... arg);

    @JSBody(params = {"element", "methodName"}, script = "return element[methodName]()")
    public native static <T> T callMethod(Object element, String methodName);

    //call field that is missing
    @JSBody(params = {"element", "fieldName"}, script = "return element[fieldName]")
    public native static <T> T getValue(Object element, String fieldName);

    public static String stringifyFlatted(Object object) {
        return Flatted.stringifyFlatted(object);
    }

    public static String stringifyFlattedArr(Object object) {
        return Flatted.stringifyFlatted(getValue(object, "$array0"));
    }

    @JSBody(params = {"obj"}, script = "return console.log(obj)")
    public native static void log(Object obj);

    @JSBody(params = {"s"}, script = "return alert(s)")
    public native static void alert(String s);
}
