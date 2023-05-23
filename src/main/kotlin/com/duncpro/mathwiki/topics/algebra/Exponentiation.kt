package com.duncpro.mathwiki.topics.algebra

import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun Exponentiation() = WikiSection("Exponentiation") {
    +WikiSection("Definition") {
        +MathBlock("x^y = 1 \\text { (} * x \\text{ [repeated } y \\text{ times ])}")
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
            = 1 \text { (} * x \text{ [repeated } a \text{ times ]) }
            * 1 \text { (} * x \text{ [repeated } b \text{ times ])}
            = x^{a+b}
        """)
        +p {
            +"The product of two exponentials which share a common base is equal to that base raised to the "
            +"sum of the exponents."
        }
    }
    +WikiSection("Quotient of Same-Base Exponentials") {
        +MathBlock("\\frac{x^a}{x^b} = x^{a-b}")
    }
    +WikiSection("Composition") {
        +MathBlock("(x^a)^b = x^{a * b}")
    }
}