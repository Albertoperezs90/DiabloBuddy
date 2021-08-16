package com.aperezsi.core.di.session

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SessionModule {

    @Provides
    @Named("session")
    fun provideSharedSessionPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("session", Context.MODE_PRIVATE)
    }

}