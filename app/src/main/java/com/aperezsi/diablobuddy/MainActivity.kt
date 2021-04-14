package com.aperezsi.diablobuddy

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import com.aperezsi.core.BaseActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    private val firebaseAnalytics = Firebase.analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productFlavour.text = BuildConfig.APPLICATION_ID
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundleOf(
            FirebaseAnalytics.Param.ITEM_ID to BuildConfig.APPLICATION_ID
        ))
    }
}