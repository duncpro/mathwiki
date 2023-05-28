package com.duncpro.mathwiki.layout

import com.duncpro.webk.*

fun PrimaryFigure(children: Children) = run {
    val `$style` = RCStyle(ref { AnonymousCSSClass("""
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
    """) })
    div(`$style`) { +children }
}