package com.duncpro.mathwiki

import com.duncpro.webk.place
import kotlinx.browser.document

fun main() {
    place(Home(), within = document.getElementById("root")!!)
}