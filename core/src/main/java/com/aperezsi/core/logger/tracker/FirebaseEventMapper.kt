package com.aperezsi.core.logger.tracker

import com.google.firebase.analytics.FirebaseAnalytics

fun TrackEvent.toFirebaseEvent(): String {
    when (this) {
        is TrackEvent.Search -> return FirebaseAnalytics.Event.SEARCH
    }
}
