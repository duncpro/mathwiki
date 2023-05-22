package com.duncpro.mathwiki.layout

import com.duncpro.mathwiki.util.encodeURIComponent
import com.duncpro.mathwiki.util.h
import com.duncpro.webk.*
import kotlinx.browser.window
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLElement

class WikiIndexEntry(val title: String, val href: String)

class WikiSectionContext(
    val title: String,
    val index: UIList<WikiIndexEntry> = UIList(),
    val parent: WikiSectionContext?
)

fun WikiSection(title: String, children: Children) = UI {
    val parent = useOptionalContext<WikiSectionContext>()
    val context = WikiSectionContext(title, UIList(), parent)
    val path = mutableListOf<WikiSectionContext>()

    var next: WikiSectionContext? = context
    while (next != null) {
        path.add(next)
        next = next.parent
    }
    path.reverse()

    val id = path.joinToString(separator = "/")

    useMountEffect {
        if (parent == null) return@useMountEffect
        val entry = WikiIndexEntry(title, encodeURIComponent("#${encodeURIComponent(id)}"))
        val parentIndex = parent.index.unref()
        parentIndex.add(entry)
        defer { parentIndex.remove(entry) }
    }

    ContextProvider(context, div {
        +h(_n = const(path.size)) {
            +span(attr(HTMLElement::id, const(id))) { +title }
        }
        +WikiSectionIndexView(context.index)
        +children
    })
}

fun WikiSectionIndexView(entries: UIList<WikiIndexEntry>) = UI {
    val `$style` = useStyleClass { AnonymousCSSClass("""
        max-height: 250px;
        display: flex;
        flex-flow: wrap column;
    """) }

    ul(`$style`) {
        ForEach(entries) { entry ->
            li { entry.title }
        }
    }
}