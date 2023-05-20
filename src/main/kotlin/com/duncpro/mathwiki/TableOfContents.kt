package com.duncpro.mathwiki

import com.duncpro.mathwiki.graphics.Numberline
import com.duncpro.webk.*
import com.duncpro.mathwiki.topics.Functions
import com.duncpro.mathwiki.topics.Limits

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
            +Numberline()
        }
    }
}