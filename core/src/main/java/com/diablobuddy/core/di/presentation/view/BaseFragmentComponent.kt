package com.diablobuddy.core.di.presentation.view

import androidx.fragment.app.Fragment

interface BaseFragmentComponent<F : Fragment> {
    fun inject(fragment: F)
}