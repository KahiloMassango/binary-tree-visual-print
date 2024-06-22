
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import org.jetbrains.skia.Font
import org.jetbrains.skia.TextBlobBuilder
import resources.binaryTree.Node
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


fun DrawScope.drawBinaryTree(node: Node?, x: Float, y: Float, yOffset: Float, xOffset: Float) {
    if (node == null) return

    val font = Font(null, 20f)
    val textBlobBuilder = TextBlobBuilder()

    textBlobBuilder.appendRun(font, node.data.toString(), 0f, 0f)
    val textBlob = textBlobBuilder.build()

    val paint = org.jetbrains.skia.Paint()
    val bounds = font.measureText(node.data.toString(), paint)
    val textWidth = bounds.width
    val textHeight = bounds.height

    val padding = 10f
    val circleRadius = maxOf(textWidth, textHeight) / 2 + padding

    drawCircle(MyTheme.secondaryContainer, circleRadius, Offset(x, y))

    val textX = x - textWidth / 2 - 2
    val textY = y + textHeight / 2


    fun calculateLinePoints(startX: Float, startY: Float, endX: Float, endY: Float, radius: Float): Pair<Offset, Offset> {
        val angle = atan2(endY - startY, endX - startX)
        val startOffset = Offset(startX + radius * cos(angle), startY + radius * sin(angle))
        val endOffset = Offset(endX - radius * cos(angle), endY - radius * sin(angle))
        return Pair(startOffset, endOffset)
    }

    drawContext.canvas.nativeCanvas.drawTextBlob(
        textBlob!!,
        textX,
        textY,
        paint.apply {
            color = MyTheme.onSecondaryContainer.toArgb()
        }
    )


    node.left?.let {
        val (start, end) = calculateLinePoints(x, y, x - xOffset, y + yOffset, circleRadius)
        drawLine(Color(0xFFABB4BD), start, end, strokeWidth = 4f)
        drawBinaryTree(it, x - xOffset, y + yOffset, yOffset+8, xOffset / 2.toFloat())
    }


    node.right?.let {
        val (start, end) = calculateLinePoints(x, y, x + xOffset, y + yOffset, circleRadius)
        drawLine(Color(0xFFABB4BD), start, end, strokeWidth = 4f)
        drawBinaryTree(it, x + xOffset, y + yOffset, yOffset+14, xOffset / 2.toFloat())
    }

}
