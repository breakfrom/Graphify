package com.booslearn.graphify.homework_one.drawers

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.booslearn.graphify.model.Point
import com.booslearn.graphify.offsetFromCoordinate
import com.booslearn.graphify.utils.GRID_SIZE

fun DrawScope.drawPoint(
    coordinate : Boolean,
    point: Point,
    centerX: Float,
    centerY: Float,
    density: Density
) {
        val offset = offsetFromCoordinate(point.coordinate, centerX, centerY, density)
        drawCircle(
            color = point.color,
            center = offset,
            radius = 5.dp.toPx()
        )
        val newX = ((((point.coordinate.x) * 10).toInt() / 10.0).toFloat())
        val newY = ((((point.coordinate.y - 1f) * 10).toInt() / 10.0).toFloat())
        drawContext.canvas.nativeCanvas.drawText(
            point.name.toString() + if(
                coordinate
            ) " (${newX}, ${newY})"  else "",
            offset.x,
            offset.y - GRID_SIZE,
            android.graphics.Paint().apply {
                color = android.graphics.Color.BLACK
                textSize = 30f
            }
        )
}