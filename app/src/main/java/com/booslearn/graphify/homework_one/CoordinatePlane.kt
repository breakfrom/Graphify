package com.booslearn.graphify.homework_one

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.booslearn.graphify.homework_one.drawers.drawGrid
import com.booslearn.graphify.homework_one.drawers.drawPoint
import com.booslearn.graphify.homework_one.drawers.drawPoints
import com.booslearn.graphify.homework_one.drawers.drawSegments
import com.booslearn.graphify.homework_one.drawers.drawXAxis
import com.booslearn.graphify.homework_one.drawers.drawYAxis
import com.booslearn.graphify.model.Point
import com.booslearn.graphify.model.Segment

@Composable
fun CoordinatePlane(
    modifier: Modifier,
    coordinate : Boolean,
    points: List<Point> = emptyList(),
    segments: List<Segment> = emptyList(),
    gridSize: Int
) {
    val density = LocalDensity.current

    Canvas(
        modifier = Modifier
            .fillMaxSize()
    )   {
        val screenWidth = size.width
        val screenHeight = size.height

        val columns = (screenWidth / (gridSize.dp.toPx())).toInt()
        val rows = (screenHeight / (gridSize.dp.toPx())).toInt()

        val centerX = screenWidth / 2
        val centerY = screenHeight / 2

        val axisXPosition = rows / 2 * gridSize.dp.toPx()
        val axisYPosition = columns / 2 * gridSize.dp.toPx()

        // Dibujar el eje X
        drawXAxis(axisXPosition, screenWidth)

        // Dibujar el eje Y
        drawYAxis(axisYPosition, screenHeight)

        // Dibujar la cuadrícula
        drawGrid(gridSize, centerX, centerY, density)

        // Dibujar los segmentos
        drawSegments(coordinate, segments, centerX, centerY, density)

        // Dibujar los puntos
        drawPoints(coordinate, points, centerX, centerY, density)
    }
}
