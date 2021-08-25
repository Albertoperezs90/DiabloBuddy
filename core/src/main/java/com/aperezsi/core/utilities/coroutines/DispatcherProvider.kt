package com.aperezsi.core.utilities.coroutines

import kotlinx.coroutines.Dispatchers

object DispatcherProvider {

    fun io() = Dispatchers.IO
    fun main() = Dispatchers.Main
    fun default() = Dispatchers.Default
}