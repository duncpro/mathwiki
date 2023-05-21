package com.duncpro.mathwiki

import com.duncpro.webk.*
import org.intellij.lang.annotations.Language

val centerText = RCStyle(const(AnonymousCSSClass("text-align: center")))
val bold = RCStyle(const(AnonymousCSSClass("font-weight: bold")))
val clickable = RCStyle(const(AnonymousCSSClass("cursor: pointer")))
val banner = RCStyle(const(AnonymousCSSClass("width: 100%")))

val unimportant = RCStyle(ref { AnonymousCSSClass("""
        color: ${CurrentTheme.unimportant};
""") })

val square = RCStyle(const(AnonymousCSSClass("aspect-ratio: 1")))

val CurrentTheme by ReactiveProperty(Theme())

class Theme {
    val unimportant = color("darkgrey")
    val textColor = color("black")
    val backgroundColor = color("white")
}

fun color(@Language("css", prefix = ".placeholder { color:  ", suffix = "; }") cssColorExpression: String): String {
    return cssColorExpression
}