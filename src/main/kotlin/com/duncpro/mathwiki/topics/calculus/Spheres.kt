package com.duncpro.mathwiki.topics.calculus

//import com.duncpro.mathwiki.graphics.Graph3d
//import com.duncpro.mathwiki.graphics.Graph3dFormat
//import com.duncpro.mathwiki.graphics.Graph3dFunction
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.square
import com.duncpro.webk.*
import kotlin.math.pow
import kotlin.math.sqrt

fun Spheres() = UI {
    WikiSection("Spheres") {
        +div(square) {
//            +Graph3d(const(Graph3dFormat(
//                _isUserScrollable = const(true),
//                _step = const(0.25),
//                _precision = const(0.075),
//                _fns = const(listOf(
//                    Graph3dFunction(
//                        fn = { x, y -> sqrt(1 - x.pow(2) - y.pow(2)) },
//                    ),
//                    Graph3dFunction(
//                        fn = { x, y -> 0 - sqrt(1 - x.pow(2) - y.pow(2)) },
//                    )
//                ))
//            )))
        }
    }
}