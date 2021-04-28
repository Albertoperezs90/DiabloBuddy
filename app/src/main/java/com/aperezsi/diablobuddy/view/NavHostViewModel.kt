package com.aperezsi.diablobuddy.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NavHostViewModel @Inject constructor(initialCounter: Int) : ViewModel() {

    val counter = MutableLiveData(initialCounter)

    fun increaseCounter() {
        counter.value = counter.value?.plus(1)
    }
}
