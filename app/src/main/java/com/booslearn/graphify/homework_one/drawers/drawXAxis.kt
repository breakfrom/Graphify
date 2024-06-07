package com.booslearn.graphify.homework_one.drawers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

fun DrawScope.drawXAxis(axisXPosition: Float, screenWidth: Float) {
    drawLine(
        color = Color.Black,
        start = Offset(0f, axisXPosition),
        end = Offset(screenWidth, axisXPosition),
        strokeWidth = 2.dp.toPx()
    )
}