package com.example.myapplication.network.editUser.userName

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserNameEditResponse(
val data:String
)
