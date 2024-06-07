package com.example.cronos.utils.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import kotlin.math.min

fun Modifier.maxAspectRatio(maxAspectRatio: Float): Modifier = this.then(
    Modifier.layout { measurable, constraints ->
        val maxWidth = constraints.maxWidth
        val maxHeight = constraints.maxHeight

        // Calculate the constrained dimensions based on the maximum aspect ratio
        val calculatedWidth = min(maxWidth, (maxHeight * maxAspectRatio).toInt())
        val calculatedHeight = min(maxHeight, (maxWidth / maxAspectRatio).toInt())

        // Create new constraints based on the calculated dimensions
        val newConstraints = Constraints.fixed(calculatedWidth, calculatedHeight)

        // Measure the placeable with the new constraints
        val placeable = measurable.measure(newConstraints)

        // Define the layout with the new dimensions
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
)
