package com.aperezsi.diablobuddy.provider

import android.os.SystemClock

class TimeProvider {
    fun elapsedBootTime(): Long {
        return SystemClock.elapsedRealtime()
    }
}
