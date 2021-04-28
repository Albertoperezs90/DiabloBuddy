package com.aperezsi.diablobuddy.provider

import android.os.SystemClock
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TimeProviderTest {

    private val timeProvider = TimeProvider()

    @Test
    fun `elapsedBootTime should return same time as System clock elapsedRealTime`() {
        val elapsedBootTime = timeProvider.elapsedBootTime()
        val elapsedRealTime = SystemClock.elapsedRealtime()

        assertEquals(elapsedRealTime, elapsedBootTime)
    }
}
