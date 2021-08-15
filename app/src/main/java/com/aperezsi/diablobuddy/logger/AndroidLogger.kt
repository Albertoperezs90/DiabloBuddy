package com.aperezsi.diablobuddy.logger

import android.util.Log
import com.aperezsi.core.logger.Logger

class AndroidLogger : Logger {

    override fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun error(tag: String, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }
}
