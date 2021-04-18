package com.aperezsi.core.logger

class Logger(private val crashLogger: CrashLogger, private val trackLogger: TrackLogger) {

    fun logEvent() {
        trackLogger.logEvent()
    }

    fun logCrashInfo(information: String) {
        crashLogger.logCrashInfo(information)
    }

}