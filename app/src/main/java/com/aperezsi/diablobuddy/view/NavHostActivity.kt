package com.aperezsi.diablobuddy.view

import com.aperezsi.diablobuddy.databinding.ActivityMainBinding
import com.aperezsi.diablobuddy.di.view.NavHostComponent
import com.aperezsi.diablobuddy.view.base.BaseActivity

class NavHostActivity: BaseActivity<ActivityMainBinding>() {

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun injectFields(navHostComponent: NavHostComponent) = navHostComponent.inject(this)
}