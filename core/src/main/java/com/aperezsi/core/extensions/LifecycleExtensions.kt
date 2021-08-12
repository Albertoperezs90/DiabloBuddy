package com.aperezsi.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aperezsi.core.framework.base.BaseFragment
import kotlin.reflect.KClass

fun <T> Fragment.observe(liveData: LiveData<T>, lambda: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer<T> {
        lambda(it!!)
    })
}

fun <T : ViewModel> BaseFragment<*, *>.provideViewModel(viewModel: KClass<T>): T {
    return ViewModelProvider(this, viewModelFactory).get(viewModel.java)
}