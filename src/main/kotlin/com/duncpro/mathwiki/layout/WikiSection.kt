package com.duncpro.mathwiki.layout

import com.duncpro.mathwiki.util.encodeURIComponent
import com.duncpro.mathwiki.util.h
import com.duncpro.webk.*
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLElement

private class WikiIndexEntry(val title: String, val href: String)

private class WikiSectionContext(
    val title: String,
    val parent: WikiSectionContext?,
    val anchorId: String,
    val depth: Int,
    val index: UIList<WikiIndexEntry> = UIList(),
)

private fun WikiSectionContext(title: String, parent: WikiSectionContext?): WikiSectionContext {
    val lineage = mutableListOf<WikiSectionContext>()
    var next: WikiSectionContext? = parent
    while (next != null) {
        lineage.add(next)
        next = next.parent
    }
    lineage.reverse()

    val pathElements = ArrayList<String>(initialCapacity = lineage.size + 1)
    for (ancestor in lineage) pathElements.add(ancestor.title)
    pathElements.add(title)
    val anchorId = pathElements.joinToString(separator = "/")
    return WikiSectionContext(title, parent, anchorId, pathElements.size)
}

fun WikiSection(title: String, children: Children) = UI {
    val parent = useOptionalContext<WikiSectionContext>()
    val context = WikiSectionContext(title, parent)

    val `$style` = useStyleClass(const(
        AnonymousCSSClass("""
        width: 100%;    
    """)
    ))

    useMountEffect {
        if (parent == null) return@useMountEffect
        val entry = WikiIndexEntry(title, "#${encodeURIComponent(context.anchorId)}")
        val parentIndex = parent.index.unref()
        parentIndex.add(entry)
        defer { parentIndex.remove(entry) }
    }

    ContextProvider(context, div(`$style`) {
        +h(_n = const(context.depth)) {
            +span(attr(HTMLElement::id, const(context.anchorId))) {
                +title
            }
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