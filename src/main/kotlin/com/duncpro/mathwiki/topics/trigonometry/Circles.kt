package com.duncpro.mathwiki.topics.trigonometry

import com.duncpro.mathwiki.graphics.*
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*
import kotlin.math.pow
import kotlin.math.sqrt

fun Circles() = WikiSection("Circles") {
    +WikiSection("Standard Form") {
        +MathBlock("x^2+y^2=r^2")
        +p {
            +"The standard form of a circle can be derived from the Pythagorean Theorem. Specifically, by holding ";
            +"the hypotenuse constant. The length of the hypotenuse is called the radius, often abbreviated as ";
            +Math("r"); +"."
        }
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
    +WikiSection("Decomposition of Standard Form") {
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
    +WikiSection("The Unit Circle") {
        +p {
            +"The circle centered at the origin with a radius of 1 is often called the Unit Circle."
        }
    }
}