package com.aperezsi.core.logger

interface Logger {

    fun debug(tag: String, message: String)
    fun error(tag: String, message: String, throwable: Throwable)

}
