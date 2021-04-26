package com.aperezsi.diablobuddy.logger

import com.aperezsi.diablobuddy.logger.interfaces.CrashLogger
import com.aperezsi.diablobuddy.logger.interfaces.TrackLogger
import com.aperezsi.diablobuddy.logger.tracker.TrackEvent

class Logger(private val crashLogger: CrashLogger, private val trackLogger: TrackLogger) {

    fun logEvent(trackEvent: TrackEvent) {
        trackLogger.logEvent(trackEvent)
    }

    fun logCrashInfo(information: String) {
        crashLogger.logCrashInfo(information)
    }
}
