package com.aperezsi.diablobuddy.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aperezsi.diablobuddy.DiabloBuddyApplication
import com.aperezsi.diablobuddy.base.extensions.setOnDebounceClickListener
import com.aperezsi.diablobuddy.base.extensions.viewBinding
import com.aperezsi.diablobuddy.databinding.ActivityMainBinding
import com.aperezsi.diablobuddy.view.base.BaseActivity
import javax.inject.Inject

class NavHostActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    override val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var viewmodel: NavHostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DiabloBuddyApplication).appComponent.inject(this)
        viewmodel = ViewModelProvider(this, viewModelFactory).get(NavHostViewModel::class.java)

        viewmodel.counter.observe(this, Observer {
            binding.textView.text = it.toString()
        })

        binding.button.setOnDebounceClickListener {
            viewmodel.increaseCounter()
        }
    }
}
