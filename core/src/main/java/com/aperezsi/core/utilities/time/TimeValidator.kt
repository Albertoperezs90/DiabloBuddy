package com.aperezsi.core.utilities.time

import javax.inject.Inject

class TimeValidator @Inject constructor() {

    companion object {
        const val MINUTES_5 = 300000
        const val MINUTES_60 = 3600000
    }

    fun isTimeLongerThan(time: Int, minutes: Int = MINUTES_5): Boolean {
        return time > minutes
    }

    fun isTimeShorterThan(time: Int, minutes: Int = MINUTES_5): Boolean {
        return time < minutes
    }
}