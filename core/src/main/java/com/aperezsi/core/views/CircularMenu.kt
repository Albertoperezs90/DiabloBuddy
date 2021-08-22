package com.aperezsi.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.START
import androidx.constraintlayout.widget.ConstraintSet.TOP

class CircularMenu @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0) : ConstraintLayout(context, attributeSet, defStyleRes) {

    private var menuButtonsHaveBeenRendered = false
    private lateinit var centralButton: CircularMainItem
    private lateinit var menuButtons: List<CircularItem>

    fun setMenu(config: CircularMenuConfig) {
        centralButton = configureCentralButton(config)
        if (!isInEditMode) centralButton.visibility = INVISIBLE
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
        centralMenuItem.elevation = 9f
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
        var angle = 315 // start at 320 degrees
        val angleStep = (360 / items.size)
        menuButtons = items.map {
            val menuItem = drawMenuItem(it, angle, centralButton)
            if (!isInEditMode) menuItem.visibility = INVISIBLE
            angle += angleStep
            menuItem
        }

        var buttonsRendered = 0
        menuButtons.forEach { _ ->
            viewTreeObserver.addOnGlobalLayoutListener {
            buttonsRendered++
            if (buttonsRendered == menuButtons.size) {
                animateButtons()
            }
        } }
    }

    private fun drawMenuItem(config: CircularItemConfig, angle: Int, centralButton: CircularMainItem): CircularItem {
        val menuItem = CircularItem(context)
        menuItem.id = generateViewId()
        menuItem.setConfig(config)
        addView(menuItem)
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        constraintSet.constrainCircle(menuItem.id, centralButton.id, (centralButton.width * 0.9).toInt(), angle.toFloat())
        constraintSet.applyTo(this)
        return menuItem
    }

    private fun animateButtons() {
        val originalMenuAxis = menuButtons.map { it.x to it.y }
        resetAnimationValues()
        val centralButtonAnimation = centralButton.animate().apply {
            scaleX(1f)
            scaleY(1f)
            interpolator = AccelerateDecelerateInterpolator()
            duration = 200
        }

        val menuButtonsAnimation = menuButtons.mapIndexed { index, circularItem -> circularItem.animate().apply {
            this.x(originalMenuAxis[index].first)
            this.y(originalMenuAxis[index].second)
            this.alpha(1f)
            interpolator = DecelerateInterpolator()
            duration = 300 + (index * 300L)
        } }

        centralButtonAnimation.start()
        menuButtonsAnimation.forEach { it.start() }
    }

    private fun resetAnimationValues() {
        val centerX = (width / 2) - (menuButtons.first().width / 2)
        val centerY = height / 2 - (menuButtons.first().height / 2)

        centralButton.scaleX = 0.8f
        centralButton.scaleY = 0.8f
        centralButton.visibility = VISIBLE

        menuButtons.forEach {
            it.alpha = 0f
            it.x = centerX.toFloat()
            it.y = centerY.toFloat()
            it.visibility = VISIBLE
        }
    }
}

data class CircularMenuConfig(@DrawableRes val centralButton: Int, val circularItems: List<CircularItemConfig>)