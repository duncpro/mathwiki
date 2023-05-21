package com.duncpro.mathwiki

import com.duncpro.webk.place
import kotlinx.browser.document

fun main() {
    place(TableOfContents(), within = document.getElementById("root")!!)
}