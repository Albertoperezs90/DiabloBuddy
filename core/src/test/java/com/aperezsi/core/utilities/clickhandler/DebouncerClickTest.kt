package com.aperezsi.core.utilities.clickhandler

import com.aperezsi.core.utilities.time.TimeProvider
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DebouncerClickTest {

    private val callback: () -> Unit = mock()
    private val timeProvider: TimeProvider = mock()
    private val debouncer = DebouncerClick(callback, timeProvider)

    @Test
    fun `Debouncer on click should notify callback only if atleast 600 miliseconds has passed`() {
        whenever(timeProvider.elapsedBootTime()).thenReturn(600L)

        debouncer.onClick()

        verify(callback).invoke()
    }

    @Test
    fun `Debouncer on click should notify on intervals of 600 as minimum`() {
        whenever(timeProvider.elapsedBootTime()).thenReturn(1200L)
        debouncer.onClick()

        whenever(timeProvider.elapsedBootTime()).thenReturn(1500L)
        debouncer.onClick()

        whenever(timeProvider.elapsedBootTime()).thenReturn(1800L)
        debouncer.onClick()

        whenever(timeProvider.elapsedBootTime()).thenReturn(1801L)
        debouncer.onClick()

        verify(callback, times(2)).invoke()
    }
}