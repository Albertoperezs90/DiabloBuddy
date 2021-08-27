package com.aperezsi.core_testing.utilities

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<Flow<T>>.thenMockFlowResult(result: T): OngoingStubbing<Flow<T>> {
    return thenReturn(flow { emit(result) })
}

fun <T> OngoingStubbing<Flow<T>>.thenMockThrowable(): OngoingStubbing<Flow<T>> {
    return thenReturn(flow { error("test_error") })
}