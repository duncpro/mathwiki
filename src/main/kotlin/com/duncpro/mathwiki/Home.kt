package com.duncpro.mathwiki

import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.topics.*
import com.duncpro.webk.*

fun Home() = UI {
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
            +WikiSection("Personal Math Wiki") {
                +EulersConstant()
                +EvenNumbers()
                +Functions()
                +Limits()
                +LinearEquations()
                +OddNumbers()
                +Circles()
                +Parabolas()
                +Derivatives()
                +Integrals()
                +Inequalities()
            }
        }
    }
}