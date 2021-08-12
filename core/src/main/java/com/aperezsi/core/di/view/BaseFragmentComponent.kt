package com.aperezsi.core.di.view

import androidx.fragment.app.Fragment

interface BaseFragmentComponent<F : Fragment> {
    fun inject(fragment: F)
}