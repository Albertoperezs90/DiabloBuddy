package com.aperezsi.core.framework

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.core.framework.base.BaseFragment
import com.aperezsi.core.utilities.coroutines.DispatcherProviderImpl
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

fun <T> Fragment.collect(flow: StateFlow<T>, lambda: (T) -> Unit) {
    lifecycleScope.launch(DispatcherProviderImpl().main) {
        flow.collect { lambda(it) }
    }
}

fun <T: ViewModel> BaseActivity<*, *, *>.provideViewModel(viewModel: KClass<T>): T {
    return ViewModelProvider(this, viewModelFactory).get(viewModel.java)
}

fun <T: ViewModel> BaseFragment<*, *, *>.provideViewModel(viewModel: KClass<T>): T {
    return ViewModelProvider(this, viewModelFactory).get(viewModel.java)
}