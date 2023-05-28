package com.duncpro.mathwiki

import com.duncpro.webk.mount
import kotlinx.browser.document

fun main() {
    mount(Home(), at = document.getElementById("root")!!)
}