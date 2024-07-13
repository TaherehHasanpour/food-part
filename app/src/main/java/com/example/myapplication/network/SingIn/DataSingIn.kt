package com.example.myapplication.network.SingIn

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataSingIn(
    val avatar:String,
    val  id:String,
    val username:String
)
