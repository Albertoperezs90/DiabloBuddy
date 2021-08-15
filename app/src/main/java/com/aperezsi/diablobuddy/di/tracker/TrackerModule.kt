package com.aperezsi.diablobuddy.di.tracker

import android.app.Application
import com.aperezsi.core.tracker.EventTracker
import com.aperezsi.diablobuddy.tracker.AnalyticsTracker
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