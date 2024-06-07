package com.booslearn.graphify.homework_one.drawers

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.booslearn.graphify.model.Segment
import com.booslearn.graphify.offsetFromCoordinate

fun DrawScope.drawSegments(
    coordinate : Boolean,
    segments: List<Segment>,
    centerX: Float,
    centerY: Float,
    density: Density
) {
    for (segment in segments) {
        val start = offsetFromCoordinate(segment.initialPoint.coordinate, centerX, centerY, density)
        val end = offsetFromCoordinate(segment.finalPoint.coordinate, centerX, centerY, density)
        drawLine(
            color = segment.color,
            start = start,
            end = end,
            strokeWidth = 2.dp.toPx()
        )
        drawPoints(
            coordinate = coordinate,
            points = listOf(
                segment.initialPoint, segment.finalPoint
            ),
            centerX,
            centerY,
            density
        )
    }
}