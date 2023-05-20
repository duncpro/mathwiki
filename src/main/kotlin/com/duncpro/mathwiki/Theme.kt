package com.duncpro.mathwiki

import com.duncpro.webk.*
import org.intellij.lang.annotations.Language

val CurrentTheme by ReactiveProperty(Theme())

class Theme {
    val unimportant = color("darkgrey")
    val strokeColor = color("black")
}

fun color(@Language("css", prefix = ".placeholder { color:  ", suffix = "; }") cssColorExpression: String): String {
    return cssColorExpression
}