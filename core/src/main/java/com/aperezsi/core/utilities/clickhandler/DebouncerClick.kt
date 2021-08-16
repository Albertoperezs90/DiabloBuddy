package com.aperezsi.core.utilities.clickhandler

import com.aperezsi.core.utilities.time.TimeProvider

class DebouncerClick(private val callback: () -> Unit, private val timeProvider: TimeProvider) {

    private var lastTimeClicked = 0L

    fun onClick(interval: Long = 600L) {
        if (timeProvider.elapsedBootTime() - lastTimeClicked >= interval) {
            lastTimeClicked = timeProvider.elapsedBootTime()
            callback()
        }
    }
}