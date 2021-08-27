package com.aperezsi.diablobuddy.shared.di.storage

import android.content.SharedPreferences
import com.aperezsi.core.utilities.time.TimeProvider
import com.aperezsi.core.utilities.time.TimeValidator
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class StorageModule {

    @Provides
    fun provideSessionPreferences(@Named("session") sharedPreferences: SharedPreferences, timeValidator: TimeValidator, timeProvider: TimeProvider): SessionPreferences {
        return SessionPreferences(sharedPreferences, timeValidator, timeProvider)
    }
}