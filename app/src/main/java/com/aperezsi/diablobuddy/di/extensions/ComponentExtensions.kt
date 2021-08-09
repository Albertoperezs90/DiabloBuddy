package com.aperezsi.diablobuddy.di.extensions

import androidx.fragment.app.Fragment
import com.aperezsi.diablobuddy.DiabloBuddyApplication

fun Fragment.coreComponent() = DiabloBuddyApplication.coreComponent(requireContext())