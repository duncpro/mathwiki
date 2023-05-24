package com.duncpro.mathwiki.graphics

import com.duncpro.mathwiki.util.useResizeObserver
import com.duncpro.webk.*
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

private const val PRIMARY_AXIS_LINE_WIDTH = 2.5
private const val AXIS_COLOR: String = "lightgray"

class Graph3dFunction(val fn: BindScope.(Double, Double) -> Double, val color: String = "red")

class Graph3dFormat(
    val _fns: ReactiveRef<List<Graph3dFunction>> = const(emptyList()),
    val _precision: ReactiveRef<Double> = const(0.5),
)

fun CabinetProjected3DGraph(_format: ReactiveRef<Graph3dFormat> = const(Graph3dFormat())) = UI {
    val fns by ref { _format.bind()._fns.bind() }
    val precision by ref { _format.bind()._precision.bind() }

    val `#canvas` = useStaticDOMHandle<HTMLCanvasElement>()
    val canvasElementDimensions by useResizeObserver(`#canvas`)
    val `$style` = useStyleClass { AnonymousCSSClass("""
        height: 100%;
        width: 100%;
    """) }

    useRenderEffect {
        val (canvasElementWidth, canvasElementHeight) =
            canvasElementDimensions?.let { it.width to it.height } ?: return@useRenderEffect
        val canvasNode = `#canvas`.unwrap()
        val canvasContext = canvasNode.getContext("2d") as CanvasRenderingContext2D
        canvasNode.width = (canvasElementWidth * window.devicePixelRatio).toInt()
        canvasNode.height = (canvasElementHeight * window.devicePixelRatio).toInt()
        canvasContext.scale(window.devicePixelRatio, window.devicePixelRatio)
        defer { canvasContext.clearRect(0.0, 0.0, canvasElementWidth, canvasElementHeight) }

        val horizontalMidpoint = (canvasElementWidth / 2)
        val verticalMidpoint = (canvasElementHeight / 2)

        fun BindScope.mapInfiniteCanvasPlaneCoordinateTo3Space(canvasX: Double, canvasY: Double): Pair<Double, Double> {
            // Multiply by 4 to shorten the interior dimensions (x dimension).
            // Specifically, set 1 unit of interior canvas distance equal to 4 units of interior space distance.
            val spaceX = (-1 * canvasY * 4)
            val spaceY = (-1 * canvasY) - canvasX

            return Pair(spaceX, spaceY)
        }

        fun BindScope.mapPixelCoordinateTo3Space(pixelX: Double, pixelY: Double): Pair<Double, Double> {
            if (pixelX !in 0.0..canvasElementWidth) throw IllegalArgumentException()
            if (pixelY !in 0.0..canvasElementHeight) throw IllegalArgumentException()
            val canvasX = pixelX - horizontalMidpoint // Assume center of canvas is origin
            val canvasY = pixelY - verticalMidpoint // Assume center of canvas is origin
            return mapInfiniteCanvasPlaneCoordinateTo3Space(canvasX, canvasY)
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
        canvasContext.beginPath()
        canvasContext.strokeStyle = AXIS_COLOR
        canvasContext.lineWidth = PRIMARY_AXIS_LINE_WIDTH
        canvasContext.moveTo(0.0, canvasElementHeight)
        canvasContext.lineTo(canvasElementWidth, 0.0)
        canvasContext.stroke()

        for (fn in fns) {
            var pixelX = 0.0
            while (pixelX < canvasElementWidth) {
                var pixelY = 0.0
                while (pixelY < canvasElementHeight) {
                    val (spaceX, spaceY) = mapPixelCoordinateTo3Space(pixelX, pixelY)
                    val spaceZ = fn.fn(this, spaceX, spaceY)
                    val finalPixelY = pixelY - spaceZ
                    if (finalPixelY in 0.0..canvasElementHeight) {
                        canvasContext.fillStyle = fn.color
                        canvasContext.fillRect(pixelX, finalPixelY, precision, precision)
                    }
                    pixelY += precision
                }
                pixelX += precision
            }
        }
    }


    canvas(`#canvas`, `$style`)
}