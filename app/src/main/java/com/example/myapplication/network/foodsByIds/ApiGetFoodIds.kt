package com.example.myapplication.network.foodsByIds

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiGetFoodIds {
    @GET("v1/food/")
    suspend fun getDetailById(
        @Query("ids") ids: Input
    ): Response<Data>
}