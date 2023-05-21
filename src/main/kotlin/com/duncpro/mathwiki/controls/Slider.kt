package com.duncpro.mathwiki.controls

import com.duncpro.webk.*
import org.w3c.dom.HTMLInputElement

class SliderRange<T>(val min: T, val max: T, val step: T, val fromString: (String) -> T)

fun DecimalSliderRange(min: Double, max: Double, step: Double): SliderRange<Double> =
    SliderRange(min, max, step, String::toDouble)

fun <T> Slider(_range: ReactiveRef<SliderRange<T>>, _value: ReactiveRef<T>, onSlide: UnrefScope.(T) -> Unit,
               label: UIComponent? = null) = UI {

    val `$container` = useStyleClass(const(AnonymousCSSClass("display: flex")))
    val range by _range

    div(`$container`) {
        +span { if (label != null) +label }
        +input(
            attr(HTMLInputElement::type, const("range")),
            attr(HTMLInputElement::min, ref { "${range.min}" }),
            attr(HTMLInputElement::max, ref { "${range.max}" }),
            attr(HTMLInputElement::step, ref { range.step.toString() }),
            attr(HTMLInputElement::value, ref { "${_value.bind()}" }),
            handle(HTMLInputElement::oninput) { _, e -> onSlide(_range.unref().fromString(e.value)) }
        )
        +span { +ref { _value.bind().toString() } }
    }
}