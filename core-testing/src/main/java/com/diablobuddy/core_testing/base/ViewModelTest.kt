package com.diablobuddy.core_testing.base

import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import org.mockito.kotlin.whenever

open class ViewModelTest {

    fun configureDispatcher(dispatcherProvider: DispatcherProvider) {
        whenever(dispatcherProvider.main).thenReturn(Dispatchers.Unconfined)
        whenever(dispatcherProvider.io).thenReturn(Dispatchers.Unconfined)
        whenever(dispatcherProvider.default).thenReturn(Dispatchers.Unconfined)
    }
}