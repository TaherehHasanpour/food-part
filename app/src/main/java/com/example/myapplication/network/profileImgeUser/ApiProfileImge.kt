package com.example.myapplication.network.profileImgeUser

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiProfileImage {
    @GET("files/users/")
    suspend fun profileImage(
        @Path("userId") userId: String,
        @Path("filename") filename: String
    ):Response<String>
}