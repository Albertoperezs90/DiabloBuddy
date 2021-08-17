package com.aperezsi.core.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.absoluteValue

class CircularMenu @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0): FrameLayout(context, attributeSet, defStyleRes) {

    private var rootHasBeenRendered = false
    private var mainButtonHasBeenRendered = false
    private var menuButtonsHasBeenRendered = false
    private var buttons = 3

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!rootHasBeenRendered) {
            rootHasBeenRendered = true
            configureCentralButton()
        }
    }

    private fun configureCentralButton() {
        val mainButton = FloatingActionButton(context)
        mainButton.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        var mainButtonHeight = 0
        var mainButtonWidth = 0
        var mainButtonX = 0f
        var mainButtonY = 0f

        mainButton.viewTreeObserver.addOnGlobalLayoutListener {
            if (!mainButtonHasBeenRendered) {
                mainButtonHasBeenRendered = true
                mainButtonHeight = mainButton.height * 2
                mainButtonWidth = mainButton.width * 2
                mainButtonX = (measuredWidth / 2 - mainButton.width).toFloat()
                mainButtonY = (measuredHeight / 2 - mainButton.height).toFloat()
                val newLayoutParams = mainButton.layoutParams.apply {
                    height = mainButtonHeight
                    width = mainButtonWidth
                }
                mainButton.layoutParams = newLayoutParams
                mainButton.x = mainButtonX
                mainButton.y = mainButtonY
            } else if (!menuButtonsHasBeenRendered) {
                menuButtonsHasBeenRendered = true
                configureMenu(mainButtonWidth, mainButtonHeight, mainButtonX, mainButtonY)
            }
        }

        addView(mainButton)
    }

    private fun configureMenu(mainButtonWidth: Int, mainButtonHeight: Int, mainButtonX: Float, mainButtonY: Float) {
        val axisCoords = getAxisCoords(mainButtonWidth, mainButtonHeight, mainButtonX, mainButtonY)
        axisCoords.forEach { axisCoord ->
            var menuButtonHasBeenRendered = false
            val menuButton = FloatingActionButton(context)
            menuButton.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

            menuButton.viewTreeObserver.addOnGlobalLayoutListener {
                if (!menuButtonHasBeenRendered) {
                    menuButtonHasBeenRendered = true
                    val menuButtonX = (axisCoord.x - menuButton.width / 2)
                    val menuButtonY = (axisCoord.y - menuButton.height / 2)
                    menuButton.x = menuButtonX
                    menuButton.y = menuButtonY
                }
            }

            addView(menuButton)
        }
    }

    private fun getAxisCoords(mainButtonWidth: Int, mainButtonHeight: Int, mainButtonX: Float, mainButtonY: Float): Array<AxisCoords> {
        val angle = 360
        val angleStep = angle / buttons
        var currentAngleStep = 0
        val axisCoords = mutableListOf<AxisCoords>()
        for (i in 0 until buttons) {
            axisCoords.add(AxisCoords(calculateXAxis(currentAngleStep, mainButtonWidth, mainButtonX), calculateYAxis(currentAngleStep, mainButtonHeight, mainButtonY), currentAngleStep))
            currentAngleStep += angleStep
        }
        return axisCoords.toTypedArray()
    }

    private fun calculateYAxis(currentAngle: Int, mainButtonHeight: Int, mainButtonY: Float): Float {
        val fixMainButtonY = mainButtonY + (mainButtonHeight / 2)
        return when (currentAngle) {
            0           -> fixMainButtonY - mainButtonHeight
            in 1..44    -> fixMainButtonY - ((currentAngle * mainButtonHeight / 90) - mainButtonHeight).absoluteValue
            45          -> fixMainButtonY - (mainButtonHeight / 2)
            in 46..89   -> fixMainButtonY - (currentAngle * mainButtonHeight / 90) - mainButtonHeight
            90          -> fixMainButtonY
            in 91..134  -> fixMainButtonY + (currentAngle * mainButtonHeight / 180)
            135         -> fixMainButtonY + (mainButtonHeight / 2)
            in 136..179 -> fixMainButtonY + (currentAngle * mainButtonHeight / 180)
            180         -> fixMainButtonY + mainButtonHeight
            in 181..224 -> fixMainButtonY + (((currentAngle - 180) * mainButtonHeight / 270) - mainButtonHeight).absoluteValue
            225         -> fixMainButtonY + (mainButtonHeight / 2)
            in 226..269 -> fixMainButtonY + ((currentAngle - 180) * mainButtonHeight / 90)
            270         -> fixMainButtonY
            in 271..314 -> fixMainButtonY + ((currentAngle - 270) * mainButtonHeight / 360)
            315         -> fixMainButtonY + (mainButtonHeight / 2)
            in 271..359 -> fixMainButtonY + ((currentAngle * mainButtonHeight / 360) - mainButtonHeight).absoluteValue
            else        -> fixMainButtonY
        }
    }

    private fun calculateXAxis(currentAngle: Int, mainButtonWidth: Int, mainButtonX: Float): Float {
        val fixMainButtonX = mainButtonX + (mainButtonWidth / 2)
        return when (currentAngle) {
            0           -> fixMainButtonX
            in 1..89    -> fixMainButtonX + (currentAngle * mainButtonWidth / 90)
            90          -> fixMainButtonX + mainButtonWidth
            in 91..179  -> fixMainButtonX + (currentAngle * mainButtonWidth / 180)
            180         -> fixMainButtonX
            in 181..269 -> fixMainButtonX - (currentAngle * mainButtonWidth / 270)
            270         -> fixMainButtonX - mainButtonWidth
            in 271..360 -> fixMainButtonX - (currentAngle * mainButtonWidth / 360)
            else        -> fixMainButtonX
        }
    }

    private data class AxisCoords(val x: Float, val y: Float, val radius: Int)
}