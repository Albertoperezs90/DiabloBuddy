package com.aperezsi.core.utilities.clickhandler

import android.view.View
import com.aperezsi.core.utilities.time.TimeProvider

fun View.setOnDebounceClickListener(callback: () -> Unit) {
    val debouncer = DebouncerClick(callback, TimeProvider())
    setOnClickListener {
        debouncer.onClick()
    }
}