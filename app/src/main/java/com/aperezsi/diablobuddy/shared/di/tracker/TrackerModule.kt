package com.aperezsi.diablobuddy.shared.di.tracker

import android.app.Application
import com.aperezsi.core.interfaces.tracker.EventTracker
import com.aperezsi.diablobuddy.shared.tracker.AnalyticsTracker
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