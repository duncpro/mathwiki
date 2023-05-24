package com.duncpro.mathwiki.topics.calculus

import com.duncpro.mathwiki.graphics.CabinetProjected3DGraph
import com.duncpro.mathwiki.graphics.Graph3dFormat
import com.duncpro.mathwiki.graphics.Graph3dFunction
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.PrimaryFigure
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.square
import com.duncpro.webk.*
import kotlin.math.pow
import kotlin.math.sqrt

fun Spheres() = UI {
    WikiSection("Spheres") {
        +MathBlock("r^2=x^2+y^2+z^2")
        +br()
        +PrimaryFigure {
            +div(square, RCStyle(const(AnonymousCSSClass("height: 200px")))) {
                +CabinetProjected3DGraph(const(Graph3dFormat(
                    _precision = const(0.4),
                    _fns = const(listOf(
                        Graph3dFunction(
                            fn = { x, y -> sqrt(50.0.pow(2) - x.pow(2) - y.pow(2)) }
                        ),
                        Graph3dFunction(
                            fn = { x, y -> - sqrt(50.0.pow(2) - x.pow(2) - y.pow(2)) }
                        )
                    ))
                )))
            }
        }
    }
}