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
    +WikiSection("Sharing Problems") {
        +"Division can be considered a sharing problem. Given "; +Math("y"); +" empty buckets, and ";
        +"a starting pool of "; +Math("x"); +" elements... How many elements will end up in each bucket, "
        +"assuming they are shared evenly?"
    }
    +WikiSection("Grouping Problems") {
        +"Division can be considered a grouping problem. Given a pool of "; +Math("x"); +" elements, many groups of ";
        +Math("y"); +" elements can be made?"
    }
    +WikiSection("Comparison") {
        +"In "; +b { +"sharing" }; +" problems, "; +Math("y"); +" is the number of groups."
        +"In "; +b { +"grouping" }; + " problems, "; +Math("y"); +" is the quantity of elements per group. ";
    }
}