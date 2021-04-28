package com.aperezsi.diablobuddy.test.logger

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LoggerTest {

    private val crashLogger: com.aperezsi.diablobuddy.logger.interfaces.CrashLogger = mock()
    private val trackLogger: com.aperezsi.diablobuddy.logger.interfaces.TrackLogger = mock()
    private val logger = com.aperezsi.diablobuddy.logger.Logger(crashLogger, trackLogger)

    @Test
    fun `log event should call track logger just once`() {
        val trackEvent = com.aperezsi.diablobuddy.logger.tracker.TrackEvent.Search("demon hunter")

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
