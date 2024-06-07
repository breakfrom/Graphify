package com.booslearn.graphify.homework_one.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

fun DrawScope.drawYAxis(axisYPosition: Float, screenHeight: Float) {
    drawLine(
        color = Color.Black,
        start = Offset(axisYPosition, 0f),
        end = Offset(axisYPosition, screenHeight),
        strokeWidth = 2.dp.toPx()
    )
}