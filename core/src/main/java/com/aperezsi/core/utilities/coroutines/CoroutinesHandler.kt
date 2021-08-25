package com.aperezsi.core.utilities.coroutines

suspend fun <T> T.then(next: suspend (T) -> Unit) {
    next(this)
}