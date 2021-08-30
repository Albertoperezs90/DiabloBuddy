package com.diablobuddy.app.shared.di.tracker

import android.app.Application
import com.diablobuddy.core.interfaces.tracker.EventTracker
import com.diablobuddy.app.shared.tracker.AnalyticsTracker
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides

@Module
class TrackerModule {

    @Provides
    fun provideEventTracker(application: Application): EventTracker {
        return AnalyticsTracker(FirebaseAnalytics.getInstance(application))
    }
}