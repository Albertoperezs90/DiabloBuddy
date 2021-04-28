package com.aperezsi.diablobuddy.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavHostViewModel : ViewModel() {

    val counter = MutableLiveData(0)

    fun increaseCounter() {
        counter.value = counter.value?.plus(1)
    }
}