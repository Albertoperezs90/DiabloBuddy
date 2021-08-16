package com.aperezsi.core.di.presentation

import androidx.fragment.app.Fragment

interface BaseFragmentComponent<F : Fragment> {
    fun inject(fragment: F)
}