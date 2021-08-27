package com.aperezsi.core.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.aperezsi.core.state.Renderable
import com.aperezsi.core.state.ViewState
import com.aperezsi.core.utilities.coroutines.DispatcherProviderImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity<V: ViewBinding, VM: BaseViewModel<VS, *>, VS: ViewState>: AppCompatActivity(), Renderable<VS> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var binding: V
        private set

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate()
        setContentView(binding.root)
        injectDependencies()
        initializeViewState()
        initialize()
    }

    private fun initializeViewState() {
        lifecycleScope.launch(DispatcherProviderImpl().main) {
            viewModel.viewState.collect { render(it) }
        }
    }

    abstract fun inflate(): V
    abstract fun injectDependencies()
    abstract fun initialize()
}
