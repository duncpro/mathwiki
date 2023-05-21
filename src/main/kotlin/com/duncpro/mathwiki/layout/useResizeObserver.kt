package com.duncpro.mathwiki.layout

import com.duncpro.webk.FcScope
import com.duncpro.webk.ReactiveRef
import com.duncpro.webk.StaticDOMHandle
import org.w3c.dom.DOMRectReadOnly
import org.w3c.dom.Element

fun FcScope.useResizeObserver(): Pair<StaticDOMHandle<Element>, ReactiveRef<DOMRectReadOnly?>> {
    val domHandle = useStaticDOMHandle<Element>()
    val rect = useResizeObserver(domHandle)
    return Pair(domHandle, rect)
}

fun FcScope.useResizeObserver(domHandle: StaticDOMHandle<out Element>): ReactiveRef<DOMRectReadOnly?> {
    val rect: ReactiveRef<DOMRectReadOnly?> = useValueStream(initialValue = null) {
        val domElement = domHandle.unwrap()
        @Suppress("UNUSED_VARIABLE")
        val observerCallback: dynamic = { entries: dynamic ->
            for (entry in entries) {
                push(entry.contentRect as? DOMRectReadOnly)
            }
        }
        val observer = js("new ResizeObserver(observerCallback)")
        observer.observe(domElement)
        return@useValueStream {
            observer.unobserve(domElement)
            observer.disconnect()
            Unit
        }
    }
    return rect
}