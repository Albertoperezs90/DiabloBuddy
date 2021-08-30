package com.diablobuddy.app.shared.di.network

import com.diablobuddy.app.BuildConfig
import com.diablobuddy.app.shared.data.converter.ConverterFactoryProvider
import com.diablobuddy.app.shared.data.interceptor.ApiInterceptor
import com.diablobuddy.app.shared.data.interceptor.AuthInterceptor
import com.diablobuddy.app.shared.data.interceptor.ErrorInterceptor
import com.diablobuddy.app.shared.data.interceptor.LoggingInterceptor
import com.diablobuddy.app.shared.storage.AppPreferences
import com.diablobuddy.core.interfaces.logger.Logger
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

@Module
class NetworkModule {

    @Named("region")
    @Provides
    fun provideRegion(): String {
        return "eu"
    }

    @Named("logging")
    @Provides
    fun provideLoggerInterceptor(logger: Logger): Interceptor {
        return LoggingInterceptor(logger)
    }

    @Named("error")
    @Provides
    fun provideErrorInterceptor(): Interceptor {
        return ErrorInterceptor()
    }

    @Named("auth")
    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return AuthInterceptor(BuildConfig.CLIENT_USERNAME, BuildConfig.CLIENT_PASSWORD)
    }

    @Named("api")
    @Provides
    fun provideApiInterceptor(appPreferences: AppPreferences): Interceptor {
        return ApiInterceptor(appPreferences)
    }

    @Named("authClient")
    @Provides
    fun provideAuthClient(@Named("auth") authInterceptor: Interceptor, @Named("logging") loggingInterceptor: Interceptor, @Named("error") errorInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).addInterceptor(loggingInterceptor).addInterceptor(errorInterceptor).build()
    }

    @Named("apiClient")
    @Provides
    fun provideApiClient(@Named("api") apiInterceptor: Interceptor, @Named("logging") loggingInterceptor: Interceptor, @Named("error") errorInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(apiInterceptor).addInterceptor(loggingInterceptor).addInterceptor(errorInterceptor).build()
    }

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