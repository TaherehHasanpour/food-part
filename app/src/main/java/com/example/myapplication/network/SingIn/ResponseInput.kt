package com.example.myapplication.network.SingIn

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseInput(
    val username:String,
    val password:String
)
