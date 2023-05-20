package com.duncpro.mathwiki

import com.duncpro.webk.place
import kotlinx.browser.document
import com.duncpro.mathwiki.topics.Limits
import kotlinx.browser.window

fun main() {
    place(TableOfContents(), within = document.getElementById("root")!!)
}