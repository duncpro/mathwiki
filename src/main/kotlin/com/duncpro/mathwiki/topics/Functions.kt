package com.duncpro.mathwiki.topics

import com.duncpro.webk.*
import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection

fun Functions() = WikiSection("Functions") {
    +WikiSection("Equality of Functions") {
        +p { +"Two functions "; +Math("f(x), g(x)"); +" are equivalent if the following criteria are met..." }
        +ul {
            +li {
                +"The co-domains of each function are equivalent. In other words, the set of values which "
                +Math("f(x)"); +" can possibly produce is equal to the set of values which "; +Math("g(x)")
                +" can possibly produce."
            }
            +li {
                +"The domains of each function are equivalent. In other words, the set of values for which "
                +Math("f(x)"); +" is defined is equal to the set of values for which "; +Math("g(x)")
                +" is defined."
            }
            +li {
                +"For every value "; +Math("a"); +" in the domain, "; +Math("f(a)=g(a)"); +"."
            }
        }
    }
    +WikiSection("Simplification through Division does not imply Equality") {
        +MathBlock("""
                \begin{align} 
                    f(x) =& \frac{x^2-1}{x-1} = \frac{(x+1)(x-1)}{x-1} \\
                    g(x) =& x+1
                \end{align}
            """)
        +p {
            +"The aforementioned functions are not strictly equivalent because their domains differ. "
            +"Specifically because "; +Math("f(1)"); +" is undefined while "; +Math("g(1) = 2"); +"."
        }
    }
}