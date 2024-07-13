package com.example.myapplication.network.CategoryDetail


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
interface DetailApi {
    @GET("v1/food/{FoodId}")
    suspend fun getDetailById(
        @Path("FoodId") FoodId: String
    ): Response<CategoryDetailResponse>
}