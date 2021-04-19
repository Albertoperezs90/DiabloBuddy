package com.aperezsi.diablobuddy

import android.app.Application

class DiabloBuddyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun setLocal() {
        val test = "Test"
        print(test)
    }

    fun setRemote() {
        val test = "Test"
        print(test)
    }
}
