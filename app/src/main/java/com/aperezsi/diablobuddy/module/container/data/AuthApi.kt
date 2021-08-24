package com.aperezsi.diablobuddy.module.container.data

import com.aperezsi.diablobuddy.module.container.data.response.OauthResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/oauth/token")
    suspend fun getToken(@Field("grant_type") grantType: String = "client_credentials"): OauthResponse
}