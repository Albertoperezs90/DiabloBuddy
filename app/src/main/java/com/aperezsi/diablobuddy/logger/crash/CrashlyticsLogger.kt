package com.aperezsi.diablobuddy.logger.crash

import com.aperezsi.diablobuddy.logger.interfaces.CrashLogger
import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashlyticsLogger(private val firebaseCrashlytics: FirebaseCrashlytics) : CrashLogger {

    override fun logCrashInfo(information: String) {
        firebaseCrashlytics.log(information)
    }
}
