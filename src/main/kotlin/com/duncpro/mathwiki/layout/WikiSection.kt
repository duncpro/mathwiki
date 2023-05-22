package com.duncpro.mathwiki.layout

import com.duncpro.mathwiki.util.encodeURIComponent
import com.duncpro.mathwiki.util.h
import com.duncpro.webk.*
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLElement

private class WikiIndexEntry(val title: String, val href: String)

private class WikiSectionContext(
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

    val id = path.joinToString(separator = "/") { it.title }

    val `$style` = useStyleClass(const(
        AnonymousCSSClass("""
        width: 100%;    
    """)
    ))

    useMountEffect {
        if (parent == null) return@useMountEffect
        val entry = WikiIndexEntry(title, "#${encodeURIComponent(id)}")
        val parentIndex = parent.index.unref()
        parentIndex.add(entry)
        defer { parentIndex.remove(entry) }
    }

    ContextProvider(context, div(`$style`) {
        +h(_n = const(path.size)) {
            +span(attr(HTMLElement::id, const(id))) { +title }
        }
        +WikiSectionIndexView(context.index)
        +children
    })
}

private fun WikiSectionIndexView(entries: UIList<WikiIndexEntry>) = UI {
    val `$style` = useStyleClass { AnonymousCSSClass("""
        max-height: 150px;
        display: flex;
        flex-flow: wrap column;
    """) }

    ul(`$style`) {
        +ForEach(entries) { entry ->
            a(attr(HTMLAnchorElement::href, const(entry.href))) {
                +li { +entry.title }
            }
        }
    }
}