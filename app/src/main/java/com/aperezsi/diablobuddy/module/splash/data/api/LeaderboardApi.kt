package com.aperezsi.diablobuddy.module.splash.data.api

import com.aperezsi.diablobuddy.module.splash.data.model.SeasonIndexResponse
import retrofit2.http.GET

interface LeaderboardApi {

    @GET("/data/d3/season/")
    suspend fun getSeasonIndex(): SeasonIndexResponse
}