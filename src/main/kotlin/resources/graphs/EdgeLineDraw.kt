package resources.graphs

import resources.MyTheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawCurvedLine(start: Offset, end: Offset) {
    val midPoint = Offset(
        (start.x + end.x) / 2,
        (start.y + end.y) / 2 - 50 // Adjust the curve height
    )

    val path = Path().apply {
        moveTo(start.x, start.y)
        quadraticBezierTo(midPoint.x, midPoint.y, end.x, end.y)
    }

    drawPath(
        path = path,
        color = MyTheme.primary,
        style = Stroke(width = 3f)
    )
}
