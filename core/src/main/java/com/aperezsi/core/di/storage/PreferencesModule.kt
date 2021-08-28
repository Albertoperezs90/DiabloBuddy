package com.aperezsi.core.di.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @Provides
    fun provideSharedSessionPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("app", Context.MODE_PRIVATE)
    }
}