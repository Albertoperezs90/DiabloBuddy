package com.aperezsi.core.clickhandler

import android.view.View
import com.aperezsi.core.date.TimeProvider

fun View.setOnDebounceClickListener(callback: () -> Unit) {
    val debouncer = DebouncerClick(callback, TimeProvider())
    setOnClickListener {
        debouncer.onClick()
    }
}