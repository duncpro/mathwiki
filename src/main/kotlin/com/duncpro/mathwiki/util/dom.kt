package com.duncpro.mathwiki.util

import com.duncpro.webk.*

fun h(_n: ReactiveRef<Int>, children: Children) = UI {
    Fork {
        when (_n.bind()) {
            1 -> h1 { +children }
            2 -> h2 { +children }
            3 -> h3 { +children }
            4 -> h4 { +children }
            5 -> h5 { +children }
            6 -> h6 { +children }
            else -> throw IllegalArgumentException()
        }
    }
}