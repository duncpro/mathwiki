package com.duncpro.mathwiki.topics.numbers

import com.duncpro.mathwiki.color
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.graphics.Numberline
import com.duncpro.mathwiki.graphics.NumberlineFormat
import com.duncpro.mathwiki.graphics.NumberlinePointStyle
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun OddNumbers() = UI {
    WikiSection("Odd Numbers") {
        +MathBlock("f(n) = 2n + 1")
        +Numberline(const(NumberlineFormat(
            _pointStyle = const { x -> NumberlinePointStyle(
                color = if (x % 2 != 0) color("blue") else null
            ) }
        )))
    }
}