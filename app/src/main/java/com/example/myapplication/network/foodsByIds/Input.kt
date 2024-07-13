package com.example.myapplication.network.foodsByIds

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Input(
    val ids:List<String>
)
