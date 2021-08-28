package com.aperezsi.diablobuddy.shared.logger

import android.util.Log
import com.aperezsi.core.interfaces.logger.Logger

class AndroidLogger : Logger {

    override fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun error(tag: String, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }
}
