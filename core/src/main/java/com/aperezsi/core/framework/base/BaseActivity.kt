package com.aperezsi.core.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseActivity<V : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var binding: V
        private set

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate()
        setContentView(binding.root)
        setUpView()
        initialize()
    }

    abstract fun inflate(): V
    abstract fun setUpView()
    abstract fun initialize()
}
