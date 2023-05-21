package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.graphics.*
import com.duncpro.webk.*
import org.w3c.dom.HTMLInputElement

fun LinearEquations() = UI {
    var m by useLocalState(1)
    var b by useLocalState(0)

    div {
        +h1 { +"Linear Equations" }
        +MathBlock("y=mx+b")
        +br()
        +Graph2d(const(Graph2dFormat(
            _fns = const(listOf(
                Graph2dFunction(fn = { x -> (m * x) + b })
            ))
        )))
        +div(RCStyle(const(AnonymousCSSClass("display: flex;")))) {
            +label { +Math("m") }
            +input(
                attr(HTMLInputElement::type, const("range")),
                attr(HTMLInputElement::min, const("-10")),
                attr(HTMLInputElement::max, const("10")),
                attr(HTMLInputElement::step, const("1")),
                attr(HTMLInputElement::value, ref { "$m" }),
                handle(HTMLInputElement::oninput) { _, e -> m = e.value.toInt() }
            )
            +span { +ref { "$m" } }
            +span(RCStyle(const(AnonymousCSSClass("padding: 10px"))))
            +label { +Math("b") }
            +input(
                attr(HTMLInputElement::type, const("range")),
                attr(HTMLInputElement::min, const("-10")),
                attr(HTMLInputElement::max, const("10")),
                attr(HTMLInputElement::step, const("1")),
                attr(HTMLInputElement::value, ref { "$b" }),
                handle(HTMLInputElement::oninput) { _, e -> b = e.value.toInt() }
            )
            +span { +ref { "$b" } }
        }
    }
}