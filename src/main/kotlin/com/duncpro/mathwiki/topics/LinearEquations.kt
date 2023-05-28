package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.controls.DecimalSliderRange
import com.duncpro.mathwiki.controls.Slider
import com.duncpro.mathwiki.graphics.*
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*
import org.w3c.dom.HTMLInputElement

fun LinearEquations() = run {
    var m by ReactiveProperty(1)
    var b by ReactiveProperty(0)

    WikiSection("Linear Equations") {
        +MathBlock("y=mx+b")
        +br()
        +Graph2d(const(Graph2dFormat(
            _fns = const(listOf(
                Graph2dFunction(fn = { x -> (m * x) + b })
            ))
        )))
        +div(RCStyle(const(AnonymousCSSClass("display: flex;")))) {
            +Slider(
                label = Math("m"),
                _range = const(DecimalSliderRange(-10.0, 10.0, 1.0)),
                _value = ref { m.toDouble() },
                onSlide = { m = it.toInt() }
            )
            +span(RCStyle(const(AnonymousCSSClass("padding: 10px"))))
            +Slider(
                label = Math("b"),
                _range = const(DecimalSliderRange(-10.0, 10.0, 1.0)),
                _value = ref { b.toDouble() },
                onSlide = { b = it.toInt() }
            )
        }
    }
}