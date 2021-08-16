package com.aperezsi.feature_menu.di

import com.aperezsi.feature_menu.data.LeaderboardApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class DataModule {

    @Provides
    fun provideLeaderboardApi(@Named("api") retrofit: Retrofit): LeaderboardApi {
        return retrofit.create(LeaderboardApi::class.java)
    }
}