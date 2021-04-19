package com.aperezsi.diablobuddy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aperezsi.core.logger.Logger
import com.aperezsi.core.logger.crash.CrashlyticsLogger
import com.aperezsi.core.logger.tracker.AnalyticsLogger
import com.aperezsi.core.logger.tracker.TrackEvent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val crashLogger = CrashlyticsLogger(FirebaseCrashlytics.getInstance())
    private val trackLogger = AnalyticsLogger(FirebaseAnalytics.getInstance(this))
    private val logger = Logger(crashLogger, trackLogger)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productFlavour.text = BuildConfig.APPLICATION_ID
        logger.logEvent(TrackEvent.Search("wizard"))
        logger.logCrashInfo("Esto es un crash")
    }
}
