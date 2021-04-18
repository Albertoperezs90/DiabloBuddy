package com.aperezsi.core.logger.crash

import android.os.Build
import androidx.core.os.bundleOf
import com.aperezsi.core.BuildConfig
import com.aperezsi.core.logger.tracker.AnalyticsLogger
import com.aperezsi.core.logger.tracker.TrackEvent
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

    private val analyticsLogger = AnalyticsLogger(firebaseAnalytics)

    @Test
    fun `logEvent should map custom events to firebase events and set parameters into bundle`() {
        val trackEvent = TrackEvent.Search("barbarian")

        analyticsLogger.logEvent(trackEvent)

        verify(firebaseAnalytics).logEvent(eq(FirebaseAnalytics.Event.SEARCH), any())
    }
}