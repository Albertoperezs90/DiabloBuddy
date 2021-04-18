package com.aperezsi.diablobuddy

import android.os.Bundle
import com.aperezsi.core.BaseActivity
import com.aperezsi.core.logger.AnalyticsLogger
import com.aperezsi.core.logger.CrashlyticsLogger
import com.aperezsi.core.logger.Logger
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val crashLogger = CrashlyticsLogger(FirebaseCrashlytics.getInstance())
    private val trackLogger = AnalyticsLogger()
    private val logger = Logger(crashLogger, trackLogger)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productFlavour.text = BuildConfig.APPLICATION_ID
        logger.logEvent()
        logger.logCrashInfo("Esto es un crash")
    }
}