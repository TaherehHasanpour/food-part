package com.example.myapplication.network.SingIn

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseOutput(
    val data:DataSingIn
)
