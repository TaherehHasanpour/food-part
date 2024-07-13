package com.example.myapplication.network.editUser.password

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponsePassword(
    val data:String,
    val additionalInfo:AdditionalInfo
)
@JsonClass(generateAdapter = true)
data class AdditionalInfo(
    val token:String
)