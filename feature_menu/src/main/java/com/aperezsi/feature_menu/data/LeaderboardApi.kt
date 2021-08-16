package com.aperezsi.feature_menu.data

import com.aperezsi.feature_menu.data.model.SeasonIndexResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LeaderboardApi {

    @GET("/data/d3/season/")
    suspend fun getSeasonIndex(@Query("access_token") accessToken: String): SeasonIndexResponse
}