package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.color
import com.duncpro.mathwiki.graphics.*
import com.duncpro.webk.*

fun EvenNumbers() = UI {
    div {
        +h1 { +"Even Numbers" }
        +MathBlock("f(n) = 2n")
        +Numberline(const(NumberlineFormat(
            _pointStyle = const { x -> NumberlinePointStyle(
                color = if (x % 2 == 0) color("blue") else null
            ) }
        )))
    }
}