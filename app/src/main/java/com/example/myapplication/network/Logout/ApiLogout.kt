package com.example.myapplication.network.Logout

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiLogout {
    @GET("v1/user/logout")
    suspend fun userLogout (
        @Header("Authorization") authorization: String
    ): Response<Int>
}