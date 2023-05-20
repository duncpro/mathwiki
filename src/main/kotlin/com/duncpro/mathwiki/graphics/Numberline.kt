package com.duncpro.mathwiki.graphics

import com.duncpro.mathwiki.CurrentTheme
import com.duncpro.mathwiki.layout.useResizeObserver
import com.duncpro.webk.*
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.math.absoluteValue

private const val TICK_INTERVAL = 50
private const val TICK_HEIGHT = 20
private const val TICK_WIDTH = 4
private const val NUMBERLINE_THICKNESS = 4
private const val TEXT_VERTICAL_OFFSET = 20

fun Numberline(label: (Int) -> String = { "$it" }, _origin: ReactiveRef<Int> = const(0)) = UI {
    val style = useStyleClass { AnonymousCSSClass("""
        width: 100%;
        height: 250px;
    """) }


    val _canvas = useStaticDOMHandle<HTMLCanvasElement>()
    val (monitorRect, rect) = useResizeObserver()
    val canvasElementWidth by useMemo { rect.bind()?.width }
    val canvasElementHeight by useMemo { rect.bind()?.height }

    var scrollOffset by useLocalState(0.0)
    val handleHorizontalScroll = handle(HTMLCanvasElement::onwheel) { event ->
        if (event.deltaY.absoluteValue > event.deltaX.absoluteValue) return@handle
        scrollOffset += (-1 * event.deltaX)
        event.preventDefault()
    }

    useRenderEffect {
        val canvasNode = _canvas.unwrap()

        // Hold the width constant (disable auto-scaling)
        canvasNode.width = (canvasElementWidth ?: 0.0).toInt()
        canvasNode.height = (canvasElementHeight ?: 0.0).toInt()

        val canvasWidth = canvasNode.width.toDouble()
        val canvasHeight = canvasNode.height.toDouble()
        val verticalMidpoint = canvasHeight / 2
        val canvasContext = canvasNode.getContext("2d") as CanvasRenderingContext2D

        canvasContext.strokeStyle = CurrentTheme.strokeColor

        // Draw Line
        canvasContext.moveTo(0.0, canvasHeight / 2)
        canvasContext.lineTo(canvasWidth, canvasHeight / 2)
        canvasContext.stroke()

        // Draw Ticks
        val totalOffset = scrollOffset + (_origin.bind() * TICK_INTERVAL * -1) + (canvasWidth / 2)
        var nextVisibleTickX = totalOffset % TICK_INTERVAL
        val firstVisibleTick = totalOffset.toInt()  / TICK_INTERVAL * -1
        val lastVisibleTickX = canvasWidth + (TICK_WIDTH.toDouble() / 2) + nextVisibleTickX
        var localTick = 0
        while (nextVisibleTickX < lastVisibleTickX) {
            canvasContext.moveTo(nextVisibleTickX, verticalMidpoint - (TICK_HEIGHT / 2))
            canvasContext.lineTo(nextVisibleTickX, verticalMidpoint + (TICK_HEIGHT / 2))
            canvasContext.stroke()
            val tick = firstVisibleTick + localTick
            val tickLabel = label(tick)
            val tickLabelWidth = canvasContext.measureText(tickLabel).width
            canvasContext.fillText(tickLabel, nextVisibleTickX - (tickLabelWidth / 2),
                verticalMidpoint + (TICK_HEIGHT / 2) + TEXT_VERTICAL_OFFSET)
            nextVisibleTickX += TICK_INTERVAL
            localTick += 1
        }

        defer { canvasContext.clearRect(0.0, 0.0, canvasWidth, canvasHeight) }
    }

    canvas(handleHorizontalScroll, _canvas, style, monitorRect)
}