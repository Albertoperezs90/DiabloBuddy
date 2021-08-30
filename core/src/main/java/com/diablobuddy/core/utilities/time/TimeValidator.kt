package com.diablobuddy.core.utilities.time

import javax.inject.Inject

class TimeValidator @Inject constructor() {

    fun isTimeLongerThan(time: Int, timeToCompare: Int): Boolean {
        return time > timeToCompare
    }

    fun isTimeShorterThan(time: Int, timeToCompare: Int): Boolean {
        return time < timeToCompare
    }
}