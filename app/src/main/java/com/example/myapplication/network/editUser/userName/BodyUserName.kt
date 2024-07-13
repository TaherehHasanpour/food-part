package com.example.myapplication.network.editUser.userName

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BodyUserName(
    val username:String
)
