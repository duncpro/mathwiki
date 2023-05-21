package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.banner
import com.duncpro.mathwiki.graphics.*
import com.duncpro.webk.*
import org.w3c.dom.HTMLInputElement
import kotlin.math.exp

fun EulersConstant() = UI {
    var x1 by useLocalState(initialValue = 1.0)
    div(banner) {
        +h1 { +"Euler's Constant" }
        +p {
            +span(RCStyle(const(AnonymousCSSClass("color: red")))) {
                +Math("f(x)=e^x")
            }
            +", "
            +span(RCStyle(const(AnonymousCSSClass("color: blue")))) {
                +Math("y=f'(x_1)[x-x_1]+e^{x_1}")
            }
        }
        +br()
        +Graph2d(const(Graph2dFormat(
            _fns = ref {
                listOf(
                    Graph2dFunction(fn = { x -> exp(x) }),
                    Graph2dFunction(fn = { x -> exp(x1) * (x - x1) + exp(x1) }, color = "blue")
                )
            },
        )))
        +div(RCStyle(const(AnonymousCSSClass("display: flex")))) {
            +label { +Math("x_1") }
            +input(
                attr(HTMLInputElement::type, const("range")),
                attr(HTMLInputElement::min, const("-3")),
                attr(HTMLInputElement::max, const("2")),
                attr(HTMLInputElement::step, const("0.1")),
                attr(HTMLInputElement::value, ref { "$x1" }),
                handle(HTMLInputElement::oninput) { _, e -> x1 = e.value.toDouble() })
            +span { +ref { "$x1" } }
        }
    }
}