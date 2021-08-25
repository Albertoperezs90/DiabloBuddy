package com.aperezsi.core.framework.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aperezsi.core.exception.NetworkException
import com.aperezsi.core.exception.UnknownException
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.state.Event
import com.aperezsi.core.state.ViewState
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<VS: ViewState, E: Event>(private val logger: Logger): ViewModel() {

    protected val jobs = mutableListOf<Job>()

    private val mutableViewState = MutableSharedFlow<VS>()
    val viewState: SharedFlow<VS>
        get() = mutableViewState

    protected fun updateViewState(viewState: VS) {
        viewModelScope.launch(DispatcherProvider.main()) {
            mutableViewState.emit(viewState)
        }
    }

    protected fun <T> launchWithHandler(lambda: suspend () -> T): Job {
        val job = viewModelScope.launch(DispatcherProvider.main() + errorHandler) { lambda() }
        job.invokeOnCompletion { jobs.remove(job) }
        jobs.add(job)
        return job
    }

    private val errorHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is NetworkException -> logger.error("ERROR", "${throwable.httpCode} -> ${throwable.url}", throwable.exception)
            is UnknownException -> logger.error("ERROR", "Error on response", throwable.exception)
            else                -> logger.error("ERROR", "Unknown error cause", throwable)
        }
    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
    }

    abstract fun onEvent(event: E)
}