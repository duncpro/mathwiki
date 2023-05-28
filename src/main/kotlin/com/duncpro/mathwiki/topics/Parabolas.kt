package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.graphics.Graph2d
import com.duncpro.mathwiki.graphics.Graph2dFormat
import com.duncpro.mathwiki.graphics.Graph2dFunction
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*
import kotlin.math.pow
import kotlin.math.sqrt

fun Parabolas() = run {
    WikiSection("Parabolas") {
        +WikiSection("Standard Form") {
            +MathBlock("y=x^2")
            +br()
            +Graph2d(const(Graph2dFormat(
                _fns = const(listOf(
                    Graph2dFunction(fn = { x -> x.pow(2.0) })
                ))
            )))
        }
        +WikiSection("Rotation 90 degrees") {
            +MathBlock("""y=\pm\sqrt{x}""")
            +br()
            +Graph2d(const(
                Graph2dFormat(
                _fns = const(listOf(
                    Graph2dFunction(fn = { x -> sqrt(x) }),
                    Graph2dFunction(fn = { x -> -1 * sqrt(x) })
                )))
            ))
        }
        +WikiSection("Rotation -90 degrees") {
            +Graph2d()
        }
    }
}