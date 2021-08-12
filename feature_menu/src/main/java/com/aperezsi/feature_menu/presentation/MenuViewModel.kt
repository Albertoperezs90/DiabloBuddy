package com.aperezsi.feature_menu.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aperezsi.core.logger.Logger
import com.aperezsi.core.tracker.EventTracker
import com.aperezsi.core.tracker.MetricEvent
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val logger: Logger, private val eventTracker: EventTracker) : ViewModel() {

    val counter = MutableLiveData(0)

    fun increaseCounter() {
        eventTracker.trackEvent(object : MetricEvent {
            override val eventName: String = "event_test_click"
        })
        logger.debug("test", "hola")
        counter.value = counter.value?.inc()
        Log.d("**test**", counter.value.toString())
    }
}