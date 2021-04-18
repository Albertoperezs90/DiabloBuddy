package com.aperezsi.core.logger

import com.aperezsi.core.logger.interfaces.CrashLogger
import com.aperezsi.core.logger.interfaces.TrackLogger
import com.aperezsi.core.logger.tracker.TrackEvent

class Logger(private val crashLogger: CrashLogger, private val trackLogger: TrackLogger) {

    fun logEvent(trackEvent: TrackEvent) {
        trackLogger.logEvent(trackEvent)
    }

    fun logCrashInfo(information: String) {
        crashLogger.logCrashInfo(information)
    }

}