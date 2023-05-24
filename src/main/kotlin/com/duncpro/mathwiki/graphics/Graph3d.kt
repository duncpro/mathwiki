package com.duncpro.mathwiki.graphics

//import com.duncpro.mathwiki.util.useResizeObserver
//import com.duncpro.webk.*
//import kotlinx.browser.window
//import org.w3c.dom.CanvasRenderingContext2D
//import org.w3c.dom.HTMLCanvasElement
//
//private const val GRID_LINE_INTERVAL = 40
//private const val PIXEL_SIZE = 2.5
//
//class Graph3dFunction(val fn: BindScope.(Double, Double) -> Double, val color: String = "red")
//
//class Graph3dFormat(
//    val _fns: ReactiveRef<List<Graph3dFunction>> = const(emptyList()),
//    val _precision: ReactiveRef<Double> = const(0.01),
//    val _step: ReactiveRef<Double> = const(1.0),
//    val _isUserScrollable: ReactiveRef<Boolean> = const(false)
//)
//
//
//fun Graph3d(_format: ReactiveRef<Graph3dFormat> = const(Graph3dFormat())) = UI {
//    val fns by ref { _format.bind()._fns.bind() }
//    val precision by ref { _format.bind()._precision.bind() }
//    val step by ref { _format.bind()._step.bind() }
//    val isUserScrollable by ref { _format.bind()._isUserScrollable.bind() }
//
//    var horizontalScrollOffset by useLocalState(initialValue = 0.0)
//    var verticalScrollOffset by useLocalState(initialValue = 0.0)
//    val handleScroll = handle(HTMLCanvasElement::onwheel) { event ->
//        if (!isUserScrollable) return@handle
//        horizontalScrollOffset += event.deltaX
//        verticalScrollOffset += (event.deltaY * -1)
//        event.preventDefault()
//    }
//
//    val `#canvas` = useStaticDOMHandle<HTMLCanvasElement>()
//    val canvasElementDimensions by useResizeObserver(`#canvas`)
//    val `$style` = useStyleClass { AnonymousCSSClass("""
//        height: 100%;
//        width: 100%;
//    """) }
//
//    useRenderEffect {
//        val (canvasElementWidth, canvasElementHeight) =
//            canvasElementDimensions?.let { it.width to it.height } ?: return@useRenderEffect
//        val canvasNode = `#canvas`.unwrap()
//        val canvasContext = canvasNode.getContext("2d") as CanvasRenderingContext2D
//        canvasNode.width = (canvasElementWidth * window.devicePixelRatio).toInt()
//        canvasNode.height = (canvasElementHeight * window.devicePixelRatio).toInt()
//        canvasContext.scale(window.devicePixelRatio, window.devicePixelRatio)
//        defer { canvasContext.clearRect(0.0, 0.0, canvasElementWidth, canvasElementHeight) }
//
//        // The z-axis increases towards the top of the page
//        val minVisibleZ = (verticalScrollOffset - canvasElementHeight) / GRID_LINE_INTERVAL * step
//
//        // The Y-axis increases towards the right-hand side of the page
//        val minVisibleY = horizontalScrollOffset / GRID_LINE_INTERVAL * step
//        val maxVisibleY = minVisibleY + (canvasElementWidth / GRID_LINE_INTERVAL * step)
//
//        val minVisibleX = maxVisibleY * -1
//        val maxVisibleX = -1 * minVisibleZ
//
//        fun pixelCoordinateOf(x: Double, y: Double, z: Double): Pair<Double, Double> {
//            if (x < minVisibleX || x > maxVisibleX) throw IllegalStateException("Coordinate does not exist in viewport")
//            if (y < minVisibleY || y > maxVisibleY) throw IllegalStateException("Coordinate does not exist in viewport")
////            if (z < minVisibleZ || z > maxVisibleZ) throw IllegalStateException("Coordinate does not exist in viewport")
//
//            val stepsX = (x - minVisibleX) / step
//            val stepsY = (y - minVisibleY) / step
//            val stepsZ = (z - minVisibleZ) / step
//
//            val pixelX = ((-1 * stepsX) + stepsY) * GRID_LINE_INTERVAL
//            val pixelY = (stepsX + (-1 * stepsZ)) * GRID_LINE_INTERVAL
//            return Pair(pixelX, pixelY)
//        }
//
//        println("X: (${minVisibleX}, ${maxVisibleX})")
//        println("Y: (${minVisibleY}, ${maxVisibleY})")
//        println("Z: (${minVisibleZ})")
//
//        for (fn in fns) {
//            var x = minVisibleX
//            while (x < maxVisibleX) {
//                var y = minVisibleY
//                while (y < maxVisibleY) {
//                    val z = fn.fn(this, x, y)
//                    val (canvasX, canvasY) = pixelCoordinateOf(x, y, z)
//                    canvasContext.fillStyle = fn.color
//                    canvasContext.fillRect(canvasX - (PIXEL_SIZE / 2), canvasY - (PIXEL_SIZE / 2), PIXEL_SIZE, PIXEL_SIZE)
//                    y += precision
//                }
//                x += precision
//            }
//        }
//    }
//
//
//    canvas(`#canvas`, `$style`, handleScroll)
//}