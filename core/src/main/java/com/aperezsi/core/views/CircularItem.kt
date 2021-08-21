package com.aperezsi.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.aperezsi.core.databinding.CircularItemViewBinding

class CircularItem @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttrs: Int = 0) : ConstraintLayout(context, attributeSet, defStyleAttrs) {

    private val binding: CircularItemViewBinding = CircularItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun configureCoords(axisCoords: AxisCoords) {
        x = axisCoords.x
        y = axisCoords.y
    }

    fun setText(text: String) {
        binding.title.text = text
    }

}