package com.aperezsi.core.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.aperezsi.core.state.Renderable
import com.aperezsi.core.state.ViewState
import com.aperezsi.core.utilities.coroutines.DispatcherProviderImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<V: ViewBinding, VM: BaseViewModel<VS, *>, VS: ViewState>: Fragment(), Renderable<VS> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var binding: V
        private set

    protected abstract val viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        initializeViewState()
        initialize()
    }

    private fun initializeViewState() {
        lifecycleScope.launch(DispatcherProviderImpl().main) {
            viewModel.viewState.collect { render(it) }
        }
    }

    abstract fun inflate(): V
    abstract fun setUpView()
    abstract fun initialize()
}