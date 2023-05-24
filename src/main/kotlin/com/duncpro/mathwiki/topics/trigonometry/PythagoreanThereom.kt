package com.duncpro.mathwiki.topics.trigonometry

import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun PythagoreanTheorem() = WikiSection("Pythagorean Theorem") {
    +p {
        +"The pythagorean theorem describes the relationship between the length of the two non-hypotenuse "
        +"legs of a right triangle, and the length of the hypotenuse. It is given by the following equation..."
    }
    +MathBlock("a^2+b^2=c^2")
    +label { +"where..." }
    +ul {
        +li {
            +Math("a"); +" is the length of the first non-hypotenuse leg of the triangle."
        }
        +li {
            +Math("b"); +" is the length of the second non-hypotenuse leg of the triangle."
        }
        +li {
            +Math("c"); +" is the length of the hypotenuse."
        }
    }
}