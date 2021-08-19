package com.aperezsi.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.cos
import kotlin.math.sin

class CircularMenu @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0) : FrameLayout(context, attributeSet, defStyleRes) {

    private var rootHasBeenRendered = false
    private var mainButtonHasBeenRendered = false
    private var menuButtonsHaveBeenRendered = false

    private lateinit var mainButton: FloatingActionButton
    private var menuItems: List<String> = emptyList()
    private var menuButtons: MutableList<FloatingActionButton> = mutableListOf()

    private var mainButtonHeight = 0
    private var mainButtonWidth = 0
    private var mainButtonX = 0f
    private var mainButtonY = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!rootHasBeenRendered) {
            rootHasBeenRendered = true
            configureCentralButton()
        }
    }

    fun setMenu(items: List<String>) {
        menuItems = items
        if (menuButtonsHaveBeenRendered) {
            resetDraw()
        }
    }

    private fun configureCentralButton() {
        mainButton = FloatingActionButton(context)
        mainButton.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        mainButton.visibility = INVISIBLE

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
            } else if (!menuButtonsHaveBeenRendered) {
                menuButtonsHaveBeenRendered = true
                drawMenu()
            }
        }

        addView(mainButton)
    }

    private fun resetDraw() {
        rootHasBeenRendered = false
        mainButtonHasBeenRendered = false
        menuButtonsHaveBeenRendered = false
        menuButtons.clear()
        removeAllViews()
    }

    private fun drawMenu() {
        if (menuItems.isEmpty()) return

        val angleStep = 360 / menuItems.size
        var angle = 0
        menuItems.forEach {
            val axisCoord = calculateAxis(angle, mainButtonHeight, mainButtonX, mainButtonY)
            angle += angleStep
            drawMenuItem(it, axisCoord)
        }

        animateButtons()
    }

    private fun drawMenuItem(it: String, axisCoord: AxisCoords) {
        var menuButtonHasBeenRendered = false
        val menuButton = FloatingActionButton(context)
        menuButton.visibility = INVISIBLE
        menuButton.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        menuButton.viewTreeObserver.addOnGlobalLayoutListener {
            if (!menuButtonHasBeenRendered) {
                menuButtonHasBeenRendered = true
                menuButton.x = axisCoord.x
                menuButton.y = axisCoord.y
            }
        }
        menuButtons.add(menuButton)
        addView(menuButton)
    }

    private fun calculateAxis(angle: Int, radius: Int, centerX: Float, centerY: Float): AxisCoords {
        val fixedCenterX = centerX + (radius / 4)
        val fixedCenterY = centerY + (radius / 4)
        val x = (radius * sin(Math.PI * 2 * angle / 360)).toFloat() + fixedCenterX
        val y = (radius * -cos(Math.PI * 2 * angle / 360)).toFloat() + fixedCenterY
        return AxisCoords(x, y, angle)
    }

    private fun animateButtons() {
        resetAnimationValues()
        val mainButtonAnimator = mainButton.animate()
        mainButtonAnimator.apply {
            alpha(1f)
            scaleX(1f)
            scaleY(1f)
            interpolator = AccelerateDecelerateInterpolator()
            duration = 200
        }

        val menuButtonsAnimator = menuButtons.map { it.animate() }
        menuButtonsAnimator.forEachIndexed { index, animator ->
            animator.alpha(1f)
            animator.scaleX(1f)
            animator.scaleY(1f)
            animator.interpolator = AccelerateDecelerateInterpolator()
            animator.duration = 300 + (index * 150L)
        }

        mainButton.visibility = VISIBLE
        mainButtonAnimator.start()
        menuButtonsAnimator.forEachIndexed { index, animator ->
            menuButtons[index].visibility = VISIBLE
            animator.start()
        }
    }

    private fun resetAnimationValues() {
        mainButton.alpha = 0.3f
        mainButton.scaleX = 0.7f
        mainButton.scaleY = 0.7f

        menuButtons.forEach {
            it.alpha = 0.3f
            it.scaleX = 0.3f
            it.scaleY = 0.3f
        }
    }

    private data class AxisCoords(val x: Float, val y: Float, val radius: Int)
}