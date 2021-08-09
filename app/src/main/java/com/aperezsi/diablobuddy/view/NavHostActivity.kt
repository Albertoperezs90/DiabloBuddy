package com.aperezsi.diablobuddy.view

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.diablobuddy.databinding.ActivityMainBinding

class NavHostActivity : BaseActivity<ActivityMainBinding>() {

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}