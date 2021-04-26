package com.aperezsi.diablobuddy.logger.tracker

sealed class TrackEvent(open val value: String) {
    class Search(override val value: String) : TrackEvent(value)
}
