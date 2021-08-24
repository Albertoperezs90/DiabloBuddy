package com.aperezsi.diablobuddy.module.container.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OauthResponse(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "token_type") val tokenType: String,
    @Json(name = "expires_in") val expiresIn: Int,
    @Json(name = "sub") val sub: String
)