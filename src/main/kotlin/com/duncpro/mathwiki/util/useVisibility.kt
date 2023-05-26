package com.duncpro.mathwiki.util

import com.duncpro.webk.*
import org.w3c.dom.Element

fun FcScope.useVisibility(_node: StaticDOMHandle<out Element>): ReactiveRef<Boolean> {
    val current = useValueStream<Boolean?>(initialValue = null) {
        val callback: dynamic = { entries: dynamic -> push(entries[0].isIntersecting as Boolean) }
        val intersectionObserver = js("new IntersectionObserver(callback)")
        intersectionObserver.observe(_node.unwrap())
        callback(intersectionObserver.takeRecords())
        return@useValueStream { intersectionObserver.disconnect(); Unit }
    }

    @Suppress("UNCHECKED_CAST")
    return current as ReactiveRef<Boolean>
}

fun FcScope.useFirstAppearance(_node: StaticDOMHandle<out Element>): ReactiveRef<Boolean> {
    val isVisible by useVisibility(_node)
    return useValueStreamReducer(initialState = false) { hasAppeared ->
        if (hasAppeared) return@useValueStreamReducer true
        return@useValueStreamReducer isVisible
    }
}