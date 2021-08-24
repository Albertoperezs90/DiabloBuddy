package com.aperezsi.feature_menu.data

import com.aperezsi.feature_menu.data.model.SeasonIndexResponse
import retrofit2.http.GET

interface LeaderboardApi {

    @GET("/data/d3/season/")
    suspend fun getSeasonIndex(): SeasonIndexResponse
}