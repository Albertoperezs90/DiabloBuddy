package com.aperezsi.feature_menu

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MenuViewModel @Inject constructor() : ViewModel() {

    val counter = MutableLiveData(0)

    fun increaseCounter() {
        counter.value = counter.value?.inc()
        Log.d("**test**", counter.value.toString())
    }
}