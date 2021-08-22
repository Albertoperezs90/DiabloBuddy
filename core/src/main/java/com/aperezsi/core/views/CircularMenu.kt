package com.aperezsi.core.views

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.START
import androidx.constraintlayout.widget.ConstraintSet.TOP

class CircularMenu @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0) : ConstraintLayout(context, attributeSet, defStyleRes) {

    private var mainButtonHasBeenResized = false
    private var menuButtonsHaveBeenRendered = false

    fun setMenu(config: CircularMenuConfig) {
        val centralButton = configureCentralButton(config)
        centralButton.viewTreeObserver.addOnGlobalLayoutListener {
            if (!menuButtonsHaveBeenRendered) {
                menuButtonsHaveBeenRendered = true
                configureMenuItems(centralButton, config.circularItems)
            }
        }
    }

    private fun configureCentralButton(config: CircularMenuConfig): CircularMainItem {
        val centralMenuItem = CircularMainItem(context)
        centralMenuItem.setImage(config)
        centralMenuItem.id = generateViewId()
        addView(centralMenuItem)
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        constraintSet.connect(centralMenuItem.id, TOP, id, TOP)
        constraintSet.connect(centralMenuItem.id, START, id, START)
        constraintSet.connect(centralMenuItem.id, END, id, END)
        constraintSet.connect(centralMenuItem.id, BOTTOM, id, BOTTOM)
        constraintSet.applyTo(this)
        return centralMenuItem
    }

    private fun configureMenuItems(centralButton: CircularMainItem, items: List<CircularItemConfig>) {
        var angle = 320
        val angleStep = (360 / items.size)
        items.forEach {
            drawMenuItem(it, angle, centralButton)
            angle += angleStep
        }
    }

    private fun drawMenuItem(config: CircularItemConfig, angle: Int, centralButton: CircularMainItem) {
        val menuItem = CircularItem(context)
        menuItem.id = generateViewId()
        menuItem.setConfig(config)
        addView(menuItem)
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        constraintSet.constrainCircle(menuItem.id, centralButton.id, (centralButton.width * 0.9).toInt(), angle.toFloat())
        constraintSet.applyTo(this)
    }

    /*private fun animateButtons() {
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
            animator.duration = 300 + (index * 700L)
        }

        mainButton.visibility = VISIBLE
        mainButtonAnimator.start()
        menuButtonsAnimator.forEachIndexed { index, animator ->
            menuButtons[index].visibility = VISIBLE
            animator.start()
        }
    }*/

    /*private fun resetAnimationValues() {
        mainButton.alpha = 0.3f
        mainButton.scaleX = 0.7f
        mainButton.scaleY = 0.7f

        menuButtons.forEach {
            it.alpha = 0.3f
            it.scaleX = 0.3f
            it.scaleY = 0.3f
        }
    }*/
}

data class CircularMenuConfig(@DrawableRes val centralButton: Int, val circularItems: List<CircularItemConfig>)