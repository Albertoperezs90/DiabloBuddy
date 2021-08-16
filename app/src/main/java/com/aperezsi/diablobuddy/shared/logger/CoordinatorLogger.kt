package com.aperezsi.diablobuddy.shared.logger

import com.aperezsi.core.interfaces.logger.Logger
import javax.inject.Inject

class CoordinatorLogger @Inject constructor(private val loggers: Set<Logger>) : Logger {

    override fun debug(tag: String, message: String) {
        loggers.forEach { it.debug(tag, message) }
    }

    override fun error(tag: String, message: String, throwable: Throwable) {
        loggers.forEach { it.error(tag, message, throwable) }
    }
}