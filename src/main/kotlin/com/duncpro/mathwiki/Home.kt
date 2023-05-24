package com.duncpro.mathwiki

import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.topics.*
import com.duncpro.mathwiki.topics.algebra.Algebra
import com.duncpro.mathwiki.topics.algebra.Inequalities
import com.duncpro.mathwiki.topics.calculus.Calculus
import com.duncpro.mathwiki.topics.numbers.Numbers
import com.duncpro.mathwiki.topics.projection.Projection
import com.duncpro.mathwiki.topics.trigonometry.Circles
import com.duncpro.mathwiki.topics.trigonometry.PythagoreanTheorem
import com.duncpro.mathwiki.topics.trigonometry.Trigonometry
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
                +Projection()
                +Calculus()
                +LinearEquations()
                +Parabolas()
                +Inequalities()
                +Trigonometry()
                +EulersConstant()
            }
        }
    }
}