package com.aperezsi.diablobuddy.shared.di.data

import com.aperezsi.diablobuddy.BuildConfig
import com.aperezsi.diablobuddy.shared.data.converter.ConverterFactoryProvider
import com.aperezsi.diablobuddy.shared.data.converter.MoshiProvider
import com.aperezsi.diablobuddy.shared.data.interceptor.AuthInterceptor
import com.aperezsi.diablobuddy.shared.data.interceptor.AuthInterceptorImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

@Module
class HttpModule {

    @Provides
    @Named("region")
    fun provideRegion(): String {
        return "eu"
    }

    @Provides
    fun provideConverterFactory(): ConverterFactoryProvider {
        return MoshiProvider()
    }

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptorImpl(BuildConfig.CLIENT_USERNAME, BuildConfig.CLIENT_PASSWORD)
    }

    @Provides
    fun provideAuthClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }
    
    @Provides
    fun provideRetrofit(
        @Named("region") region: String,
        converterFactoryProvider: ConverterFactoryProvider
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactoryProvider.build())
            .baseUrl("https://${region}${BuildConfig.BASE_API_URL}")
            .build()
    }

    @Named("battle_net")
    @Provides
    fun provideBattleNetRetrofit(
        @Named("region") region: String,
        converterFactoryProvider: ConverterFactoryProvider,
        authClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactoryProvider.build())
            .baseUrl("https://${region}${BuildConfig.BASE_BATTLENET_URL}")
            .client(authClient)
            .build()
    }
}