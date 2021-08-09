package com.aperezsi.diablobuddy.di.application

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aperezsi.diablobuddy.DiabloBuddyApplication

fun AppCompatActivity.appComponent(): AppComponent {
    return (application as DiabloBuddyApplication).appComponent
}

fun Fragment.appComponent(): AppComponent {
    return (requireActivity().application as DiabloBuddyApplication).appComponent
}