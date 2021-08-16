package com.aperezsi.core.date

import android.os.SystemClock
import java.util.*
import javax.inject.Inject

class TimeProvider @Inject constructor() {

    fun elapsedBootTime(): Long {
        return SystemClock.elapsedRealtime()
    }

    fun getCurrentTime(): Long {
        return Date().time
    }

    fun getSeconds(time: Long): Long {
        return time / 1000
    }
}
