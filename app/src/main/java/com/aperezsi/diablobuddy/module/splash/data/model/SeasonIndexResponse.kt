package com.aperezsi.diablobuddy.module.splash.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonIndexResponse(
    @Json(name = "_links") val links: SelfUrlApi,
    @Json(name = "season") val seasonsHref: List<HrefApi>,
    @Json(name = "current_season") val currentSeason: Int,
    @Json(name = "service_current_season") val serviceCurrentSeason: Int,
    @Json(name = "service_season_state") val serviceSeasonState: String,
    @Json(name = "last_update_time") val lastUpdatedTime: String,
    @Json(name = "generated_by") val generatedBy: String
)

@JsonClass(generateAdapter = true)
data class SelfUrlApi(
    @Json(name = "self") val self: HrefApi
)

@JsonClass(generateAdapter = true)
data class HrefApi(
    @Json(name = "href") val href: String
)