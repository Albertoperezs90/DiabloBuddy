package com.aperezsi.core.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseFragment<V : ViewBinding, VM : ViewModel> : Fragment() {

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
    }

    abstract fun inflate(): V
    abstract fun setUpView()
}