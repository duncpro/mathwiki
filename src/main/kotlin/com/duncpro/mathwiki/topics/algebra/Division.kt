package com.duncpro.mathwiki.topics.algebra

import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun Division() = WikiSection("Division") {
    +WikiSection("Practical Definition") {
        +MathBlock("""
        x \div y = x\space[-y\space[\text{ repeated }n\text{ times } ]]
    """)
        +p {
            +"Division is repeated subtraction. Or in a more abstract sense, finding the amount of times one number ";
            +Math("y"); +" fits inside another, "; +Math("x"); + "."
        }
    }
    +WikiSection("Grouping Problems") {
        +"Division can be considered a grouping problem. How many groups, with "; +Math("y"); +" elements ";
        +"in each group, can be made from a pool of "; +Math("x"); + " total elements?";
    }
    +WikiSection("Sharing Problems") {
        +"Division can be considered a sharing problem. For "; +Math("y"); +" groups, how many elements "
        +"will be in each group, assuming a starting pool of "; +Math("x"); +" elements."
    }
}