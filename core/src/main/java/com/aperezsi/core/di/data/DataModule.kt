package com.aperezsi.core.di.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideSharedSessionPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("session", Context.MODE_PRIVATE)
    }

}