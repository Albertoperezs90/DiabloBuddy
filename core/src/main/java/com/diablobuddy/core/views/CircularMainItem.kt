package com.diablobuddy.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.diablobuddy.core.databinding.CircularMainItemViewBinding

class CircularMainItem @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttrs: Int = 0) : ConstraintLayout(context, attributeSet, defStyleAttrs) {

    private val binding: CircularMainItemViewBinding = CircularMainItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun setImage(config: CircularMenuConfig) {
        binding.image.setImageResource(config.centralButton)
    }
}