package com.duncpro.mathwiki.graphics

import com.duncpro.mathwiki.CurrentTheme
import com.duncpro.mathwiki.util.clamp
import com.duncpro.mathwiki.util.useResizeObserver
import com.duncpro.webk.*
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

private const val GRID_LINE_INTERVAL = 40
private const val PIXEL_SIZE = 2.5
private const val PRIMARY_AXIS_LINE_WIDTH = 2.5
private const val SECONDARY_AXIS_LINE_WIDTH = 1.0
private const val AXIS_LABEL_OFFSET = PRIMARY_AXIS_LINE_WIDTH * 2

class Graph2dFunction(val fn: BindScope.(Double) -> Double, val color: String = "red")

class Graph2dFormat(
    val _fns: ReactiveRef<List<Graph2dFunction>> = const(emptyList()),
    val _precision: ReactiveRef<Double> = const(0.01),
    val _step: ReactiveRef<Double> = const(1.0),
    val _isUserScrollable: ReactiveRef<Boolean> = const(false),
)

fun Graph2d(_format: ReactiveRef<Graph2dFormat> = const(Graph2dFormat())) = UI {
    val fns by ref { _format.bind()._fns.bind() }
    val precision by ref { _format.bind()._precision.bind() }
    val step by ref { _format.bind()._step.bind() }
    val isUserScrollable by ref { _format.bind()._isUserScrollable.bind() }

    val `#canvas` = useStaticDOMHandle<HTMLCanvasElement>()
    val canvasElementDimensions by useResizeObserver(`#canvas`)
    val `$style` = useStyleClass { AnonymousCSSClass("""
        width: 100%;
        height: 100%;
    """) }

    var horizontalScrollOffset by useLocalState(initialValue = 0.0)
    var verticalScrollOffset by useLocalState(initialValue = 0.0)
    val handleScroll = handle(HTMLCanvasElement::onwheel) { event ->
        if (!isUserScrollable) return@handle
        horizontalScrollOffset += (event.deltaX * -1)
        verticalScrollOffset += (event.deltaY * -1)
        event.preventDefault()
    }

    useRenderEffect {
        val canvasNode = `#canvas`.unwrap()
        val canvasContext = canvasNode.getContext("2d") as CanvasRenderingContext2D
        val (canvasElementWidth, canvasElementHeight) = canvasElementDimensions?.let { Pair(it.width, it.height) }
            ?: return@useRenderEffect
        canvasNode.width = (canvasElementWidth * window.devicePixelRatio).toInt()
        canvasNode.height = (canvasElementHeight * window.devicePixelRatio).toInt()
        canvasContext.scale(window.devicePixelRatio, window.devicePixelRatio)
        canvasContext.font = "12px Arial"

        val horizontallyCenterOriginOffset = canvasElementWidth / 2
        val verticallyCenterOriginOffset = canvasElementHeight / 2

        val horizontalOffset = horizontalScrollOffset + horizontallyCenterOriginOffset
        val verticalOffset = verticalScrollOffset + verticallyCenterOriginOffset

        var nextVerticalLineX = horizontalOffset % GRID_LINE_INTERVAL
        while (nextVerticalLineX < canvasElementWidth) {
            canvasContext.strokeStyle = CurrentTheme.textColor
            canvasContext.lineWidth = SECONDARY_AXIS_LINE_WIDTH
            canvasContext.beginPath()
            canvasContext.moveTo(nextVerticalLineX, 0.0)
            canvasContext.lineTo(nextVerticalLineX, canvasElementHeight)
            canvasContext.stroke()
            nextVerticalLineX += GRID_LINE_INTERVAL
        }

        var nextHorizontalLineY = verticalOffset % GRID_LINE_INTERVAL
        while (nextHorizontalLineY < canvasElementHeight) {
            canvasContext.strokeStyle = CurrentTheme.textColor
            canvasContext.lineWidth = SECONDARY_AXIS_LINE_WIDTH
            canvasContext.beginPath()
            canvasContext.moveTo(0.0, nextHorizontalLineY)
            canvasContext.lineTo(canvasElementWidth, nextHorizontalLineY)
            canvasContext.stroke()
            nextHorizontalLineY += GRID_LINE_INTERVAL
        }


        val minX = (horizontalOffset * -1) / GRID_LINE_INTERVAL * step
        val maxX = ((horizontalOffset * - 1) + canvasElementWidth) / GRID_LINE_INTERVAL * step
        val minY = (verticalOffset * -1) / GRID_LINE_INTERVAL * step
        val maxY = ((verticalOffset * -1) + canvasElementHeight) / GRID_LINE_INTERVAL * step


        val isPrimaryHorizontalAxisVisible = 0.0 in minY..maxY
        val isPrimaryVerticalAxisVisible = 0.0 in minX..maxX

        // Bold Primary Horizontal Axis
        if (isPrimaryHorizontalAxisVisible) {
            val pixelY = (0 - minY) / step * GRID_LINE_INTERVAL
            canvasContext.strokeStyle = CurrentTheme.textColor
            canvasContext.lineWidth = PRIMARY_AXIS_LINE_WIDTH
            canvasContext.beginPath()
            canvasContext.moveTo(0.0, pixelY)
            canvasContext.lineTo(canvasElementWidth, pixelY)
            canvasContext.stroke()
        }

        // Bold Primary Vertical Axis
        if (isPrimaryVerticalAxisVisible) {
            val pixelX = (0 - minX) / step  * GRID_LINE_INTERVAL
            canvasContext.strokeStyle = CurrentTheme.textColor
            canvasContext.lineWidth = PRIMARY_AXIS_LINE_WIDTH
            canvasContext.beginPath()
            canvasContext.moveTo(pixelX, 0.0)
            canvasContext.lineTo(pixelX, canvasElementHeight)
            canvasContext.stroke()
        }

        for (fn in fns) {
            var x = minX
            while (x < maxX) {
                val y = fn.fn(this, x) * -1
                if (y in minY..maxY) {
                    val pixelX = (x - minX) / step * GRID_LINE_INTERVAL
                    val pixelY = (y - minY) / step * GRID_LINE_INTERVAL
                    canvasContext.fillStyle = fn.color
                    canvasContext.fillRect(pixelX - (PIXEL_SIZE / 2), pixelY - (PIXEL_SIZE / 2), PIXEL_SIZE, PIXEL_SIZE)
                }
                x += precision
            }
        }

        // Label Primary Vertical Axis
        run {
            var y = maxY - (maxY % step)
            while (y >= minY) {
                val axisLabel = "${y * -1}"
                val pixelY = ((y - minY) / step * GRID_LINE_INTERVAL) + (canvasContext.measureText(axisLabel)
                    .actualBoundingBoxAscent / 2)
                val pixelX = clamp(
                    value = (0 - minX) / step * GRID_LINE_INTERVAL + AXIS_LABEL_OFFSET,
                    lb = 0.0,
                    ub = canvasElementWidth - AXIS_LABEL_OFFSET - canvasContext.measureText(axisLabel).width
                )
                if (y != 0.0) {
                    canvasContext.strokeStyle = CurrentTheme.backgroundColor
                    canvasContext.strokeText(axisLabel, pixelX, pixelY)
                    canvasContext.fillStyle = CurrentTheme.textColor
                    canvasContext.fillText(axisLabel, pixelX, pixelY)
                }
                y -= step
            }
        }

        // Label Primary Horizontal Axis
        run {
            var x = maxX - (maxX % step)
            while (x >= minX) {
                val axisLabel = "$x"
                val pixelX = ((x - minX) / step * GRID_LINE_INTERVAL) - (canvasContext.measureText(axisLabel).width / 2)
                val pixelY = clamp(
                    value = (0 - minY) / step * GRID_LINE_INTERVAL - AXIS_LABEL_OFFSET,
                    lb = 0.0 + AXIS_LABEL_OFFSET + canvasContext.measureText(axisLabel).actualBoundingBoxAscent,
                    ub = canvasElementHeight
                )
                if (x != 0.0) {
                    canvasContext.strokeStyle = CurrentTheme.backgroundColor
                    canvasContext.strokeText(axisLabel, pixelX, pixelY)
                    canvasContext.fillStyle = CurrentTheme.textColor
                    canvasContext.fillText(axisLabel, pixelX, pixelY)
                }
                x -= step
            }
        }

        defer { canvasContext.clearRect(0.0, 0.0, canvasElementWidth, canvasElementHeight) }
    }

    canvas(`#canvas`, `$style`, handleScroll)
}