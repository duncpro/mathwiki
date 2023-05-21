package com.duncpro.mathwiki

import com.duncpro.mathwiki.graphics.Numberline
import com.duncpro.mathwiki.topics.*
import com.duncpro.webk.*

fun TableOfContents() = UI {
    val limitWidth = useStyleClass(const(AnonymousCSSClass("""
        max-width: 780px;
    """)))

    val padWithWhitespace = useStyleClass(const(AnonymousCSSClass("""
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
    """)))


    div(padWithWhitespace) {
        +div(limitWidth) {
            +h1 { +"Personal Math Wiki" }
            +hr()
            +Functions()
            +hr()
            +Limits()
            +hr()
            +EvenNumbers()
            +hr()
            +OddNumbers()
            +hr()
            +EulersConstant()
            +hr()
            +LinearEquations()
        }
    }
}