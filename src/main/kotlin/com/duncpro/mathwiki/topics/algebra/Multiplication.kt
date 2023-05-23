package com.duncpro.mathwiki.topics.algebra

import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun Multiplication() = WikiSection("Multiplication") {
    +WikiSection("Definition") {
        +MathBlock("x * y = 0\\space[+x\\space[\\text{repeated } y \\text{ times}]]")
        +p {
            +"Multiplication is repeated addition."
        }
    }
}