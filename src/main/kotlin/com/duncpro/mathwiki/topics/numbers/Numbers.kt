package com.duncpro.mathwiki.topics.numbers

import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.topics.EulersConstant

fun Numbers() = WikiSection("Numbers") {
    +EvenNumbers()
    +OddNumbers()
    +ComplexNumbers()
}