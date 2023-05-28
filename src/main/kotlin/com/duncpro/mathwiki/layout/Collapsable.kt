package com.duncpro.mathwiki.layout

import com.duncpro.mathwiki.clickable
import com.duncpro.mathwiki.unimportant
import com.duncpro.webk.*
import org.w3c.dom.HTMLElement

fun Collapsable(children: Children) = run {
    var isCollapsed by ReactiveProperty(initialValue = false)
    Fork {
        if (isCollapsed) {
            div(unimportant, clickable, handle(HTMLElement::onclick) { _ -> isCollapsed = false }) {
                +"[expand]"
            }
        } else {
            div {
                +div(unimportant, clickable, handle(HTMLElement::onclick) { _ -> isCollapsed = true }) {
                    +"[collapse]"
                }
                +children
            }
        }
    }
}