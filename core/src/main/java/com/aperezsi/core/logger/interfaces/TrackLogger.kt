package com.aperezsi.core.logger.interfaces

import com.aperezsi.core.logger.tracker.TrackEvent

interface TrackLogger {
    fun logEvent(trackEvent: TrackEvent)
}
