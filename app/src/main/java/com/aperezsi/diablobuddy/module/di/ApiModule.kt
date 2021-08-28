package com.aperezsi.diablobuddy.module.di

import com.aperezsi.diablobuddy.module.splash.data.api.AuthApi
import com.aperezsi.diablobuddy.module.splash.data.api.LeaderboardApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class ApiModule {

    @Provides
    fun provideAuthApi(@Named("battle_net") retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun provideLeaderboardApi(retrofit: Retrofit): LeaderboardApi {
        return retrofit.create(LeaderboardApi::class.java)
    }
}