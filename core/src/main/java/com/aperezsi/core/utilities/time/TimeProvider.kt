package com.aperezsi.core.utilities.time

import android.os.SystemClock
import java.util.Date
import javax.inject.Inject

class TimeProvider @Inject constructor() {

    companion object {
        const val MILISECONDS_TO_SECONDS = 1000
    }

    fun elapsedBootTime(): Long {
        return SystemClock.elapsedRealtime()
    }

    fun getCurrentTime(): Long {
        return Date().time
    }

    fun getSeconds(time: Long): Long {
        return time / MILISECONDS_TO_SECONDS
    }
}
