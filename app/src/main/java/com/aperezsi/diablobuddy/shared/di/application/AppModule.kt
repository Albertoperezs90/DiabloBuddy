package com.aperezsi.diablobuddy.shared.di.application

import android.content.SharedPreferences
import com.aperezsi.core.date.TimeProvider
import com.aperezsi.core.date.TimeValidator
import com.aperezsi.core.parser.JsonParser
import com.aperezsi.diablobuddy.shared.parser.MoshiParser
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideJsonParser(): JsonParser {
        return MoshiParser()
    }

    @Provides
    fun provideSessionPreferences(sharedPreferences: SharedPreferences, timeValidator: TimeValidator, timeProvider: TimeProvider): SessionPreferences {
        return SessionPreferences(sharedPreferences, timeValidator, timeProvider)
    }
}