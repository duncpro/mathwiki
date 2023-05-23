package com.duncpro.mathwiki.topics.algebra

import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun Exponentiation() = WikiSection("Exponentiation") {
    +WikiSection("Definition") {
        +MathBlock("x^y = 1 \\text { [} * x \\text{ [repeated } y \\text{ times ]]}")
        +p {
            +"Exponentiation is to multiplication what multiplication is to addition. "
            +"Put more simply, exponentiation is repeated multiplication."
        }
        +p {
            +"By this definition, "; +Math("x^0"); +" is equal to the multiplicative identity, 1."
        }
    }
    +WikiSection("Product of Same-Base Exponentials") {
        +MathBlock("""
            x^a * x^b
            = 1 \text { [} * x \text{ [repeated } a \text{ times ]] }
            * 1 \text { [} * x \text{ [repeated } b \text{ times ]]}
            = x^{a+b}
        """)
        +p {
            +"The product of two exponentials which share a common base is equal to that base raised to the "
            +"sum of the exponents."
        }
    }
    +WikiSection("Quotient of Same-Base Exponentials") {
        +MathBlock("""
            \frac{x^a}{x^b}
            =1 \text { [} * x \text{ [ repeated } a \text{ times ]] }
            \text { [}\div x \text{ [ repeated } b \text{ times ]] }
            = x^{a-b}
        """)
        +p {
            +"This identity follows from the premise that"; +b { +" division is the inverse of multiplication. " }
            +"Each division by "; +Math("x"); +" removes exactly 1 multiple of "; +Math("x"); +" "
            +"from the quotient. Therefore, "; +Math("b"); +" divisions by "; +Math("x"); +" "
            +"remove exactly "; +Math("b"); +" multiples of "; +Math("x"); +" from the quotient. "
            +"Hence, "; +Math("a - b"); +" multiples remain."
        }
    }
    +WikiSection("Composition") {
        +MathBlock("""
            (x^a)^b = 1 * \text { [ } 1 \text { [} * x \text{ [ repeated } a \text{ times ]] } \text { [ repeated } b \text{ times ]]}
            = x^{a * b}
        """)
    }
}