package com.example.myapplication.network.editUser.PasswordAndUserName

import com.example.myapplication.network.editUser.password.ResponsePassword
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiPasAndUName {
    @POST("v1/user/edit")
    suspend fun editePasAndUName(
        @Header("Authorization") authorization: String,
        @Body body:BodyResponse
    ): Response<ResponsePassword>
}