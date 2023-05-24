package com.duncpro.mathwiki.layout

import com.duncpro.webk.*

fun PrimaryFigure(children: Children) = UI {
    val `$style` = useStyleClass { AnonymousCSSClass("""
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
    """) }
    div(`$style`) { +children }
}