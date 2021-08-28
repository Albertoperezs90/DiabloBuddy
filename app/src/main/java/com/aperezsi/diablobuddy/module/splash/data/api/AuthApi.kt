package com.aperezsi.diablobuddy.module.splash.data.api

import com.aperezsi.diablobuddy.module.splash.data.model.OauthResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/oauth/token")
    suspend fun getToken(@Field("grant_type") grantType: String = "client_credentials"): OauthResponse
}