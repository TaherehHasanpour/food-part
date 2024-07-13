package com.example.myapplication.network.editUser.password

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BodyPassword (
    val oldPassword:String,
    val newpassword:String
)