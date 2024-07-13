package com.example.myapplication.network.editUser.userName

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
interface ApiEditUserName {
    @POST("v1/user/edit")
    suspend fun editUserName(
        @Header("Authorization") authorization: String,
        @Body body: BodyUserName
    ): Response<UserNameEditResponse>
}