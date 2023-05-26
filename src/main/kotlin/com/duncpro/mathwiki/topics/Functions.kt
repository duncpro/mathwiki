package com.duncpro.mathwiki.topics

import com.duncpro.mathwiki.graphics.CabinetProjectedGraph3d
import com.duncpro.mathwiki.graphics.Graph2d
import com.duncpro.mathwiki.graphics.Graph2dFormat
import com.duncpro.mathwiki.graphics.Graph2dFunction
import com.duncpro.mathwiki.graphics.Graph3dFormat
import com.duncpro.mathwiki.graphics.Graph3dFunction
import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.graphics.Numberline
import com.duncpro.mathwiki.graphics.NumberlineFormat
import com.duncpro.mathwiki.graphics.NumberlinePointStyle
import com.duncpro.mathwiki.layout.PrimaryFigure
import com.duncpro.webk.*
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.square
import kotlin.math.pow
import kotlin.math.sqrt

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
    +WikiSection("Graphing Functions") {
        +p {
            +"Graphing a function over some interval entails building a visual representation of the function which ";
            +"depicts the association of each value in the function's domain, with the corresponding value in the ";
            +"function's codomain."
        }
        +WikiSection("One-Dimensional Functions") {
            +p {
                +"A one-dimensional function has no parameters, only a value. "
                +"Such functions can be graphed using a number line."
            }
            +MathBlock("f(x) = 0")
            +Numberline(const(NumberlineFormat(
                _pointStyle = const { n -> NumberlinePointStyle(color = if (n == 0) "red" else null ) }
            )))
        }
        +WikiSection("Two Dimensional Functions") {
            +p {
                +"Two-dimensional functions have one parameter."; +" Such functions can be graphed using a coordinate ";
                +"plane. The horizontal axis, denoted by "; +Math("x"); +", represents the value of the parameter. "
                +"The vertical axis, denoted by "; +Math("y"); +", represents the value of the function."
            }
            +MathBlock("f(x)=\\pm \\sqrt{1-x^2}")
            +br()
            +PrimaryFigure {
                +div(square, RCStyle(const(AnonymousCSSClass("height: 200px")))) {
                    +Graph2d(const(Graph2dFormat(
                        _step = const(0.5),
                        _fns = const(listOf(
                            Graph2dFunction(
                                fn = { x -> 0 + sqrt(1.0 - x.pow(2)) },
                            ),
                            Graph2dFunction(
                                fn = { x -> 0 - sqrt(1.0 - x.pow(2)) },
                            )
                        )),
                    )
                    ))
                }
            }
        }
        +WikiSection("Three Dimensional Functions") {
            +p {
                +"Three-dimensional functions have two-parameters. Therefore three axis are needed. Specifically...";
            }
            +ul {
                +li { +"An axis for the first parameter, typically denoted "; +Math("x"); +"." }
                +li { +"An axis for the second parameter, typically denoted "; +Math("y"); +"." }
                +li { +"An axis for the function's value, typically denoted "; +Math("z"); +"." }
            }
            +p {
                +"A two-dimensional function "; +Math("f(x)"); +" represents one level-surface in the family of ";
                +" level-surfaces represented by the three-dimensional function "; +Math("g(x,y)");
                +". Specifically, "; +Math("g(x, y)=f(x) - y"); +"."
            }
            +MathBlock("""
                g(x, y) = \pm \sqrt{1-x^2} - y
            """)
            +br()
            +CabinetProjectedGraph3d(const(Graph3dFormat(
                _precision = const(0.20),
                _fns = const(listOf(
                    Graph3dFunction(
                        fn = { x, y -> 0 + sqrt(1.0 - x.pow(2)) - y },
                    ),
                    Graph3dFunction(
                        fn = { x, y -> 0 - sqrt(1.0 - x.pow(2)) - y },
                    )
                ))
            )
            ))
            +p {
                +"When graphing a three-dimensional function, both parameters, "; +Math("x"); +", and "; +Math("y");
                +", are free. In other words, every possible combination of parameters is depicted. In this way, the ";
                +"graph of a three-dimensional function is a representation of that function's solutions over some ";
                +"interval.";
            }
            +p {
                +"The graph presented above is an infinite stack of circles, where the "; +Math("z"); +"-position"
                +" (altitude) of each circle is determined by the value of the free "; +Math("y"); +" parameter."
                +" For instance, when the free "; +Math("y"); +" parameter is assigned value zero, then the altitude ";
                +"of that point ("; +Math("z"); +") is simply equal to the value of the two-dimensional function ";
                +Math("f(x)"); +Math("."); +" As the free "; +Math("y"); +" increases the "; +Math("z"); +" "
                +"decreases and the point appears lower in space. Conversely, as "; +Math("y"); +" decreases, ";
                +"the "; +Math("z"); +" increases and the point appears higher in space."
            }
        }
        +WikiSection("Four Dimensional Functions") {
            +p {
                +"Four dimensional functions have 3 parameters, therefore four axis are needed. Specifically: "
                +Math("x, y, z"); +" and "; +Math("t"); + "."
                +" Since the fourth axis cannot be depicted as an offset in three-dimensional space, another visualization";
                +" technique must be used instead."; +" For instance, the fourth dimension could be depicted by ";
                +" an animated three-dimensional graph, or three-dimensional graph where the color of each point represents";
                +" the value of the fourth dimension."
            }
            var t by ReactiveProperty(0.0)

        }
    }
}