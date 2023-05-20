package com.duncpro.mathwiki

import com.duncpro.webk.*

val centerText = RCStyle(const(AnonymousCSSClass("text-align: center")))
val bold = RCStyle(const(AnonymousCSSClass("font-weight: bold")))
val clickable = RCStyle(const(AnonymousCSSClass("cursor: pointer")))

val unimportant = RCStyle(ref { AnonymousCSSClass("""
        color: ${CurrentTheme.unimportant};
""") })