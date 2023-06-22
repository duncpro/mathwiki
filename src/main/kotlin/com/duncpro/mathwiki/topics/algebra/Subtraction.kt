package com.duncpro.mathwiki.topics.algebra

import com.duncpro.mathwiki.centerText
import com.duncpro.mathwiki.controls.Slider
import com.duncpro.mathwiki.controls.SliderRange
import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.graphics.Numberline
import com.duncpro.mathwiki.graphics.NumberlineFormat
import com.duncpro.mathwiki.graphics.NumberlinePointStyle
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.webk.*

fun Subtraction() = WikiSection("Subtraction") {
    +p {
        +"The difference between "; +Math("a"); +" and "; +Math("b"); +" can be found by subtracting "
        +Math("b"); +" from "; +Math("a"); +"."
    }
    +WikiSection("Geometric Definition") {
        +p {
            +"On a numberline, the difference between "; +Math("a"); +" and "; +Math("b"); +" represents "
            +"the direction and magnitude which one must travel from "; +Math("b"); +" in order to reach "; +Math("a")
        }
        var a by ReactiveProperty(initialValue = 1.toDouble())
        var b by ReactiveProperty(initialValue = (-1).toDouble())
        +div(RCStyle(const(AnonymousCSSClass("display: flex")))) {
            +Slider(
                _range = const(SliderRange(min = -10, max = 10, step = 1, String::toDouble)),
                _value = ref { a },
                onSlide = { a = it.toDouble() },
                label = span(RCStyle(const(AnonymousCSSClass("color: red")))) {
                    +i { +"a" }
                }
            )
            +Slider(
                _range = const(SliderRange(min = -10, max = 10, step = 1, String::toDouble)),
                _value = ref { b },
                onSlide = { b = it.toDouble() },
                label = span(RCStyle(const(AnonymousCSSClass("color: blue")))) {
                    +i { +"b" }
                }
            )
        }
        +p(RCStyle(const(AnonymousCSSClass("width: 100%; text-align: center;")))) {
            +i { +"a" }
            +" - "
            +i { +"b" }
            +" = "
            +ref { "${a - b}" }
        }
        +Numberline(_format = const(NumberlineFormat(
            _pointStyle = const { x ->
                if (x.toDouble() == a) return@const NumberlinePointStyle(color = "red")
                if (x.toDouble() == b) return@const NumberlinePointStyle(color = "blue")
                return@const NumberlinePointStyle()
            },
            _label = const { x ->
                if (x.toDouble() == a) return@const "a"
                if (x.toDouble() == b) return@const "b"
                return@const ""
            }
        )))
    }

    +p {
        +"If "; +Math("a > b"); +" then one must travel in the positive direction from "; +Math("b"); +" to reach ";
        +Math("a"); +"."; +br(); +"This is reflected in the difference "; +Math("a - b = c"); +" since ";
        +Math("""c \gt 0"""); +"."
    }
    +p {
        +"Conversely, if "; +Math("b > a"); +" then one must travel in the negative direction from "; +Math("b");
        +" to reach "; +Math("a"); +"."; +br(); +"This is reflected in the difference "; +Math("a - b = c"); +" since ";
        +Math("""c \lt 0"""); +"."
    }
    +p {
        +"The geometric definition of subtraction can be applied to subtraction in multiple dimensions as well."
        +" Specifically "; +Math("""\vec{a}-\vec{b}"""); +" is equal to "; +Math("""=\vec{c}"""); +", "
        +"the magnitude and direction which one must travel from "; +Math("""\vec{b}"""); +" in order to reach ";
        +Math("""\vec{a}""")
    }
    +p {
        +"Therefore, the difference of two "; +Math("a-b"); +" can be depicted graphically as a vector ";
        +"originating at zero and terminating at "; +Math("a"); +", with another vector originating at "; +Math("a");
        +" and terminating at "; +Math("a-b"); +". The the magnitude of the second vector is of course ";
        +Math("b"); +"."
    }
    +p {
        +"The idea that the difference "; +Math("a-b=c"); +" is equal to an adjustment on "; +Math("b");
        +" in order to reach "; +Math("a"); +" can be seen algebraically..."
    }
    +MathBlock("""a-b=c \implies b+c=a""")
}