package com.aperezsi.core.views

import kotlin.math.cos
import kotlin.math.sin

class CircleCoordsHandler {

    fun calculateAxis(angle: Int, radius: Int, centerX: Float, centerY: Float): AxisCoords {
        val fixedCenterX = centerX - (radius * 0.05)
        val fixedCenterY = centerY - (radius * 0.05)
        val x = (radius * -cos(Math.PI * 2 * (angle + 55) / 360)).toFloat() + fixedCenterX
        val y = (radius * -sin(Math.PI * 2 * (angle + 55) / 360)).toFloat() + fixedCenterY
        return AxisCoords(x.toFloat(), y.toFloat(), angle)
    }

}

data class AxisCoords(val x: Float, val y: Float, val radius: Int)