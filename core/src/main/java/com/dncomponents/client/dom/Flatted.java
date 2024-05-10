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

import org.teavm.jso.JSBody;

public class Flatted {

    static String stringifyFlatted(Object object) {
        if (isFlattedUnDefined()) {
            bigInt();
            includeFlatted();
        }
        return stringifyFlattedNative(object).toString();
    }

    @JSBody(params = {"obj"}, script = "return Flatted.stringify(obj)")
    private native static <T> T stringifyFlattedNative(Object obj);


    @JSBody(params = {}, script = "return (typeof self.Flatted === 'undefined')")
    private native static boolean isFlattedUnDefined();

    //language=js
    @JSBody(params = {}, script = "return  BigInt.prototype.toJSON = function() { return this.toString() }")
    private native static void bigInt();


    //language=js
    @JSBody(params = {}, script = "return self.Flatted = function (n) {\n" +
                                  "    \"use strict\";\n" +
                                  "    function t(n) {\n" +
                                  "        return t = \"function\" == typeof Symbol && \"symbol\" == typeof Symbol.iterator ? function (n) {\n" +
                                  "            return typeof n\n" +
                                  "        } : function (n) {\n" +
                                  "            return n && \"function\" == typeof Symbol && n.constructor === Symbol && n !== Symbol.prototype ? \"symbol\" : typeof n\n" +
                                  "        }, t(n)\n" +
                                  "    }\n" +
                                  "\n" +
                                  "    var r = JSON.parse, e = JSON.stringify, o = Object.keys, u = String, f = \"string\", i = {}, c = \"object\",\n" +
                                  "        a = function (n, t) {\n" +
                                  "            return t\n" +
                                  "        }, l = function (n) {\n" +
                                  "            return n instanceof u ? u(n) : n\n" +
                                  "        }, s = function (n, r) {\n" +
                                  "            return t(r) === f ? new u(r) : r\n" +
                                  "        }, y = function n(r, e, f, a) {\n" +
                                  "            for (var l = [], s = o(f), y = s.length, p = 0; p < y; p++) {\n" +
                                  "                var v = s[p], S = f[v];\n" +
                                  "                if (S instanceof u) {\n" +
                                  "                    var b = r[S];\n" +
                                  "                    t(b) !== c || e.has(b) ? f[v] = a.call(f, v, b) : (e.add(b), f[v] = i, l.push({k: v, a: [r, e, b, a]}))\n" +
                                  "                } else f[v] !== i && (f[v] = a.call(f, v, S))\n" +
                                  "            }\n" +
                                  "            for (var m = l.length, g = 0; g < m; g++) {\n" +
                                  "                var h = l[g], O = h.k, d = h.a;\n" +
                                  "                f[O] = a.call(f, O, n.apply(null, d))\n" +
                                  "            }\n" +
                                  "            return f\n" +
                                  "        }, p = function (n, t, r) {\n" +
                                  "            var e = u(t.push(r) - 1);\n" +
                                  "            return n.set(r, e), e\n" +
                                  "        }, v = function (n, e) {\n" +
                                  "            var o = r(n, s).map(l), u = o[0], f = e || a, i = t(u) === c && u ? y(o, new Set, u, f) : u;\n" +
                                  "            return f.call({\"\": i}, \"\", i)\n" +
                                  "        }, S = function (n, r, o) {\n" +
                                  "            for (var u = r && t(r) === c ? function (n, t) {\n" +
                                  "                return \"\" === n || -1 < r.indexOf(n) ? t : void 0\n" +
                                  "            } : r || a, i = new Map, l = [], s = [], y = +p(i, l, u.call({\"\": n}, \"\", n)), v = !y; y < l.length;) v = !0, s[y] = e(l[y++], S, o);\n" +
                                  "            return \"[\" + s.join(\",\") + \"]\";\n" +
                                  "\n" +
                                  "            function S(n, r) {\n" +
                                  "                if (v) return v = !v, r;\n" +
                                  "                var e = u.call(this, n, r);\n" +
                                  "                switch (t(e)) {\n" +
                                  "                    case c:\n" +
                                  "                        if (null === e) return e;\n" +
                                  "                    case f:\n" +
                                  "                        return i.get(e) || p(i, l, e)\n" +
                                  "                }\n" +
                                  "                return e\n" +
                                  "            }\n" +
                                  "        };\n" +
                                  "    return n.fromJSON = function (n) {\n" +
                                  "        return v(e(n))\n" +
                                  "    }, n.parse = v, n.stringify = S, n.toJSON = function (n) {\n" +
                                  "        return r(S(n))\n" +
                                  "    }, n\n" +
                                  "}({});")
    public native static void includeFlatted();
}
