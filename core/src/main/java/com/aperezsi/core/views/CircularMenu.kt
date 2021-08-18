package com.aperezsi.core.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CircularMenu @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0) : FrameLayout(context, attributeSet, defStyleRes) {

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
                    val menuButtonY = if (axisCoord.radius in 91..269) {
                        axisCoord.y
                    } else {
                        (axisCoord.y - menuButton.height / 2)
                    }

                    menuButton.x = (axisCoord.x - menuButton.width / 2)
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
            axisCoords.add(AxisCoords(temporalXAxis(currentAngleStep, mainButtonWidth, mainButtonX), temporalYAxis(currentAngleStep, mainButtonHeight, mainButtonY), currentAngleStep))
            currentAngleStep += angleStep
        }
        return axisCoords.toTypedArray()
    }

    private fun temporalYAxis(currentAngle: Int, mainButtonHeight: Int, mainButtonY: Float): Float {
        val halfMainButtonHeight = mainButtonHeight / 2 // 300
        val fixMainButtonY = mainButtonY + halfMainButtonHeight // 1000
        val heightDiff = fixMainButtonY - mainButtonHeight // 400
        return when (currentAngle) {
            0           -> (heightDiff * 1.1).toFloat()  //  300
            in 1..90    -> (fixMainButtonY - mainButtonHeight) + (currentAngle * mainButtonHeight / 90) // 401-1000
            in 91..179  -> fixMainButtonY + ((currentAngle - 90) * mainButtonHeight / 90) // 1001-1300
            180         -> (fixMainButtonY / 1.2).toFloat() + ((currentAngle - 90) * mainButtonHeight / 90) // 1001-1300
            in 181..270 -> (fixMainButtonY + mainButtonHeight) - ((currentAngle - 180) * mainButtonHeight / 90) // 1300-1000
            in 271..360 -> fixMainButtonY - ((currentAngle - 270) * mainButtonHeight / 90) // 999-700
            else        -> fixMainButtonY
        }
    }

    private fun temporalXAxis(currentAngle: Int, mainButtonWidth: Int, mainButtonX: Float): Float {
        val halfMainButtonWidth = mainButtonWidth / 2 // 300
        val fixMainButtonX = mainButtonX + halfMainButtonWidth // 500
        val widthDiff = fixMainButtonX - mainButtonWidth // 200
        return when (currentAngle) {
            0           -> fixMainButtonX // 500
            in 1..89    -> fixMainButtonX + (currentAngle * mainButtonWidth / 90) // 500-300
            90          -> (fixMainButtonX / 1.1).toFloat() + (currentAngle * mainButtonWidth / 90) // 500-300
            in 91..180  -> (fixMainButtonX + mainButtonWidth) - ((currentAngle - 90) * mainButtonWidth / 90) // 799-500
            in 181..269 -> fixMainButtonX - ((currentAngle - 180) * mainButtonWidth / 90) // 799-500
            270         -> (fixMainButtonX * 1.1).toFloat() - ((currentAngle - 180) * mainButtonWidth / 90) // 799-500
            in 271..360 -> widthDiff + ((currentAngle - 270) * mainButtonWidth / 90) // 201-500
            else        -> fixMainButtonX
        }
    }

    private data class AxisCoords(val x: Float, val y: Float, val radius: Int)
}