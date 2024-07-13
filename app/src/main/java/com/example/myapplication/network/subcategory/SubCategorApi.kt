package com.example.myapplication.network.subcategory

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface
SubCategorApi {
    @GET("v1/food/")
    suspend fun getCategoryById(
        @Query("category") categoryId: String
    ): Response<SubCategoryview>
}