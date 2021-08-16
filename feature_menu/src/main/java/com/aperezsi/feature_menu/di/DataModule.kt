package com.aperezsi.feature_menu.di

import com.aperezsi.feature_menu.data.LeaderboardApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun provideLeaderboardApi(retrofit: Retrofit): LeaderboardApi {
        return retrofit.create(LeaderboardApi::class.java)
    }
}