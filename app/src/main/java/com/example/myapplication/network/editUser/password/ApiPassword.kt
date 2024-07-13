package com.example.myapplication.network.editUser.password

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiPassword {
    @POST("v1/user/edit")
    suspend fun editPassword (
        @Header("Authorization") authorization: String,
        @Body body:BodyPassword
    ): Response<ResponsePassword>
}