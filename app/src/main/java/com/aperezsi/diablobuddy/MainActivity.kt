package com.aperezsi.diablobuddy

import android.os.Bundle
import android.util.Log
import com.aperezsi.core.BaseActivity

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val test = null
        Log.d("Test", test!!)
    }
}