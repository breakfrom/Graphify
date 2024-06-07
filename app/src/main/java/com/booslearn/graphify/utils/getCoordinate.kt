package com.booslearn.graphify.utils

fun Float.getX(): Float {
    return this * GRID_SIZE
}
fun Float.getY(): Float {
    return (this * GRID_SIZE) - GRID_SIZE/2
}
const val GRID_SIZE = 20