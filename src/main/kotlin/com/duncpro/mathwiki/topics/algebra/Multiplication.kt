package com.duncpro.mathwiki.topics.algebra

import com.duncpro.mathwiki.controls.DecimalSliderRange
import com.duncpro.mathwiki.controls.Slider
import com.duncpro.mathwiki.graphics.Graph2d
import com.duncpro.mathwiki.graphics.Graph2dFormat
import com.duncpro.mathwiki.graphics.Graph2dFunction
import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.PrimaryFigure
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun Multiplication() = WikiSection("Multiplication") {
    +WikiSection("Definition") {
        +p { +"Multiplication is repeated addition." }
        +MathBlock("x * y = 0\\space[+x\\space[\\text{repeated } y \\text{ times}]]")
    }
    +WikiSection("One Negative Factor") {
        +p { +"When one factor is negative, multiplication is repeated subtraction." }
        +MathBlock("-x * y = 0\\space[-x\\space[\\text{repeated } y \\text{ times}]]")
        +br()
        +p { +"This definition also holds true when "; +Math("y"); +" is the negative factor." }
        +MathBlock("x * -y = 0\\space[+x\\space[\\text{repeated } -y \\text{ times}]]")
        +p {
            +"Performing addition a "; +i { +"negative number of times"; };
            +" can be described as performing the inverse of addition that number of times. The inverse of addition is"
            +" subtraction. Therefore..."
        }
        +MathBlock("x * -y = 0\\space[-x\\space[\\text{repeated } y \\text{ times}]]")
        +p { +"Therefore, "; +Math("x * -y = -x * y"); }
    }
    +WikiSection("Two Negative Factors") {
        +p {
            +"Multiplication of two negative factors is therefore the inverse of repeated subtraction. ";
            +"The inverse of repeated subtraction is repeated addition. Assuming an initial quantity of zero, ";
            +"any subsequent addition to that quantity will yield a total quantity at least as large as the initial quantity. ";
            +"Therefore the product of two negatives must be a positive number or zero."
        }
    }
    +WikiSection("Geometric Definition") {
        +p {
            +"The product of two positive quantities "; +Math("x * y = p"); +" can be visualized in two-dimensional space ";
            +"on a coordinate plane. Each square on the coordinate plane represents a unit, 1. ";
            +"The product "; +Math("p"); +" can be computed by simply counting the squares after the x-axis ";
            +" but before the "; +" parallel vertical axis at "; +Math("x"); +". But not below the y-axis ";
            +" and not above the parallel horizontal axis at "; +Math("y"); +"."
        }
        var x by ReactiveProperty(1); var y by ReactiveProperty(1)
        +div(RCStyle(const(AnonymousCSSClass("display: flex;")))) {
            +Slider(
                _range = const(DecimalSliderRange(0.0, 9.0, 1.0)),
                _value = ref { x.toDouble() },
                onSlide = { x = it.toInt() },
                label = Math("x")
            )
            +Slider(
                _range = const(DecimalSliderRange(0.0, 9.0, 1.0)),
                _value = ref { y.toDouble() },
                onSlide = { y = it.toInt() },
                label = Math("y")
            )
        }
        +PrimaryFigure {
            +Graph2d(const(Graph2dFormat(
                _fns = const(listOf(
                    Graph2dFunction(
                        fn = {  if (it in 0.01..x.toDouble()) y.toDouble() else Double.NaN  }
                    )
                )),
                _inverseFns = const(listOf(
                    Graph2dFunction(
                        fn = {  if (it in 0.01..y.toDouble()) x.toDouble() else Double.NaN  }
                    )
                ))
            )))
        }
    }
    +WikiSection("The Square of a Number") {
        +p {
            +"The quantity "; +Math("x"); +" multiplied by itself "; +" is the quantity ";
            +b { +Math("x"); +" squared" }
            +". This term follows from the geometric depiction of the quantity in two-dimensional space."
        }
    }
}