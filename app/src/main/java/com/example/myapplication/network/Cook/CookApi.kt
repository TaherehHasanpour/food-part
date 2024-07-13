package com.example.myapplication.network.Cook

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CookApi {
    @GET("v1/what-to-cook")
    suspend fun cook(
        @Query("ingredients") query: String,
        @Query("timeLimit") query1: Int,
        @Query("difficulty") query3: String?
    ): Response<CookResponse>
}