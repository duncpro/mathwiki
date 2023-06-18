package com.duncpro.mathwiki.topics.algebra

import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun Addition() = WikiSection("Addition") {
    +WikiSection("Intuitive Definition") {
        +p {
            +"Combining two groups "; +Math("A"); +" and "; +Math("B"); +" produces a single group ";
            +Math("C"); +" containing the elements of both. "; +" This is addition."
        }
        +p {
            +"For instance, a first group containing three apples, combined with a second group containing five apples ";
            +"yields a group containing "; +Math("3 + 5"); +" apples."
        }
    }
}