package com.aperezsi.core.logger

import com.aperezsi.core.logger.interfaces.CrashLogger
import com.aperezsi.core.logger.interfaces.TrackLogger
import com.aperezsi.core.logger.tracker.TrackEvent
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LoggerTest {

    private val crashLogger: CrashLogger = mock()
    private val trackLogger: TrackLogger = mock()

    private val logger = Logger(crashLogger, trackLogger)

    @Test
    fun `log event should call track logger just once`() {
        val trackEvent = TrackEvent.Search("demon hunter")

        logger.logEvent(trackEvent)

        verify(trackLogger).logEvent(trackEvent)
    }

    @Test
    fun `crash logger should call crash logger just once with provided string`() {
        val providedString = "information"

        logger.logCrashInfo(providedString)

        verify(crashLogger).logCrashInfo(providedString)
    }

}