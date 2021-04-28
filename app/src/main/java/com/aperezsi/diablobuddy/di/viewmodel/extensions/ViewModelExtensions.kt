package com.aperezsi.diablobuddy.di.viewmodel.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aperezsi.diablobuddy.view.base.BaseActivity

inline fun <reified VM : ViewModel> BaseActivity.viewModel(): Lazy<VM> {
    return lazyOf(ViewModelProvider(this).get(VM::class.java))
}