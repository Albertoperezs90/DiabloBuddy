package com.diablobuddy.core.utilities.clickhandler

import android.view.View
import com.diablobuddy.core.utilities.time.TimeProvider

fun View.setOnDebounceClickListener(callback: () -> Unit) {
    val debouncer = DebouncerClick(callback, TimeProvider())
    setOnClickListener {
        debouncer.onClick()
    }
}