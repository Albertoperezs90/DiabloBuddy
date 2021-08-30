package com.diablobuddy.app.shared.di.logger

import com.diablobuddy.core.interfaces.logger.Logger
import com.diablobuddy.app.shared.logger.AndroidLogger
import com.diablobuddy.app.shared.logger.CoordinatorLogger
import com.diablobuddy.app.shared.logger.CrashlyticsLogger
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.Module
import dagger.Provides

@Module
class LoggerModule {

    @Provides
    fun provideCoordinatorLogger(): Logger {
        val loggers = mutableSetOf<Logger>()
        loggers.add(AndroidLogger())
        loggers.add(CrashlyticsLogger(FirebaseCrashlytics.getInstance()))
        return CoordinatorLogger(loggers)
    }
}