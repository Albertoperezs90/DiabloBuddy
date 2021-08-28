package com.aperezsi.diablobuddy.shared.di.storage

import android.content.SharedPreferences
import com.aperezsi.core.utilities.time.TimeProvider
import com.aperezsi.core.utilities.time.TimeValidator
import com.aperezsi.diablobuddy.shared.storage.AppPreferences
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideSessionPreferences(sharedPreferences: SharedPreferences, timeValidator: TimeValidator, timeProvider: TimeProvider): AppPreferences {
        return AppPreferences(sharedPreferences, timeValidator, timeProvider)
    }
}