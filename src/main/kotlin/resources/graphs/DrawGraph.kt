package resources.graphs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import org.jetbrains.skia.Font
import org.jetbrains.skia.Paint
import org.jetbrains.skia.TextBlobBuilder
import resources.MyTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun DrawGraph(
    graph: Graph,
    nodePositions: MutableList<Offset>
) {
    val nodes = graph.nodes
    val edges = graph.edges

    if (nodePositions.isEmpty()) {
        val radius = 200f
        val centerX = 300f
        val centerY = 300f
        val angleIncrement = (2 * Math.PI / nodes.size).toFloat()

        nodePositions.addAll(nodes.indices.map { i ->
            val angle = i * angleIncrement
            Offset(
                centerX + radius * cos(angle),
                centerY + radius * sin(angle)
            )
        })
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        // Draw edges
        for (edge in edges) {
            val (src, dst) = edge
            val srcIndex = nodes.indexOf(src)
            val dstIndex = nodes.indexOf(dst)
            if (srcIndex == dstIndex) continue // Skip self-loops for simplicity
            if (edges.contains(dst to src)) {
                drawCurvedLine(nodePositions[srcIndex], nodePositions[dstIndex])
            }
            if(edges.contains(src to dst)) {
                drawLine(
                    color = MyTheme.primary,
                    start = nodePositions[srcIndex],
                    end = nodePositions[dstIndex],
                    strokeWidth = 3f
                )
            }
        }

        // Draw nodes
        for ((index, node) in nodes.withIndex()) {
            val font = Font(null, 20f)
            val textBlobBuilder = TextBlobBuilder()

            textBlobBuilder.appendRun(font, node, 0f, 0f)
            val textBlob = textBlobBuilder.build()
            val paint = Paint()
            val bounds = font.measureText(node, paint)
            val textWidth = bounds.width
            val textHeight = bounds.height

            val position = nodePositions[index]

            val padding = 20f
            val circleRadius = maxOf(textWidth, textHeight) / 2 + padding
            drawCircle(
                color = MyTheme.secondaryContainer,
                radius = circleRadius,
                center = Offset(position.x+7, position.y-7),
            )

            drawContext.canvas.nativeCanvas.drawTextBlob(
                textBlob!!,
                position.x,
                position.y,
                Paint().apply {
                    color = MyTheme.onSecondaryContainer.toArgb()
                }
            )
        }
    }
}


