package com.duncpro.mathwiki.topics.trigonometry

import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun FundamentalTrigonometricFunctions() = WikiSection("Fundamental Trigonometric Functions") {
    +WikiSection("Cosine") {
        +p {
            +"Given the measure of the angle at the intersection of the hypotenuse and x-axis, the ";
            +Math("\\cos(\\theta)"); +" function returns the ratio of the length of the leg overlapping ";
            +"the x-axis to the length of the hypotenuse."
        }
        +p {
            +"The leg overlapping the x-axis is typically called the "; +b { +"adjacent" }; +" leg."
        }
        +p {
            +"On the unit circle, "; +Math("\\cos(\\theta)"); +" is equal the x-coordinate of the point intersecting ";
            +"the circle at angle "; +Math("\\theta"); +". This follows from the premise that the length of the ";
            +"hypotenuse of the right triangle of a circle is always constant with respect to the circle. Specifically, "
            +"the length of the hypotenuse is equal to the radius of the circle."
        }
        +MathBlock("\\cos(\\theta) = \\frac{\\text{adjacent}}{\\text{hypotenuse}}")
    }
    +WikiSection("Sin") {
        +p {
            +"Given the measure of the angle at the intersection of the hypotenuse and x-axis, the ";
            +Math("\\sin(\\theta)"); +" function returns the ratio of the length of the leg parallel to the ";
            +"y-axis to the length of the hypotenuse."
        }
        +p {
            +"The leg parallel with the y-axis is typically called the "; +b { +"opposite" }; +" leg."
        }
        +MathBlock("\\sin(\\theta) = \\frac{\\text{opposite}}{\\text{hypotenuse}}")
    }
}