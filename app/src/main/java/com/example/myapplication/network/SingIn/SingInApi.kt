package com.example.myapplication.network.SingIn

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SingInApi {
    @POST("v1/user/register")
    suspend fun createUser(
        @Body body: ResponseInput
    ):Response<ResponseOutput>


}