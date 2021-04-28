package com.aperezsi.diablobuddy.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.aperezsi.diablobuddy.base.extensions.setOnDebounceClickListener
import com.aperezsi.diablobuddy.base.extensions.viewBinding
import com.aperezsi.diablobuddy.databinding.ActivityMainBinding
import com.aperezsi.diablobuddy.view.base.BaseActivity

class NavHostActivity : BaseActivity() {

    override val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewmodel: NavHostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel.counter.observe(this, Observer {
            binding.textView.text = it.toString()
        })

        binding.button.setOnDebounceClickListener {
            viewmodel.increaseCounter()
        }
    }
}
