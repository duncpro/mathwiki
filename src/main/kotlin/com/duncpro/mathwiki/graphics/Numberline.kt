package com.duncpro.mathwiki.graphics

import com.duncpro.mathwiki.CurrentTheme
import com.duncpro.mathwiki.util.useResizeObserver
import com.duncpro.webk.*
import kotlinx.browser.window
import org.intellij.lang.annotations.Language
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.math.PI
import kotlin.math.absoluteValue

private const val TICK_INTERVAL = 50
private const val TICK_HEIGHT = 20
private const val TICK_WIDTH = 4
private const val NUMBERLINE_THICKNESS = 4
private const val TEXT_VERTICAL_OFFSET = 20

class NumberlinePointStyle(
    @Language("css", prefix = ".placeholder { color:  ", suffix = "; }") val color: String? = null
)

class NumberlineFormat(
    val _origin: ReactiveRef<Int> = const(0),
    val _label: ReactiveRef<(Int) -> String> = const { "$it" },
    val _pointStyle: ReactiveRef<(Int) -> NumberlinePointStyle> = const { NumberlinePointStyle() }
)

fun Numberline(_format: ReactiveRef<NumberlineFormat> = const(NumberlineFormat())) = UI {
    val origin by ref { _format.bind()._origin.bind() }
    val label by ref { _format.bind()._label.bind() }
    val pointStyleFn by ref { _format.bind()._pointStyle.bind() }

    val style = useStyleClass { AnonymousCSSClass("""
        width: 100%;
        height: 100px;
    """) }

    val _canvas = useStaticDOMHandle<HTMLCanvasElement>()
    val rect by useResizeObserver(_canvas)

    var scrollOffset by useLocalState(0.0)
    val handleHorizontalScroll = handle(HTMLCanvasElement::onwheel) { event ->
        if (event.deltaY.absoluteValue > event.deltaX.absoluteValue) return@handle
        scrollOffset += (-1 * event.deltaX)
        event.preventDefault()
    }

    useRenderEffect {
        val canvasNode = _canvas.unwrap()
        canvasNode.width = ((rect?.width ?: 0.0) * window.devicePixelRatio).toInt()
        canvasNode.height = ((rect?.height ?: 0.0) * window.devicePixelRatio).toInt()

        val canvasWidth = canvasNode.width.toDouble()
        val canvasHeight = canvasNode.height.toDouble()
        val verticalMidpoint = canvasHeight / window.devicePixelRatio / 2
        val horizontalMidpoint = canvasWidth / window.devicePixelRatio / 2

        val canvasContext = canvasNode.getContext("2d") as CanvasRenderingContext2D
        canvasContext.scale(window.devicePixelRatio, window.devicePixelRatio)
        canvasContext.font = "16px Arial"

        // Draw Line
        canvasContext.strokeStyle = CurrentTheme.textColor
        canvasContext.moveTo(0.0, verticalMidpoint)
        canvasContext.lineTo(canvasWidth, verticalMidpoint)
        canvasContext.stroke()

        // Draw Ticks
        val totalOffset = scrollOffset + (origin * TICK_INTERVAL * -1) + (horizontalMidpoint)
        var nextVisibleTickX = totalOffset % TICK_INTERVAL
        val firstVisibleTick = (totalOffset  / TICK_INTERVAL * -1).toInt()
        val lastVisibleTickX = canvasWidth + (TICK_WIDTH / 2) + nextVisibleTickX
        var localTick = 0
        while (nextVisibleTickX < lastVisibleTickX) {
            canvasContext.moveTo(nextVisibleTickX, verticalMidpoint - (TICK_HEIGHT / 2))
            canvasContext.lineTo(nextVisibleTickX, verticalMidpoint + (TICK_HEIGHT / 2))
            canvasContext.strokeStyle = CurrentTheme.textColor
            canvasContext.stroke()
            val tick = firstVisibleTick + localTick
            val tickLabel = label(tick)
            val tickLabelWidth = canvasContext.measureText(tickLabel).width
            canvasContext.fillStyle = CurrentTheme.textColor
            canvasContext.fillText(tickLabel, nextVisibleTickX - (tickLabelWidth / 2),
                verticalMidpoint + (TICK_HEIGHT / 2) + TEXT_VERTICAL_OFFSET)
            val pointStyle = pointStyleFn(tick)
            if (pointStyle.color != null) {
                canvasContext.fillStyle = pointStyle.color
                canvasContext.beginPath();
                canvasContext.arc(nextVisibleTickX, verticalMidpoint, 4.0, 0.0, 2 * PI);
                canvasContext.fill()
            }
            nextVisibleTickX += TICK_INTERVAL
            localTick += 1
        }

        defer { canvasContext.clearRect(0.0, 0.0, canvasWidth, canvasHeight) }
    }

    canvas(handleHorizontalScroll, _canvas, style)
}