package com.aperezsi.diablobuddy.logger.tracker

import com.google.firebase.analytics.FirebaseAnalytics

fun TrackEvent.toFirebaseEvent(): String {
    when (this) {
        is TrackEvent.Search -> return FirebaseAnalytics.Event.SEARCH
    }
}
