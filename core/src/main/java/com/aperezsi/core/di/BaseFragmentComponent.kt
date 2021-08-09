package com.aperezsi.core.di

import androidx.fragment.app.Fragment

interface BaseFragmentComponent<F : Fragment> {
    fun inject(fragment: F)
}