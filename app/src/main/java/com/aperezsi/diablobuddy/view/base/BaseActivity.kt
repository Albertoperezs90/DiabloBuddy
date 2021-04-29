package com.aperezsi.diablobuddy.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.aperezsi.diablobuddy.di.application.appComponent
import com.aperezsi.diablobuddy.di.view.NavHostComponent
import javax.inject.Inject

abstract class BaseActivity<B: ViewBinding>: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    internal lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding()
        setContentView(binding.root)
        val navHostComponent = appComponent().navHostComponent().create(this)
        injectFields(navHostComponent)
    }

    abstract fun viewBinding(): B
    abstract fun injectFields(navHostComponent: NavHostComponent)
}
