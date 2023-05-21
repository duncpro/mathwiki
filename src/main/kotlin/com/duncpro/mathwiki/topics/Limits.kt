package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.bold
import com.duncpro.mathwiki.centerText
import com.duncpro.webk.*
import com.duncpro.mathwiki.graphics.Math

fun Limits() = UI {
    div {
        +h1 { +"Limits" }
        +p(centerText) {
            +"The limit of "; +Math("f(x)"); +" as "; +Math("x"); +" approaches "; +Math("c")
            +" is "; +Math("y"); +"."
        }
        +p {
            +"The statement above is actually shorthanded for a logical conjunction of the following two statements..."
        }
        +ul {
            +li {
                +"The limit of "; +Math("f(x)"); +" as "; +Math("x"); +" approaches "; +Math("c")
                +" from the "; +span(bold) { +"negative direction " }; +"is "; +Math("y"); +"."
            }
            +li {
                +"The limit of "; +Math("f(x)"); +" as "; +Math("x"); +" approaches "; +Math("c")
                +" from the "; +span(bold) { +"positive direction " }; +"is "; +Math("y"); +"."
            }
        }
    }
}

