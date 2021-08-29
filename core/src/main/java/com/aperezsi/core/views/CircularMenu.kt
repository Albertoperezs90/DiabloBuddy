package com.aperezsi.core.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.START
import androidx.constraintlayout.widget.ConstraintSet.TOP
import androidx.core.animation.doOnStart

@Suppress("MagicNumber")
class CircularMenu @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleRes: Int = 0): ConstraintLayout(context, attributeSet, defStyleRes) {

    private var menuButtonsHaveBeenRendered = false
    private lateinit var centralButton: CircularMainItem
    private lateinit var menuButtons: List<CircularItem>
    private lateinit var menuButtonAnimations: MutableList<CircularItemAnimationConfig>

    fun setMenu(config: CircularMenuConfig) {
        removeAllViews()
        centralButton = configureCentralButton(config)
        centralButton.visibility = INVISIBLE
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
        centralMenuItem.elevation = 1f
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
        var angle = 315
        val angleStep = (360 / items.size)
        menuButtonAnimations = mutableListOf()
        menuButtons = items.map {
            val menuItem = drawMenuItem(it, angle, centralButton)
            menuItem.visibility = INVISIBLE
            menuButtonAnimations.add(CircularItemAnimationConfig(angle.toFloat(), (centralButton.width * 0.9).toInt()))
            angle += angleStep
            menuItem
        }

        onMenuButtonsRendered {
            animateButtons()
        }
    }

    private fun onMenuButtonsRendered(onComplete: () -> Unit) {
        var buttonsRendered = 0
        menuButtons.forEach { _ ->
            viewTreeObserver.addOnGlobalLayoutListener {
                buttonsRendered++
                if (buttonsRendered == menuButtons.size) {
                    onComplete()
                }
            }
        }
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
        val originalMenuAxis = menuButtons.map { it.x to it.y }.first()
        resetAnimationValues()
        val centralButtonAnimation = centralButton.animate().apply {
            scaleX(1f)
            scaleY(1f)
            interpolator = AccelerateDecelerateInterpolator()
            duration = 200
        }

        val firstMenuButton = menuButtons.first().animate().apply {
            this.x(originalMenuAxis.first)
            this.y(originalMenuAxis.second)
            interpolator = DecelerateInterpolator()
            alpha(1f)
            duration = 300
        }

        val menuAnimations = menuButtons.drop(1).mapIndexed { index, circularItem ->
            val animatorValue = ValueAnimator.ofInt(menuButtonAnimations[index].angle.toInt(), menuButtonAnimations[index + 1].angle.toInt())
            animatorValue.addUpdateListener {
                val value = it.animatedValue as Int
                val layoutParams = circularItem.layoutParams as LayoutParams
                layoutParams.circleAngle = value.toFloat()
                circularItem.layoutParams = layoutParams
            }

            animatorValue.doOnStart {
                circularItem.visibility = VISIBLE
            }

            animatorValue.duration = 200
            animatorValue.interpolator = LinearInterpolator()
            animatorValue.startDelay = 300 + (index * 170L)
            animatorValue
        }

        centralButtonAnimation.start()
        firstMenuButton.start()
        menuAnimations.forEach { it.start() }
    }

    private fun resetAnimationValues() {
        val centerX = (width / 2) - (menuButtons.first().width / 2)
        val centerY = height / 2 - (menuButtons.first().height / 2)

        centralButton.scaleX = 0.8f
        centralButton.scaleY = 0.8f
        centralButton.visibility = VISIBLE

        menuButtons.forEachIndexed { index, circularItem ->
            if (index == 0) {
                circularItem.alpha = 0f
                circularItem.x = centerX.toFloat()
                circularItem.y = centerY.toFloat()
                circularItem.visibility = VISIBLE
            } else {
                val layoutParams = circularItem.layoutParams as LayoutParams
                layoutParams.circleAngle = menuButtonAnimations[index - 1].angle
                circularItem.layoutParams = layoutParams
                circularItem.visibility = INVISIBLE
            }
        }
    }
}

data class CircularMenuConfig(@DrawableRes val centralButton: Int, val circularItems: List<CircularItemConfig>)