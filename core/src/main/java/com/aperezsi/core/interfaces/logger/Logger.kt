package com.aperezsi.core.interfaces.logger

interface Logger {

    fun debug(tag: String, message: String)
    fun error(tag: String, message: String, throwable: Throwable)
}
