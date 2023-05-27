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

fun CatalogOfSurfaces() = WikiSection("Catalog of Surfaces") {
    +WikiSection("Elliptic Paraboloid") {
        +WikiSection("Standard Form") {
            +MathBlock("x^2+y^2=z")
            +br()
            +PrimaryFigure {
                +div(square, RCStyle(const(AnonymousCSSClass("height: 200px")))) {
                    +CabinetProjectedGraph3d(const(Graph3dProperties(
                        precision = 0.5,
                        fns = listOf(
                            Graph3dFunction(
                                fn = { x, y ->  (x.pow(2) + y.pow(2)) }
                            ),
                        )
                    )))
                }
            }
        }
       +WikiSection("Offset from Origin") {
           var z1 by ReactiveProperty(0.0)
           +MathBlock("x^2+y^2=z-z_1")
           +br()
           +PrimaryFigure {
               +div(square, RCStyle(const(AnonymousCSSClass("height: 200px")))) {
                   +CabinetProjectedGraph3d(const(Graph3dProperties(
                       precision = 0.5,
                       fns = listOf(
                           Graph3dFunction(
                               fn = { x, y ->  (x.pow(2) + y.pow(2)) + z1 }
                           ),
                       )
                   )))
               }
           }
           +Slider(
               _range = const(DecimalSliderRange(-2.0, 2.0, 0.05)),
               _value = ref { z1 },
               onSlide = { z1 = it },
               label = Math("z_1")
           )
       }
        +WikiSection("Reflected About XY-Plane") {
            +MathBlock("-x^2-y^2=z")
            +br()
            +PrimaryFigure {
                +div(square, RCStyle(const(AnonymousCSSClass("height: 200px")))) {
                    +CabinetProjectedGraph3d(const(Graph3dProperties(
                        precision = 0.5,
                        fns = listOf(
                            Graph3dFunction(
                                fn = { x, y ->  (-1 * x.pow(2) - y.pow(2)) }
                            ),
                        ))
                    ))
                }
            }
            +br()
            +MathBlock("""
                \begin{gather}
                -x^2-y^2=z \\
                \implies -1(x^2 + y^2) = z \\
                \implies x^2 + y^2 = \frac{z}{-1} \\
                \implies x^2 + y^2 = -z 
                \end{gather}
            """)
        }
    }

    +WikiSection("Hyperbolic Paraboloid") {
        +MathBlock("z = -x^2 + y^2")
        +br()
        +PrimaryFigure {
            +div(square, RCStyle(const(AnonymousCSSClass("height: 200px")))) {
                +CabinetProjectedGraph3d(const(Graph3dProperties(
                    fns = listOf(
                        Graph3dFunction(fn = { x, y ->
                            (-1 * x.pow(2)) + y.pow(2)
                        })
                    ))
                ))
            }
        }
    }

    +WikiSection("Sphere") {
        +WikiSection("Standard Form") {
            +MathBlock("r^2=x^2+y^2+z^2")
            +br()
            +PrimaryFigure {
                +div(square, RCStyle(const(AnonymousCSSClass("height: 200px")))) {
                    +CabinetProjectedGraph3d(const(Graph3dProperties(
                        precision = 0.5,
                        fns = listOf(
                            Graph3dFunction(
                                fn = { x, y -> sqrt(1 - x.pow(2) - y.pow(2)) }
                            ),
                            Graph3dFunction(
                                fn = { x, y -> - sqrt(1 - x.pow(2) - y.pow(2)) }
                            )
                        ))
                    ))
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
                    +CabinetProjectedGraph3d(const(Graph3dProperties(
                        precision = 0.5,
                        fns = listOf(
                            Graph3dFunction(
                                fn = { x, y -> sqrt(1.0.pow(2) - (x - x1).pow(2) - (y - y1).pow(2)) + z1 }
                            ),
                            Graph3dFunction(
                                fn = { x, y -> - sqrt(1.0.pow(2) - (x - x1).pow(2) - (y - y1).pow(2)) + z1 }
                            )
                        )
                    )))
                }
            }
            +div(RCStyle(const(AnonymousCSSClass("display: flex;")))) {
                +Slider(
                    _range = const(DecimalSliderRange(-2.0, 2.0, 0.05)),
                    _value = ref { x1 },
                    onSlide = { x1 = it },
                    label = Math("x_1")
                )
                +Slider(
                    _range = const(DecimalSliderRange(-2.0, 2.0, 0.05)),
                    _value = ref { y1 },
                    onSlide = { y1 = it },
                    label = Math("y_1")
                )
                +Slider(
                    _range = const(DecimalSliderRange(-2.0, 2.0, 0.05)),
                    _value = ref { z1 },
                    onSlide = { z1 = it },
                    label = Math("z_1")
                )
            }
        }
    }
}