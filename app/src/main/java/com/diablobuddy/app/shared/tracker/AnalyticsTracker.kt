package com.diablobuddy.app.shared.tracker

import android.os.Bundle
import com.diablobuddy.core.interfaces.tracker.EventTracker
import com.diablobuddy.core.interfaces.tracker.MetricEvent
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsTracker(private val firebaseAnalytics: FirebaseAnalytics) : EventTracker {

    override fun trackEvent(event: MetricEvent) {
        firebaseAnalytics.logEvent(event.eventName, Bundle())
    }
}
