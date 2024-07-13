package com.example.myapplication.network.Login

import com.example.myapplication.network.SingIn.ResponseInput
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("v1/user/login")
    suspend fun loginUser(
        @Body body: ResponseInput
    ): Response<LoginResponse>
}