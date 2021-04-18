package com.aperezsi.core.logger.tracker

sealed class TrackEvent(open val value: String) {
    class Search(override val value: String): TrackEvent(value)
}
