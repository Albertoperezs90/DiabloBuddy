package com.aperezsi.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.aperezsi.core.databinding.CircularItemViewBinding

class CircularItem @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttrs: Int = 0): ConstraintLayout(context, attributeSet, defStyleAttrs) {

    private val binding: CircularItemViewBinding = CircularItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun setConfig(circularItemConfig: CircularItemConfig) {
        binding.image.setImageResource(circularItemConfig.image)
        binding.title.setText(circularItemConfig.text)
    }
}

data class CircularItemConfig(@DrawableRes val image: Int, @StringRes val text: Int)
data class CircularItemAnimationConfig(val angle: Float, val radius: Int)