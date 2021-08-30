package com.diablobuddy.app.shared.di.storage

import android.content.SharedPreferences
import com.diablobuddy.core.utilities.time.TimeProvider
import com.diablobuddy.core.utilities.time.TimeValidator
import com.diablobuddy.app.shared.storage.AppPreferences
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideSessionPreferences(sharedPreferences: SharedPreferences, timeValidator: TimeValidator, timeProvider: TimeProvider): AppPreferences {
        return AppPreferences(sharedPreferences, timeValidator, timeProvider)
    }
}