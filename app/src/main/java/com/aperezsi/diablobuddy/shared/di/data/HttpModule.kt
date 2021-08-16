package com.aperezsi.diablobuddy.shared.di.data

import com.aperezsi.diablobuddy.BuildConfig
import com.aperezsi.diablobuddy.shared.data.converter.ConverterFactoryProvider
import com.aperezsi.diablobuddy.shared.data.converter.MoshiProvider
import com.aperezsi.diablobuddy.shared.data.interceptor.ApiInterceptor
import com.aperezsi.diablobuddy.shared.data.interceptor.AuthInterceptor
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

@Module
class HttpModule {

    @Named("region")
    @Provides
    fun provideRegion(): String {
        return "eu"
    }

    @Provides
    fun provideConverterFactory(): ConverterFactoryProvider {
        return MoshiProvider()
    }

    @Named("auth")
    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return AuthInterceptor(BuildConfig.CLIENT_USERNAME, BuildConfig.CLIENT_PASSWORD)
    }

    @Named("api")
    @Provides
    fun provideApiInterceptor(sessionPreferences: SessionPreferences): Interceptor {
        return ApiInterceptor(sessionPreferences)
    }

    @Named("authClient")
    @Provides
    fun provideAuthClient(@Named("auth") authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Named("apiClient")
    @Provides
    fun provideApiClient(@Named("api") apiInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(apiInterceptor).build()
    }

    @Named("api")
    @Provides
    fun provideRetrofit(
        @Named("region") region: String,
        converterFactoryProvider: ConverterFactoryProvider,
        @Named("apiClient") apiClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactoryProvider.build())
            .baseUrl("https://${region}${BuildConfig.BASE_API_URL}")
            .client(apiClient)
            .build()
    }

    @Named("battle_net")
    @Provides
    fun provideBattleNetRetrofit(
        @Named("region") region: String,
        converterFactoryProvider: ConverterFactoryProvider,
        @Named("authClient") authClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactoryProvider.build())
            .baseUrl("https://${region}${BuildConfig.BASE_BATTLENET_URL}")
            .client(authClient)
            .build()
    }
}