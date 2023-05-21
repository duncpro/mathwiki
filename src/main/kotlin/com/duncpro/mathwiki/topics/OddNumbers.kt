package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.color
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.graphics.Numberline
import com.duncpro.mathwiki.graphics.NumberlineFormat
import com.duncpro.mathwiki.graphics.NumberlinePointStyle
import com.duncpro.webk.*

fun OddNumbers() = UI {
    div {
        +h1 { +"Odd Numbers" }
        +MathBlock("f(n) = 2n + 1")
        +Numberline(const(NumberlineFormat(
            _pointStyle = const { x -> NumberlinePointStyle(
                color = if (x % 2 != 0) color("blue") else null
            ) }
        )))
    }
}