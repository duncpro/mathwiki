package com.duncpro.mathwiki.graphics

import com.duncpro.webk.*
import org.intellij.lang.annotations.Language
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLSpanElement

value class Tex(@Language("tex") val raw: String)

fun Math(tex: ReactiveRef<Tex>) = UI {
    val _span = useStaticDOMHandle<HTMLSpanElement>()

    useRenderEffect {
        val katex = js("require(\"katex\")")
        val options = js("{ displayMode: false, output: \"mathml\" }")
        katex.render(tex.bind().raw, _span.unwrap(), options)
        return@useRenderEffect
    }

    span(_span)
}

fun Math(@Language("tex") tex: String) = Math(const(Tex(tex)))

fun MathBlock(tex: ReactiveRef<Tex>) = UI {
    val _div = useStaticDOMHandle<HTMLDivElement>()

    useRenderEffect {
        val katex = js("require(\"katex\")")
        val options = js("{ displayMode: true, output: \"mathml\" }")
        katex.render(tex.bind().raw, _div.unwrap(), options)
        return@useRenderEffect
    }

    div(_div)
}

fun MathBlock(@Language("tex") tex: String) = MathBlock(const(Tex(tex)))