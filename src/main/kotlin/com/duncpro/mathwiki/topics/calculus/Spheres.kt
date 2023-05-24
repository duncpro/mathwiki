package com.duncpro.mathwiki.topics.calculus

import com.duncpro.mathwiki.controls.DecimalSliderRange
import com.duncpro.mathwiki.controls.Slider
import com.duncpro.mathwiki.graphics.*
import com.duncpro.mathwiki.layout.PrimaryFigure
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.square
import com.duncpro.webk.*
import kotlin.math.pow
import kotlin.math.sqrt

fun Spheres() = UI {
    WikiSection("Spheres") {

        +WikiSection("Standard Form") {
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

        +WikiSection("Offset from Origin") {
            var x1 by ReactiveProperty(0.0)
            var y1 by ReactiveProperty(0.0)
            var z1 by ReactiveProperty(0.0)

            +MathBlock("r^2=(x-x_1)^2+(y-y_1)^2+(z-z_1)^2")
            +br()
            +PrimaryFigure {
                +div(square, RCStyle(const(AnonymousCSSClass("height: 200px")))) {
                    +CabinetProjected3DGraph(const(Graph3dFormat(
                        _precision = const(0.4),
                        _fns = const(listOf(
                            Graph3dFunction(
                                fn = { x, y -> sqrt(50.0.pow(2) - (x - x1).pow(2) - (y - y1).pow(2)) + z1 }
                            ),
                            Graph3dFunction(
                                fn = { x, y -> - sqrt(50.0.pow(2) - (x - x1).pow(2) - (y - y1).pow(2)) + z1 }
                            )
                        ))
                    )))
                }
            }
            +div(RCStyle(const(AnonymousCSSClass("display: flex;")))) {
                +Slider(
                    _range = const(DecimalSliderRange(-200.0, 200.0, 1.0)),
                    _value = ref { x1 },
                    onSlide = { x1 = it },
                    label = Math("x_1")
                )
                +Slider(
                    _range = const(DecimalSliderRange(-200.0, 200.0, 1.0)),
                    _value = ref { y1 },
                    onSlide = { y1 = it },
                    label = Math("y_1")
                )
                +Slider(
                    _range = const(DecimalSliderRange(-200.0, 200.0, 1.0)),
                    _value = ref { z1 },
                    onSlide = { z1 = it },
                    label = Math("z_1")
                )
            }
        }
    }
}