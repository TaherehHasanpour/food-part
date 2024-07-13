package com.example.myapplication.network.FoodByMeal

import com.example.myapplication.network.subcategory.SubCategoryview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMeal {
    @GET("v1/food/")
    suspend fun getMealById(
        @Query("meal") meal: String
    ): Response<SubCategoryview>
}