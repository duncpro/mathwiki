package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.banner
import com.duncpro.mathwiki.controls.DecimalSliderRange
import com.duncpro.mathwiki.controls.Slider
import com.duncpro.mathwiki.graphics.*
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*
import org.w3c.dom.HTMLInputElement
import kotlin.math.exp

fun EulersConstant() = UI {
    var x1 by useLocalState(initialValue = 1.0)

    WikiSection("Euler's Constant") {
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
            _fns = const(listOf(
                Graph2dFunction(fn = { x -> exp(x) }),
                Graph2dFunction(fn = { x -> exp(x1) * (x - x1) + exp(x1) }, color = "blue")
            )),
        )))
        +Slider(
            label = Math("x_1"),
            _range = const(DecimalSliderRange(min = -3.0, max = 2.0, step = 0.1)),
            _value = ref { x1 },
            onSlide = { nextValue -> x1 = nextValue },
        )
    }
}