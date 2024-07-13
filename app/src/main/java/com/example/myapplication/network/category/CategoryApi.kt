package com.example.myapplication.network.category

import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("v1/category")
    suspend fun getCategory(): Response<CategoryResponse>


}