package com.aperezsi.diablobuddy.di.logger

import com.aperezsi.core.logger.Logger
import com.aperezsi.diablobuddy.logger.AndroidLogger
import com.aperezsi.diablobuddy.logger.CoordinatorLogger
import com.aperezsi.diablobuddy.logger.CrashlyticsLogger
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