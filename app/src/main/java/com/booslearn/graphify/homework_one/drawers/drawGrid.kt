package com.booslearn.graphify.homework_one.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

fun DrawScope.drawGrid(gridSize: Int, centerX: Float, centerY: Float, density: Density) {
    val step = with(density) { gridSize.dp.toPx() }
    for (x in 0 until size.width.toInt() step step.toInt()) {
        drawLine(
            color = Color.Gray,
            start = Offset(x.toFloat(), 0f),
            end = Offset(x.toFloat(), size.height),
            strokeWidth = 1.dp.toPx()
        )
    }
    for (y in 0 until size.height.toInt() step step.toInt()) {
        drawLine(
            color = Color.Gray,
            start = Offset(0f, y.toFloat()),
            end = Offset(size.width, y.toFloat()),
            strokeWidth = 1.dp.toPx()
        )
    }
}