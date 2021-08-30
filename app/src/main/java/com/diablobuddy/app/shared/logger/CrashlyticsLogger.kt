package com.diablobuddy.app.shared.logger

import com.diablobuddy.core.interfaces.logger.Logger
import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashlyticsLogger(private val firebaseCrashlytics: FirebaseCrashlytics) : Logger {

    override fun debug(tag: String, message: String) {
        firebaseCrashlytics.log("DEBUG: $message")
    }

    override fun error(tag: String, message: String, throwable: Throwable) {
        firebaseCrashlytics.log("ERROR: $message")
        firebaseCrashlytics.recordException(throwable)
    }
}
