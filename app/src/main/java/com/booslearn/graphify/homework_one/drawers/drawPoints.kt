package com.booslearn.graphify.homework_one.drawers

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.booslearn.graphify.model.Point
import com.booslearn.graphify.offsetFromCoordinate
import com.booslearn.graphify.utils.GRID_SIZE

fun DrawScope.drawPoints(
    coordinate : Boolean,
    points: List<Point>,
    centerX: Float,
    centerY: Float,
    density: Density
) {
    points.forEach {
        drawPoint(
            coordinate = coordinate,
            point = it,
            centerX = centerX,
            centerY = centerY,
            density = density
        )
    }
}