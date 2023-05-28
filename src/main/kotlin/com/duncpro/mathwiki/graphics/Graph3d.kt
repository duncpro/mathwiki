package com.duncpro.mathwiki.graphics

import com.duncpro.mathwiki.util.useResizeObserver
import com.duncpro.webk.*
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.math.min

private const val PRIMARY_AXIS_LINE_WIDTH = 2.5
private const val AXIS_COLOR: String = "lightgray"

class Graph3dFunction(val fn: BindScope.(Double, Double) -> Double, val color: String = "red")

class Graph3dProperties(
    val fns: List<Graph3dFunction> = emptyList(),
    val precision: Double = 0.5,
    val scale: Double = 50.0 /* CSS Pixel Per Unit Space */
)

fun CabinetProjectedGraph3d(_format: R<Graph3dProperties> = const(Graph3dProperties())) = UIBoundary {
    val format by ref { _format.bind() }

    val `#canvas` = useStaticDOMHandle<HTMLCanvasElement>()
    val canvasElementDimensions by useResizeObserver(`#canvas`)
    val `$style` = useStyleClass { AnonymousCSSClass("""
        height: 100%;
        width: 100%;
    """) }

    useAsyncEffect {
        val (canvasElementWidth, canvasElementHeight) = canvasElementDimensions?.let { it.width to it.height }
            ?: return@useAsyncEffect

        val canvasNode = `#canvas`.unwrap()

        canvasNode.width = (canvasElementWidth * window.devicePixelRatio).toInt()
        canvasNode.height = (canvasElementHeight * window.devicePixelRatio).toInt()

        val canvasContext = canvasNode.getContext("2d") as CanvasRenderingContext2D
        canvasContext.scale(window.devicePixelRatio, window.devicePixelRatio)
        defer {
            canvasContext.clearRect(0.0, 0.0, canvasElementWidth, canvasElementHeight)
        }

        val horizontalMidpoint = (canvasElementWidth / 2)
        val verticalMidpoint = (canvasElementHeight / 2)

        fun map2dPlaneCoordinateTo3Space(canvasX: Double, canvasY: Double): Pair<Double, Double> {
            // Multiply by 4 to shorten the interior dimensions (x dimension).
            // Specifically, set 1 unit of interior canvas distance equal to 4 units of interior space distance.
            val spaceX = (-1 * canvasY * 4) / format.scale
            val spaceY = ((-1 * canvasY) - canvasX) / format.scale
            return Pair(spaceX * -1, spaceY * -1)
        }

        fun mapPixelCoordinateTo3Space(pixelX: Double, pixelY: Double): Pair<Double, Double> {
            if (pixelX !in 0.0..canvasElementWidth) throw IllegalArgumentException()
            if (pixelY !in 0.0..canvasElementHeight) throw IllegalArgumentException()
            val canvasX = pixelX - horizontalMidpoint // Assume center of canvas is origin
            val canvasY = pixelY - verticalMidpoint // Assume center of canvas is origin
            return map2dPlaneCoordinateTo3Space(canvasX, canvasY)
        }

        // Draw Vertical-Axis
        canvasContext.beginPath()
        canvasContext.strokeStyle = AXIS_COLOR
        canvasContext.lineWidth = PRIMARY_AXIS_LINE_WIDTH
        canvasContext.moveTo(horizontalMidpoint, 0.0)
        canvasContext.lineTo(horizontalMidpoint, canvasElementHeight)
        canvasContext.stroke()

        // Draw Horizontal-Axis
        canvasContext.beginPath()
        canvasContext.strokeStyle = AXIS_COLOR
        canvasContext.lineWidth = PRIMARY_AXIS_LINE_WIDTH
        canvasContext.moveTo(0.0, verticalMidpoint)
        canvasContext.lineTo(canvasElementWidth, verticalMidpoint)
        canvasContext.stroke()

        // Draw Diagonal Axis
        run {
            canvasContext.beginPath()
            canvasContext.strokeStyle = AXIS_COLOR
            canvasContext.lineWidth = PRIMARY_AXIS_LINE_WIDTH
            val horizontalOffset = min(canvasElementWidth - canvasElementHeight, 0.0) / 2
            val verticalOffset = min(canvasElementHeight - canvasElementWidth, 0.0) / 2
            canvasContext.moveTo(0.0 + horizontalOffset, canvasElementHeight - verticalOffset)
            canvasContext.lineTo(canvasElementWidth - horizontalOffset, 0.0 + verticalOffset)
            canvasContext.stroke()
        }

        for (fn in format.fns) {
            var pixelX = 0.0
            while (pixelX < canvasElementWidth) {
                var pixelY = 0.0
                while (pixelY < canvasElementHeight) {
                    val (spaceX, spaceY) = mapPixelCoordinateTo3Space(pixelX, pixelY)
                    val spaceZ = fn.fn(this, spaceX, spaceY)
                    val finalPixelY = pixelY - (spaceZ * format.scale)
                    yield()
                    if (finalPixelY in 0.0..canvasElementHeight) {
                        canvasContext.fillStyle = fn.color
                        canvasContext.fillRect(pixelX, finalPixelY, format.precision, format.precision)
                    }
                    pixelY += format.precision
                }
                pixelX += format.precision
            }
        }
    }

    canvas(`#canvas`, `$style`)
}