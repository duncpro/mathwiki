package com.duncpro.mathwiki.topics.trigonometry

import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun FundamentalTrigonometricFunctions() = WikiSection("Fundamental Trigonometric Functions") {
    +WikiSection("Cosine") {
        +"The trigonometric function "; +b { +"cosine" }; +" describes the relationship between the angle of"
        +MathBlock("\\cos(\\theta) = \\frac{\\text{adjacent}}{\\text{hypotenuse}}")
    }
    +WikiSection("Sin") {

    }
}