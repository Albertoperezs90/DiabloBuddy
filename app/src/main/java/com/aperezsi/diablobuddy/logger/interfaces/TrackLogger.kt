package com.aperezsi.diablobuddy.logger.interfaces

import com.aperezsi.diablobuddy.logger.tracker.TrackEvent

interface TrackLogger {
    fun logEvent(trackEvent: TrackEvent)
}
