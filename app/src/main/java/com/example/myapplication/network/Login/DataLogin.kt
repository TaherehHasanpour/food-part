package com.example.myapplication.network.Login

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataLogin(
    val token:String,
    val user:UserLogin
)
