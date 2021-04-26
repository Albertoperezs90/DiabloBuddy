package com.aperezsi.diablobuddy.test.logger.crash

import com.aperezsi.diablobuddy.logger.tracker.AnalyticsLogger
import com.google.firebase.analytics.FirebaseAnalytics
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [Config.OLDEST_SDK])
class AnalyticsLoggerTest {

    private val firebaseAnalytics: FirebaseAnalytics = mock()

    private val analyticsLogger = com.aperezsi.diablobuddy.logger.tracker.AnalyticsLogger(firebaseAnalytics)

    @Test
    fun `logEvent should map custom events to firebase events and set parameters into bundle`() {
        val trackEvent = com.aperezsi.diablobuddy.logger.tracker.TrackEvent.Search("barbarian")

        analyticsLogger.logEvent(trackEvent)

        verify(firebaseAnalytics).logEvent(eq(FirebaseAnalytics.Event.SEARCH), any())
    }
}
