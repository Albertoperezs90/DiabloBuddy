package com.aperezsi.diablobuddy.module.splash.data.model

import com.aperezsi.diablobuddy.module.splash.domain.model.LoginData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OauthResponse(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "token_type") val tokenType: String,
    @Json(name = "expires_in") val expiresIn: Int,
    @Json(name = "sub") val sub: String
) {

    fun toDomain() = LoginData(accessToken, expiresIn)
}