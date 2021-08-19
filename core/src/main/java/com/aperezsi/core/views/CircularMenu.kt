package com.aperezsi.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewManager
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CircularMenu @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0): FrameLayout(context, attributeSet, defStyleRes) {

    private var rootHasBeenRendered = false
    private var mainButtonHasBeenRendered = false
    private var menuButtonsHasBeenRendered = false
    private var buttons = 5

    private lateinit var mainButton: FloatingActionButton
    private var menuButtons: MutableList<FloatingActionButton> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!rootHasBeenRendered) {
            rootHasBeenRendered = true
            configureCentralButton()
        }
    }

    private fun configureCentralButton() {
        mainButton = FloatingActionButton(context)
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
                //configureMenu(mainButtonWidth, mainButtonHeight, mainButtonX, mainButtonY)
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
            menuButtons.add(menuButton)
            addView(menuButton)
        }
    }

    private fun getAxisCoordForAngle(mainButtonWidth: Int, mainButtonHeight: Int, mainButtonX: Float, mainButtonY: Float, angle: Int): AxisCoords {
        return AxisCoords(calculateAxisX(angle, mainButtonWidth, mainButtonX), calculateAxisY(angle, mainButtonHeight, mainButtonY), angle)
    }

    private fun getAxisCoords(mainButtonWidth: Int, mainButtonHeight: Int, mainButtonX: Float, mainButtonY: Float): Array<AxisCoords> {
        val angle = 360
        val angleStep = angle / buttons
        var currentAngleStep = 0
        val axisCoords = mutableListOf<AxisCoords>()
        for (i in 0 until buttons) {
            axisCoords.add(AxisCoords(calculateAxisX(currentAngleStep, mainButtonWidth, mainButtonX), calculateAxisY(currentAngleStep, mainButtonHeight, mainButtonY), currentAngleStep))
            currentAngleStep += angleStep
        }
        return axisCoords.toTypedArray()
    }

    private fun calculateAxisY(currentAngle: Int, mainButtonHeight: Int, mainButtonY: Float): Float {
        val halfMainButtonHeight = mainButtonHeight / 2
        val centerY = mainButtonY + halfMainButtonHeight
        val lowestY = centerY - mainButtonHeight
        return when (currentAngle) {
            0           -> (lowestY * 1.1).toFloat()
            in 1..90    -> (centerY - mainButtonHeight) + (currentAngle * mainButtonHeight / 90)
            in 91..179  -> centerY + ((currentAngle - 90) * mainButtonHeight / 90)
            180         -> (centerY / 1.2).toFloat() + ((currentAngle - 90) * mainButtonHeight / 90)
            in 181..270 -> (centerY + mainButtonHeight) - ((currentAngle - 180) * mainButtonHeight / 90)
            in 271..360 -> centerY - ((currentAngle - 270) * mainButtonHeight / 90)
            else        -> centerY
        }
    }

    private fun calculateAxisX(currentAngle: Int, mainButtonWidth: Int, mainButtonX: Float): Float {
        val halfMainButtonWidth = mainButtonWidth / 2
        val centerX = mainButtonX + halfMainButtonWidth
        val lowestX = centerX - mainButtonWidth
        return when (currentAngle) {
            0           -> centerX
            in 1..89    -> centerX + (currentAngle * mainButtonWidth / 90)
            90          -> (centerX / 1.1).toFloat() + (currentAngle * mainButtonWidth / 90)
            in 91..180  -> (centerX + mainButtonWidth) - ((currentAngle - 90) * mainButtonWidth / 90)
            in 181..269 -> centerX - ((currentAngle - 180) * mainButtonWidth / 90)
            270         -> (centerX * 1.1).toFloat() - ((currentAngle - 180) * mainButtonWidth / 90)
            in 271..360 -> lowestX + ((currentAngle - 270) * mainButtonWidth / 90)
            else        -> centerX
        }
    }

    fun updateAngle(value: Float) {
        val mainButtonWidth = mainButton.width
        val mainButtonHeight = mainButton.height
        val mainButtonX = mainButton.x
        val mainButtonY = mainButton.y
        val axisCoords = getAxisCoordForAngle(mainButtonWidth, mainButtonHeight, mainButtonX, mainButtonY, value.toInt())

        menuButtons.forEach {
            (parent as ViewManager).removeView(it)
        }

        var menuButtonHasBeenRendered = false
        val menuButton = FloatingActionButton(context)
        menuButton.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        menuButton.viewTreeObserver.addOnGlobalLayoutListener {
            if (!menuButtonHasBeenRendered) {
                menuButtonHasBeenRendered = true
                val menuButtonY = if (axisCoords.radius in 91..269) {
                    axisCoords.y
                } else {
                    (axisCoords.y - menuButton.height / 2)
                }

                menuButton.x = (axisCoords.x - menuButton.width / 2)
                menuButton.y = menuButtonY
            }
        }
        menuButtons.add(menuButton)
        addView(menuButton)
    }

    private data class AxisCoords(val x: Float, val y: Float, val radius: Int)
}