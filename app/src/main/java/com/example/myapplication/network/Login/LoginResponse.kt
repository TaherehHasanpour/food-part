package com.example.myapplication.network.Login

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val data:DataLogin
)
