package com.diablobuddy.core.utilities.coroutines

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge

@FlowPreview fun <T, R> Flow<T>.onSuccessCompletion(completion: suspend (T) -> Flow<R>): Flow<R> {
    return flatMapMerge {
        if (it !is Throwable) {
            completion(it)
        } else {
            emptyFlow()
        }
    }
}