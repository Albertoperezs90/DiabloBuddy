package com.aperezsi.core.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashlyticsLogger(private val firebaseCrashlytics: FirebaseCrashlytics) : CrashLogger {

    override fun logCrashInfo(information: String) {
        firebaseCrashlytics.log(information)
    }

}