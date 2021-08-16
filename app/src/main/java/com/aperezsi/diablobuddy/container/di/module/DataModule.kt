package com.aperezsi.diablobuddy.container.di.module

import com.aperezsi.diablobuddy.container.data.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class DataModule {

    @Provides
    fun provideAuthApi(@Named("battle_net") retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}