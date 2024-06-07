package com.booslearn.graphify.model

import androidx.compose.ui.graphics.Color

data class Segment(
    val initialPoint : Point,
    val finalPoint : Point,
    val color : Color
)
