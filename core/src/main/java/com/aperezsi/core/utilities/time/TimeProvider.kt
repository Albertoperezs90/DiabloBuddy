package com.aperezsi.core.utilities.time

import android.os.SystemClock
import java.util.Date
import javax.inject.Inject

class TimeProvider @Inject constructor() {

    fun elapsedBootTime(): Long {
        return SystemClock.elapsedRealtime()
    }

    fun getCurrentTime(): Long {
        return Date().time
    }
}
