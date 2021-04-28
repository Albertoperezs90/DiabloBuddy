package com.aperezsi.diablobuddy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aperezsi.diablobuddy.base.viewBinding
import com.aperezsi.diablobuddy.databinding.ActivityMainBinding

class NavHostActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
