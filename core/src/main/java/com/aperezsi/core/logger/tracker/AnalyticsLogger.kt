package com.aperezsi.core.logger.tracker

import androidx.core.os.bundleOf
import com.aperezsi.core.logger.interfaces.TrackLogger
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsLogger(private val firebaseAnalytics: FirebaseAnalytics) : TrackLogger {

    override fun logEvent(trackEvent: TrackEvent) {
        firebaseAnalytics.logEvent(
            trackEvent.toFirebaseEvent(),
            bundleOf(FirebaseAnalytics.Param.CONTENT to trackEvent.value)
        )
    }
}