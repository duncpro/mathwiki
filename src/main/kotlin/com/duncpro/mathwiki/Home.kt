package com.duncpro.mathwiki

import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.topics.*
import com.duncpro.mathwiki.topics.algebra.Algebra
import com.duncpro.mathwiki.topics.algebra.Inequalities
import com.duncpro.mathwiki.topics.calculus.Calculus
import com.duncpro.mathwiki.topics.calculus.Integrals
import com.duncpro.mathwiki.topics.numbers.Numbers
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
                +Numbers()
                +Algebra()
                +Functions()
                +Calculus()
                +LinearEquations()
                +Circles()
                +Parabolas()
                +Inequalities()
                +PythagoreanTheorem()
            }
        }
    }
}