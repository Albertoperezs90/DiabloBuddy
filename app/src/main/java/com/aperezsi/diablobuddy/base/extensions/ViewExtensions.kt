package com.aperezsi.diablobuddy.base.extensions

import android.view.View
import com.aperezsi.diablobuddy.provider.TimeProvider

fun View.setOnDebounceClickListener(callback: () -> Unit) {
    val debouncer = Debouncer(callback)

    setOnClickListener {
        debouncer.onClick()
    }
}

class Debouncer(private val callback: () -> Unit, private val timeProvider: TimeProvider = TimeProvider()) {
    companion object {

        private const val debounceInterval = 600L
    }

    private var lastActionTime = 0L

    fun onClick() {
        val now = timeProvider.elapsedBootTime()
        val timePassed = now - lastActionTime
        val isEnoughTime = timePassed > debounceInterval

        if (isEnoughTime) {
            lastActionTime = now
            callback()
        }
    }
}
