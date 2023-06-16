package com.duncpro.mathwiki.topics.trigonometry

import com.duncpro.mathwiki.CurrentTheme
import com.duncpro.mathwiki.controls.DecimalSliderRange
import com.duncpro.mathwiki.controls.Slider
import com.duncpro.mathwiki.graphics.*
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*
import kotlin.math.*

fun Circles() = WikiSection("Circles") {
    +WikiSection("Standard Form") {
        +MathBlock("x^2+y^2=r^2")
        +p {
            +"The standard form of a circle can be derived from the Pythagorean Theorem. Specifically, by holding ";
            +"the hypotenuse constant. The length of the hypotenuse is called the radius, often abbreviated as ";
            +Math("r"); +"."
        }
    }
    +WikiSection("Decomposition of Standard Form") {
        +div {
            +span(RCStyle(const(AnonymousCSSClass("color: green")))) {
                +Math("y=0+\\sqrt{r^2-x^2}")
            }
            +span { +", " }
            +span(RCStyle(const(AnonymousCSSClass("color: red")))) {
                +Math("y=0-\\sqrt{r^2-x^2}")
            }
        }
        +br()
        +Graph2d(const(Graph2dFormat(
            _fns = const(listOf(
                Graph2dFunction(
                    fn = { x -> 0 + sqrt(1.0 - x.pow(2)) },
                    color = "green"
                ),
                Graph2dFunction(
                    fn = { x -> 0 - sqrt(1.0 - x.pow(2)) },
                    color = "red"
                )
            )),
            _step = const(0.25),
            _precision = const(0.001)
        )))
    }
    +WikiSection("Domain of Circular Function") {
        +p {
            +"A circular function, that is, a function whose graph is a circle, has domain "; +Math("|x| \\le |r|"); +". "
            +"This is made evident by solving the equation for "; +Math("y"); +"."
        }
        +MathBlock("""
           y^2=r^2-x^2
           \implies \sqrt{y^2}=\sqrt{r^2-x^2}
           \implies y = \pm \sqrt{r^2-x^2}
        """)
        +p {
            +"No real number "; +Math("n"); +" exists such that "; +Math("n^2 < 0"); +", therefore ";
            +Math("r^2-x^2 \\ge 0"); + ". Finally,"
        }
        +MathBlock("""
           r^2-x^2 \ge 0 \implies r^2 \ge x^2 \implies |r| \ge |x| \implies |x| \le |r|
        """)
    }
    +UnitCircle()
}

private fun UnitCircle() = run {
    var angle by ReactiveProperty(1.0 / 4.0 * PI)
    WikiSection("The Unit Circle") {
        +p {
            +"The circle centered at the origin with a radius of 1 is often called the Unit Circle. "
            +"Like all circles, the equation for the Unit Circle is derived from the pythagorean theorem. "
        }
        +MathBlock("1=x^2+y^2")
        +br()
        +span(RCStyle(const(AnonymousCSSClass("color: blue")))) {
            +Math(ref { Tex("\\cos(\\theta)=") })
            +ref { "${cos(angle)}".take(4) }
        }
        +span { +" " }
        +span(RCStyle(const(AnonymousCSSClass("color: green")))) {
            +Math(ref { Tex("\\sin(\\theta)=") })
            +ref { "${sin(angle)}".take(4) }
        }
        +Graph2d(const(Graph2dFormat(
            _fns = const(listOf(
                Graph2dFunction(
                    fn = { x -> 0 + sqrt(1.0 - x.pow(2)) },
                    color = "black"
                ),
                Graph2dFunction(
                    fn = { x -> 0 - sqrt(1.0 - x.pow(2)) },
                    color = "black"
                ),
            )),
            _step = const(0.25),
            _precision = const(0.001),
            _lineSegments = ref {
                listOf(
                    Graph2dLineSegment(
                        x1 = 0.0, y1 = 0.0,
                        x2 = cos(angle), y2 = sin(angle),
                    ),
                    Graph2dLineSegment(
                        x1 = cos(angle), y1 = 0.0,
                        x2 = cos(angle), y2 = sin(angle),
                        color = "green"
                    ),
                    Graph2dLineSegment(
                        x1 = 0.0, y1 = 0.0,
                        x2 = cos(angle), y2 = 0.0,
                        color = "blue"
                    )
                )
            }
        )))
        +Slider(
            _range = const(DecimalSliderRange(0.0, 2 * PI, 0.01)),
            _value = ref { angle },
            onSlide = { angle = it },
            label = Math("\\theta")
        )
    }
}