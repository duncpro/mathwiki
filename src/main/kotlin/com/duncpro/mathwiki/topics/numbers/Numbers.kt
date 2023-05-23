package com.duncpro.mathwiki.topics.numbers

import com.duncpro.mathwiki.layout.WikiSection

fun Numbers() = WikiSection("Numbers") {
    +EvenNumbers()
    +OddNumbers()
    +EulersConstant()
    +ComplexNumbers()
}