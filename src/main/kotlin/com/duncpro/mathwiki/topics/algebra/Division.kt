package com.duncpro.mathwiki.topics.algebra

import com.duncpro.mathwiki.CurrentTheme
import com.duncpro.mathwiki.graphics.Math
import com.duncpro.mathwiki.graphics.MathBlock
import com.duncpro.mathwiki.layout.WikiSection
import com.duncpro.mathwiki.util.useResizeObserver
import com.duncpro.webk.*
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.math.max

fun Division() = WikiSection("Division") {
    +WikiSection("Definition") {
        +MathBlock("""
            x \div y = x\space[-y\space[\text{ repeated }n\text{ times } ]]
        """)
        +p {
            +"Division is repeated subtraction. Or in a more abstract sense, finding the amount of times one number ";
            +Math("y"); +" fits inside another, "; +Math("x"); + "."
        }
    }
    +WikiSection("Sharing Problems") {
        +"Division can be represented as a sharing problem. Given "; +Math("y"); +" empty buckets, and ";
        +"a starting pool of "; +Math("x"); +" elements... How many elements will end up in each bucket, "
        +"assuming they are shared evenly?"
    }
    +WikiSection("Grouping Problems") {
        +p {
            +"Division can be represented as a grouping problem. Given a pool of "; +Math("x"); +" elements, how ";
            +"many groups of "; +Math("y"); +" elements can be made?"
        }
        +GroupingDiagram()
    }
    +WikiSection("Comparison") {
        +"In "; +b { +"sharing" }; +" problems, "; +Math("y"); +" is the number of groups. "
        +"In "; +b { +"grouping" }; + " problems, "; +Math("y"); +" is the quantity of elements per group. ";
    }
}

fun GroupingDiagram() = UILifecycleBoundary {
    val `#canvas` = useStaticDOMHandle<HTMLCanvasElement>()
    val `$style` = useStyleClass { AnonymousCSSClass("""
        width: 100%;
        height: 175px;
    """) }
    val canvasDimensions by useResizeObserver(`#canvas`)
    val unitWidth = 125.0
    val unitHeight = 75.0
    val rulerOffset = 20.0

    useRenderEffect {
        val (canvasWidth, canvasHeight) = canvasDimensions?.let { it.width to it.height } ?: return@useRenderEffect
        val canvasNode = `#canvas`.unwrap()
        canvasNode.width = canvasWidth.toInt()
        canvasNode.height = canvasHeight.toInt()
        val horizontalMidpoint = canvasNode.width.toDouble() / 2
        val canvasContext = canvasNode.getContext("2d") as CanvasRenderingContext2D
        defer { canvasContext.clearRect(0.0, 0.0, canvasWidth, canvasHeight) }

        // Draw Elements
        var currentX = 0.0
        while (currentX < canvasWidth) {
            if (currentX >= unitWidth) {
                canvasContext.strokeStyle = "rgba(0, 0, 0, ${max(1 - (currentX / canvasWidth), 0.15)})"
            } else {
                canvasContext.strokeStyle = CurrentTheme.textColor
            }

            canvasContext.strokeRect(currentX, 0.0, unitWidth, unitHeight)
            currentX += unitWidth
        }

        // Draw 1 Ruler
        val oneRulerY = unitHeight + rulerOffset
        val oneRulerLength = 25.0
        run {
            canvasContext.beginPath()
            canvasContext.moveTo(0.0, oneRulerY)
            canvasContext.lineTo(oneRulerLength, oneRulerY)
            canvasContext.strokeStyle = CurrentTheme.textColor
            canvasContext.stroke()
            val labelMeasure = canvasContext.measureText("1")
            val labelHeight = labelMeasure.actualBoundingBoxAscent + 10
            val labelWidth = labelMeasure.width
            canvasContext.fillStyle = CurrentTheme.textColor
            canvasContext.font = "italic 16px Arial"
            canvasContext.fillText("1", (oneRulerLength / 2) - (labelWidth / 2),
                oneRulerY + labelHeight)
        }

        // Draw Element Ruler
        val elementRulerY = oneRulerY + 30
        run {
            canvasContext.beginPath()
            canvasContext.moveTo(0.0, elementRulerY)
            canvasContext.lineTo(unitWidth, elementRulerY)
            canvasContext.strokeStyle = CurrentTheme.textColor
            canvasContext.stroke()
            val labelMeasure = canvasContext.measureText("y")
            val labelHeight = labelMeasure.actualBoundingBoxAscent + 10
            val labelWidth = labelMeasure.width
            canvasContext.fillStyle = CurrentTheme.textColor
            canvasContext.font = "italic 16px Arial"
            canvasContext.fillText("y", (unitWidth / 2) - (labelWidth / 2),
                elementRulerY + labelHeight)
        }

        // Draw Pool Ruler
        val poolRulerY = elementRulerY + 30
        run {
            canvasContext.beginPath()
            canvasContext.moveTo(0.0, poolRulerY)
            canvasContext.lineTo(canvasWidth, poolRulerY)
            canvasContext.strokeStyle = CurrentTheme.textColor
            canvasContext.stroke()
            val poolRulerLabelMeasure = canvasContext.measureText("x")
            val poolRulerLabelOffset = poolRulerLabelMeasure.actualBoundingBoxAscent + 10
            val poolRulerLabelWidth = poolRulerLabelMeasure.width
            canvasContext.fillStyle = CurrentTheme.textColor
            canvasContext.font = "italic 16px Arial"
            canvasContext.fillText("x", horizontalMidpoint - (poolRulerLabelWidth / 2),
                poolRulerY + poolRulerLabelOffset)
        }
    }

    canvas(`#canvas`, `$style`)
}